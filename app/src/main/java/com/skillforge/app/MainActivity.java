package com.skillforge.app;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import com.skillforge.app.features.onboarding.OnboardingFragment;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if (savedInstanceState == null) {
            getSupportFragmentManager()
                    .beginTransaction()
                    .replace(R.id.fragment_container, new OnboardingFragment())
                    .commit();
        }
    }
}