package com.mungai.intothepotter_verse.common

// This is a sealed class that represents the different states of a resource that is being fetched or processed.
sealed class Resource<T>(val data: T? = null, val message: String? = null) {

    class Loading<T>(data: T? = null) : Resource<T>(data = data)

    class Success<T>(data: T?) : Resource<T>(data = data)

    class Error<T>(data: T? = null, message: String?) : Resource<T>(data = data, message = message)

}

