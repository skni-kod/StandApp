package com.sknikod.standapp.uti

// T- type data E- type error
sealed class Result<T>(val value: T?) {
    class Init<T> : Result<T>(null)
    class Loading<T> : Result<T>(null)
    class Success<T>(value: T) : Result<T>(value)
    class FailureFetch<T, E>(val error: E) : Result<T>(null)
    class Error<T>(val throwable: Throwable) : Result<T>(null)
}
inline fun <T> Result<T>.onFailure(action: (exception: Throwable) -> Unit): Result<T> {
    if (this is Result.Error) action(this.throwable)
    return this
}

inline fun <T> Result<T>.onSuccess(action: (value: T) -> Unit): Result<T> {
    if (this is Result.Success) action(value as T)
    return this
}

inline fun <T> Result<T>.onLoading(action: () -> Unit): Result<T> {
    if (this is Result.Loading) action()
    return this
}