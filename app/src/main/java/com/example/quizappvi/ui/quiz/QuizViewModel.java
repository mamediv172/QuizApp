package com.example.quizappvi.ui.quiz;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.quizappvi.QuizApp;
import com.example.quizappvi.data.remote.IQuizApiClient;
import com.example.quizappvi.model.Question;

import java.util.List;

public class QuizViewModel extends ViewModel {

    MutableLiveData<List<Question>> questions = new MutableLiveData<>();
    MutableLiveData<Integer> currentQuestionPosition = new MutableLiveData<>();
    MutableLiveData<Boolean> isLoading = new MutableLiveData<>();

    private IQuizApiClient quizApiClient = QuizApp.quizApiClient;
    private List<Question> mQuestions;



    void init(int amount, int categoryIndex, String difficultyIndex){
        isLoading.setValue(true);
        quizApiClient.getQuestions(new IQuizApiClient.QuestionsCallBack() {
            @Override
            public void onSuccess(List<Question> result) {
                mQuestions = result;
                questions.setValue(mQuestions);
                currentQuestionPosition.setValue(0);
                isLoading.setValue(false);
            }

            @Override
            public void onFailure(Exception e) {

            }
        });
    }


    private void moveToQuestionOrFinish(int position){
        if (position == mQuestions.size()){
            finishQuiz();
        } else {
            currentQuestionPosition.setValue(position);
        }
    }

    void finishQuiz(){

    }

    void onSkipClick(){

    }

    void onBackPressed(){

    }

    public void onAnswerClick(int questionPosition, int answerPosition) {
        if (currentQuestionPosition.getValue() == null || mQuestions == null){
            return;
        }

        Question question = mQuestions.get(questionPosition);

        question.setSelectAnswerPosition(answerPosition);

        mQuestions.set(questionPosition, question);

        questions.setValue(mQuestions);

        moveToQuestionOrFinish(questionPosition + 1);
    }
}
