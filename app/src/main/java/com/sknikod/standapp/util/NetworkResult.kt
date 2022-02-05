package com.sknikod.standapp.util

sealed class NetworkResult<T>(
    val data:T?=null,
    val exception: Exception?=null
)
{
    class Success<T>(data: T) : NetworkResult<T>(data)
    class Error<T>(data:T?=null, exception: Exception?) : NetworkResult<T>(data,exception)
}
