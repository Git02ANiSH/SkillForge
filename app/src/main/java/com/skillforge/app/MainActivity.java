package com.skillforge.app;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.WindowCompat;
import androidx.navigation.NavController;
import androidx.navigation.fragment.NavHostFragment;
import androidx.navigation.NavGraph;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        WindowCompat.setDecorFitsSystemWindows(getWindow(), true);

        setContentView(R.layout.activity_main);

        NavHostFragment navHostFragment =
                (NavHostFragment) getSupportFragmentManager()
                        .findFragmentById(R.id.nav_host_fragment);

        if (navHostFragment == null) {
            return;
        }

        NavController navController = navHostFragment.getNavController();

        NavGraph navGraph = navController.getNavInflater()
                .inflate(R.navigation.nav_graph);

        String destination = getIntent().getStringExtra("start_destination");

        if ("dashboard".equals(destination)) {

            navGraph.setStartDestination(R.id.dashboardFragment);

        } else if ("login".equals(destination)) {

            navGraph.setStartDestination(R.id.loginFragment);

        } else {

            navGraph.setStartDestination(R.id.onboardingFragment);

        }

        navController.setGraph(navGraph);
    }
}