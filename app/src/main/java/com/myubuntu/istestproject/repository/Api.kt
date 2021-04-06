package com.myubuntu.istestproject.repository

import com.myubuntu.istestproject.model.Product
import okhttp3.ResponseBody
import org.json.JSONObject
import retrofit2.Call
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.FormUrlEncoded
import retrofit2.http.Header
import retrofit2.http.POST

interface Api {
    @POST(NetworkUtils.LOGIN)
    fun login(
        @Body params: HashMap<String, String>
    ): Call<ResponseBody>

    @POST(NetworkUtils.GET_PRODUCTS)
    fun getProducts(
        @Header("Authorization") token: String
    ): Call<ResponseBody>
}