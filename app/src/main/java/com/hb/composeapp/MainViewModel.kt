package com.hb.composeapp

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.hb.composeapp.data.ApiClient
import com.hb.composeapp.data.NewsResponse
import com.hb.composeapp.data.Resource
import com.hb.composeapp.data.Secrets
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import retrofit2.Response

class MainViewModel : ViewModel() {

    val newsResponseLiveData = MutableLiveData<Resource<NewsResponse?>>()


    init {
        loadData()
    }

    fun loadData() {
        viewModelScope.launch {
            newsResponseLiveData.value = Resource.Loading()
            val response: Response<NewsResponse> = ApiClient.api.getPopularNews()
            if (response.isSuccessful) {
                val data = response.body()
                if (data != null && data.status == Secrets.STATUS_SUCCESS && !data.articles.isNullOrEmpty()) {
                    newsResponseLiveData.value = Resource.Success(data)
                } else {
                    newsResponseLiveData.value = Resource.Failure(errorCode = 400)
                }
            } else {
                newsResponseLiveData.value = Resource.Failure(errorCode = response.code(), error = response.message())
            }
        }
    }
}