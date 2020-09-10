package com.example.quizappvi.data.remote;

import com.example.quizappvi.core.IBaseCallback;
import com.example.quizappvi.model.Question;

import java.util.List;

public interface IQuizApiClient {

    void getQuestions(QuestionsCallBack callBack);

    interface QuestionsCallBack extends IBaseCallback<List<Question>>{
        @Override
        void onSuccess(List<Question> result);

        @Override
        void onFailure(Exception e);
    }
}
