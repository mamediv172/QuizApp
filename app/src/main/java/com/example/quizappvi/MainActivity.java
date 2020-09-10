package com.example.quizappvi;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;


import android.os.Bundle;
import android.view.MenuItem;

import com.example.quizappvi.adapters.MainPagerAdapter;
import com.example.quizappvi.data.remote.IQuizApiClient;
import com.example.quizappvi.model.Question;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    private ViewPager viewPager;
    private BottomNavigationView bottomNavigationView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initializationViews();
        createMainBottomNavigationWithViewPager();

        QuizApp.quizApiClient.getQuestions(new IQuizApiClient.QuestionsCallBack() {
            @Override
            public void onSuccess(List<Question> questions) {

            }

            @Override
            public void onFailure(Exception e) {

            }
        });
    }

    private void initializationViews() {
        viewPager = findViewById(R.id.main_pager);
        bottomNavigationView = findViewById(R.id.bottomNavigation_main);
    }

    private void createMainBottomNavigationWithViewPager() {
        viewPager.setAdapter(new MainPagerAdapter(getSupportFragmentManager()));
        viewPager.setOffscreenPageLimit(2);
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.navigation_explore:
                        viewPager.setCurrentItem(0, false);
                        break;
                    case R.id.navigation_map:
                        viewPager.setCurrentItem(1, false);
                        break;
                    case R.id.navigation_settings:
                        viewPager.setCurrentItem(2, false);
                        break;
                }
                return true;
            }
        });

        if (getSupportActionBar() != null) {
            getSupportActionBar().setElevation(0.0f);
            getSupportActionBar().setTitle(R.string.textTitle_main_quiz);
            getSupportActionBar().setBackgroundDrawable(getDrawable(R.drawable.background_white));
        }
        viewPager.addOnPageChangeListener(new ViewPager.SimpleOnPageChangeListener() {

            @Override
            public void onPageSelected(int position) {
                if (getSupportActionBar() != null) {
                    switch (position) {
                        case 0:
                            getSupportActionBar().setTitle(R.string.textTitle_main_quiz);
                            break;
                        case 1:
                            getSupportActionBar().setTitle(R.string.textTitle_main_history);
                            break;
                        case 2:
                            getSupportActionBar().setTitle(R.string.textTitle_main_settings);
                            break;
                    }
                }
            }

        });
    }
}