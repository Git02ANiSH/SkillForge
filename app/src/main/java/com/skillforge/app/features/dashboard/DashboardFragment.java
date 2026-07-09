package com.skillforge.app.features.dashboard;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.fragment.NavHostFragment;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.skillforge.app.R;
import com.skillforge.app.databinding.FragmentDashboardBinding;

import java.util.Calendar;

public class DashboardFragment extends Fragment {

    private FragmentDashboardBinding binding;
    private FirebaseAuth mAuth;

    public DashboardFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container,
                             Bundle savedInstanceState) {

        binding = FragmentDashboardBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view,
                              @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        mAuth = FirebaseAuth.getInstance();

        // Show logged-in user's email
        FirebaseUser user = mAuth.getCurrentUser();

        if (user != null) {
            binding.txtEmail.setText(user.getEmail());
        }

        // Greeting based on current time
        int hour = Calendar.getInstance().get(Calendar.HOUR_OF_DAY);

        if (hour < 12) {
            binding.txtGreeting.setText("Good Morning");
        } else if (hour < 17) {
            binding.txtGreeting.setText("Good Afternoon");
        } else {
            binding.txtGreeting.setText("Good Evening");
        }

        // Logout Button
        binding.btnLogout.setOnClickListener(v -> {

            mAuth.signOut();

            NavHostFragment.findNavController(DashboardFragment.this)
                    .navigate(R.id.action_dashboardFragment_to_loginFragment);

        });

        binding.btnTimetable.setOnClickListener(v -> {

            NavHostFragment.findNavController(DashboardFragment.this)
                    .navigate(R.id.action_dashboardFragment_to_timetableFragment);

        });
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}