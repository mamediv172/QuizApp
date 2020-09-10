package com.example.quizappvi.data.remote;

import androidx.lifecycle.LiveData;

import com.example.quizappvi.model.QuizResult;

import java.util.List;

public interface IHistoryStorage {


    QuizResult getQuizResult(int id);

    int saveQuizResult(QuizResult quizResult);

    LiveData<List<QuizResult>> getAll();

    void delete(int id);

    void deleteAll();
}
