package com.example.quizappvi;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import android.os.Bundle;

import com.example.quizappvi.adapters.MainPagerAdapter;
import com.example.quizappvi.ui.fragments.MainFragment;

public class MainActivity extends AppCompatActivity {

    private ViewPager viewPager;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        viewPager = findViewById(R.id.main_pager);

        viewPager.setAdapter(new MainPagerAdapter(getSupportFragmentManager()));

    }
}