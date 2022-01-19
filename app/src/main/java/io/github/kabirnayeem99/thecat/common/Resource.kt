package io.github.kabirnayeem99.thecat.common

sealed class Resource<T>(val data: T?, val message: String?) {
    class Success<T>(data: T) : Resource<T>(data, null)
    class Error<T>(message: String) : Resource<T>(null, message)
    class Loading<T> : Resource<T>(null, null)
    class Empty<T> : Resource<T>(null, null)
}