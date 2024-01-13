package com.hb.composeapp.data

import retrofit2.Response
import retrofit2.http.GET

interface ApiService {

    @GET("everything?q=apple&from=2024-01-11&to=2024-01-11&sortBy=popularity&apiKey=${Secrets.API_KEY}")
    suspend fun getPopularNews(): Response<NewsResponse>
}