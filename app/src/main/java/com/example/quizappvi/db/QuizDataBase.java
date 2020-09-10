package com.example.quizappvi.db;


import androidx.room.Database;
import androidx.room.RoomDatabase;

import com.example.quizappvi.model.QuizResult;


@Database(entities = {QuizResult.class}, version = 1)
public abstract class QuizDataBase extends RoomDatabase {
    public abstract QuizDao quizDao();
}
