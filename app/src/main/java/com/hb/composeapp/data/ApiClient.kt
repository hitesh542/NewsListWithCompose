package com.hb.composeapp.data

import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object ApiClient {
    val api: ApiService by lazy {
        val retrofit = Retrofit.Builder()
            .baseUrl(Secrets.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .client(client)
            .build()
        retrofit.create(ApiService::class.java)
    }

    private val client: OkHttpClient by lazy {
        OkHttpClient.Builder().build()
    }
}