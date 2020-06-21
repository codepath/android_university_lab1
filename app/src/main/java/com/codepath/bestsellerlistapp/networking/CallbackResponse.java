package com.codepath.bestsellerlistapp.networking;

public interface CallbackResponse<T> {
    void onSuccess(T model);

    void onFailure(Throwable error);
}
