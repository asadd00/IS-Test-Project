package com.myubuntu.istestproject.repository

import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.myubuntu.istestproject.model.User
import okhttp3.ResponseBody
import org.json.JSONObject
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class LoginRepository {
    private val TAG = this::class.java.simpleName
    private var liveUser: MutableLiveData<User> = MutableLiveData()

    fun login(username: String, password: String): MutableLiveData<User>{
        val user = User()

        val params = HashMap<String, String>()
        params["username"] = username
        params["password"] = password
        NetworkUtils.getAPIService().login(params).enqueue(
            object : Callback<ResponseBody> {
                override fun onFailure(call: Call<ResponseBody>, t: Throwable) {
                    user.hasError = true
                    user.message = t.message.toString()
                    liveUser.value = user
                }

                override fun onResponse(call: Call<ResponseBody>, response: Response<ResponseBody>) {
                    if(response.isSuccessful){
                        try {
                            val obj = response.body()?.string()
                            val jsonData = JSONObject(obj)
                            if(obj != null && jsonData.has("token")){
                                val token = jsonData.getString("token")
                                Log.e(TAG, "token: $token")
                                user.token = "Bearer $token"
                                liveUser.value = user
                            }
                        }
                        catch (e:Exception){
                            e.printStackTrace()
                            user.hasError = true; user.message = e.message.toString()
                            liveUser.value = user
                        }
                    }
                    else {
                        user.hasError = true; user.message = "Error unknown"
                        liveUser.value = user
                    }
                }
            }
        )

        return liveUser
    }
}