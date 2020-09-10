package com.example.quizappvi.ui.main;

import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.SeekBar;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.quizappvi.R;
import com.example.quizappvi.ui.quiz.QuizActivity;
import com.example.quizappvi.utils.SimpleSeekBarChangeListener;

public class MainFragment extends Fragment {

    private MainViewModel mViewModel;
    private TextView mAmount;
    private Button mStart;
    private SeekBar mSeekBar;
    private Spinner categorySpinner;
    private Spinner difficultySpinner;

    private int amountIndex;
    private int categoryIndex = 9;
    private String difficultyIndex = "easy";


    public static MainFragment newInstance() {
        return new MainFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.main_fragment, container, false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = ViewModelProviders.of(this).get(MainViewModel.class);
        mViewModel.message.observe(getViewLifecycleOwner(), new Observer<String>() {
            @Override
            public void onChanged(String s) {
                Toast.makeText(getContext(), s, Toast.LENGTH_SHORT).show();
            }
        });

    }


    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mAmount = view.findViewById(R.id.amount);
        mStart = view.findViewById(R.id.button_main_start);
        mSeekBar = view.findViewById(R.id.amount_seek_bar);
        categorySpinner = view.findViewById(R.id.spinner_main_category);
        difficultySpinner = view.findViewById(R.id.spinner_main_difficulty);

        mSeekBar.setOnSeekBarChangeListener(new SimpleSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                mAmount.setText(String.valueOf(progress));
                amountIndex = progress;
            }
        });

        mStart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                QuizActivity.start(getContext(), amountIndex, categoryIndex, difficultyIndex);
            }
        });
    }
}