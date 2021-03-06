package com.example.quizappvi.ui.quiz;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.PagerSnapHelper;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.SnapHelper;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;

import com.example.quizappvi.R;
import com.example.quizappvi.model.Question;
import com.example.quizappvi.ui.quiz.recycler.QuestionAdapter;
import com.example.quizappvi.ui.quiz.recycler.QuestionViewHolder;

import java.util.List;

public class QuizActivity extends AppCompatActivity implements QuestionViewHolder.Listener {

    public static final String EXTRA_AMOUNT = "amount";
    public static final String EXTRA_CATEGORY = "categoryIndex";
    public static final String EXTRA_DIFFICULTY = "difficultyIndex";

    private int amountIndex;
    private int categoryIndex;
    private String difficultyIndex;
    private Intent intent = getIntent();
    private QuizViewModel viewModel;

    private RecyclerView recyclerView;
    private QuestionAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz);

        recyclerView = findViewById(R.id.question_recycler);

        viewModel = ViewModelProviders.of(this).get(QuizViewModel.class);

        amountIndex = intent.getIntExtra(EXTRA_AMOUNT, 10);
        categoryIndex = intent.getIntExtra(EXTRA_CATEGORY, 9);
        difficultyIndex = intent.getStringExtra(EXTRA_DIFFICULTY);
        viewModel.init(amountIndex, categoryIndex, difficultyIndex);

        viewModel.questions.observe(this, new Observer<List<Question>>() {
            @Override
            public void onChanged(List<Question> questions) {
                adapter.setQuestions(questions);
            }
        });

        viewModel.currentQuestionPosition.observe(this, new Observer<Integer>() {
            @Override
            public void onChanged(Integer integer) {
                recyclerView.smoothScrollToPosition(integer);
            }
        });

        adapter = new  QuestionAdapter(this);

        recyclerView.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                return true;
            }
        });

        SnapHelper snapHelper = new PagerSnapHelper();
        snapHelper.attachToRecyclerView(recyclerView);


        recyclerView.setLayoutManager(new LinearLayoutManager(this,
                RecyclerView.HORIZONTAL,
                false));

        recyclerView.setAdapter(adapter);
    }


    public static void start(Context context, int amountIndex, int categoryIndex, String difficultyIndex) {
        Intent intent = new Intent(context, QuizActivity.class);
        intent.putExtra(EXTRA_AMOUNT, amountIndex);
        intent.putExtra(EXTRA_CATEGORY, categoryIndex);
        intent.putExtra(EXTRA_DIFFICULTY, difficultyIndex);
        context.startActivity(intent);
    }

    @Override
    public void onAnswerClick(int position, int selectAnswerPosition) {
        viewModel.onAnswerClick(position, selectAnswerPosition);
    }
}