package com.codepath.bestsellerlistapp.networking

interface CallbackResponse<T> {
    fun onSuccess(model: T)

    fun onFailure(error: Throwable?)
}