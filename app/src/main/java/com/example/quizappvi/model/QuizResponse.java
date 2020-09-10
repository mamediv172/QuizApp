package com.example.quizappvi.model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class QuizResponse {

    @SerializedName("response_code")
    private int responseCode;
    @SerializedName("results")
    private List<Question> questions;


    public List<Question> getQuestions() {
        return questions;
    }

    public void setQuestions(List<Question> questions) {
        this.questions = questions;
    }


    public int getResponseCode() {
        return responseCode;
    }

    public void setResponseCode(int responseCode) {
        this.responseCode = responseCode;
    }


}
