package com.myubuntu.istestproject.repository

import android.content.Context
import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.myubuntu.istestproject.model.Product
import com.myubuntu.istestproject.model.User
import okhttp3.ResponseBody
import org.json.JSONObject
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ProductRepository {
    private val TAG = this::class.java.simpleName
    private var liveProductList: MutableLiveData<Product> = MutableLiveData()

    fun getProductList(context: Context) : MutableLiveData<Product> {
        val user = User.getUser(context)
        user ?: return liveProductList
        val product = Product()

        NetworkUtils.getAPIService().getProducts(user.token).enqueue(object :
                Callback<ResponseBody> {
            override fun onFailure(call: Call<ResponseBody>, t: Throwable) {
                product.hasError = true; product.message = t.message.toString()
                liveProductList.value = product
            }

            override fun onResponse(call: Call<ResponseBody>, response: Response<ResponseBody>) {
                if(response.isSuccessful){
                    try {
                        val obj = response.body()?.string()
                        val jsonObj = JSONObject(obj)
                        if(jsonObj.has("itemList")){
                            val type = object : TypeToken<ArrayList<Product>>() {}.type
                            val list: ArrayList<Product> = Gson().fromJson(jsonObj.getJSONArray("itemList").toString(), type)
                            product.list = list
                            liveProductList.value = product
                        }
                    }
                    catch (e: Exception){
                        e.printStackTrace()
                        product.hasError = true; product.message = e.message.toString()
                        liveProductList.value = product
                    }
                }
                else {
                    product.hasError = true; product.message = "Error unknown";
                    liveProductList.value = product
                }
            }
        })

        return liveProductList
    }
}