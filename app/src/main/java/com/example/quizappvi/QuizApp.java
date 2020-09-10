package com.example.quizappvi;

import android.app.Application;

import androidx.room.Room;

import com.example.quizappvi.data.QuizRepository;
import com.example.quizappvi.data.remote.IHistoryStorage;
import com.example.quizappvi.data.remote.IQuizApiClient;
import com.example.quizappvi.data.remote.QuizApiClient;
import com.example.quizappvi.db.QuizDataBase;
import com.example.quizappvi.ui.history.HistoryStorage;

public class QuizApp extends Application {

    public static IQuizApiClient quizApiClient;
    private static IHistoryStorage historyStorage;
    public static QuizDataBase quizDataBase;

    public QuizRepository repository;
    @Override
    public void onCreate() {
        super.onCreate();

        quizDataBase = Room.databaseBuilder(
                this,
                QuizDataBase.class,
                "quiz.db"
        ).fallbackToDestructiveMigration()
                .allowMainThreadQueries()
                .build();

        quizApiClient = new QuizApiClient();
        historyStorage = new HistoryStorage();

        repository = new QuizRepository(quizApiClient, historyStorage, quizDataBase.quizDao());

    }



}
