package com.hb.composeapp.data

sealed class Resource<T> {
    class Loading<T> : Resource<T>()
    data class Success<T>(val data: T) : Resource<T>()
    data class Failure<T>(val errorCode: Int, val error: String? = null) : Resource<T>()
}