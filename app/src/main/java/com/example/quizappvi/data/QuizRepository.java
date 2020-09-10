package com.example.quizappvi.data;

import androidx.lifecycle.LiveData;

import com.example.quizappvi.data.remote.IHistoryStorage;
import com.example.quizappvi.data.remote.IQuizApiClient;
import com.example.quizappvi.db.QuizDao;
import com.example.quizappvi.model.Question;
import com.example.quizappvi.model.QuizResult;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class QuizRepository implements IHistoryStorage, IQuizApiClient {

    private IQuizApiClient quizApiClient;
    private IHistoryStorage historyStorage;
    private QuizDao quizDao;

    public QuizRepository(IQuizApiClient quizApiClient, IHistoryStorage historyStorage, QuizDao quizDao) {
        this.quizApiClient = quizApiClient;
        this.historyStorage = historyStorage;
        this.quizDao = quizDao;
    }


    public void getQuestions1(final IQuizApiClient.QuestionsCallBack callBack){
        quizApiClient.getQuestions(new IQuizApiClient.QuestionsCallBack(){

            @Override
            public void onSuccess(List<Question> result) {
                for (int i = 0; i < result.size(); i++){
                    result.set(i, shuffleAnswer(result.get(i)));
                }

                callBack.onSuccess(result);
            }

            @Override
            public void onFailure(Exception e) {

            }
        });
    }


    private Question shuffleAnswer(Question question){
        ArrayList<String> answers = new ArrayList<>();

        answers.add(question.getCorrectAnswer());
        answers.addAll(question.getIncorrectAnswers());

        Collections.shuffle(answers);
        question.setAnswers(answers);

        return question;
    }

    @Override
    public QuizResult getQuizResult(int id) {
        return null;
    }

    @Override
    public int saveQuizResult(QuizResult quizResult) {
        return 0;
    }

    @Override
    public LiveData<List<QuizResult>> getAll() {
        return null;
    }

    @Override
    public void delete(int id) {

    }

    @Override
    public void deleteAll() {

    }

    @Override
    public void getQuestions(QuestionsCallBack callBack) {

    }
}
