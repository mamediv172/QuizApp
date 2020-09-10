package com.example.quizappvi.core;

public interface IBaseCallback<T> {

    void onSuccess(T result);

    void onFailure(Exception e);
}
