package com.skillforge.app;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.WindowCompat;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.skillforge.app.core.util.SessionManager;

public class SplashActivity extends AppCompatActivity {

    private SessionManager sessionManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        WindowCompat.setDecorFitsSystemWindows(getWindow(), true);

        setContentView(R.layout.activity_splash);

        sessionManager = new SessionManager(this);

        new Handler(Looper.getMainLooper()).postDelayed(() -> {

            FirebaseUser currentUser = FirebaseAuth.getInstance().getCurrentUser();

            Intent intent;

            if (currentUser != null) {

                // User already logged in
                intent = new Intent(SplashActivity.this, MainActivity.class);
                intent.putExtra("start_destination", "dashboard");

            } else if (!sessionManager.isFirstLaunch()) {

                // Onboarding completed, go to Login
                intent = new Intent(SplashActivity.this, MainActivity.class);
                intent.putExtra("start_destination", "login");

            } else {

                // First launch
                intent = new Intent(SplashActivity.this, MainActivity.class);
                intent.putExtra("start_destination", "onboarding");

            }

            startActivity(intent);
            finish();

        }, 1500);
    }
}