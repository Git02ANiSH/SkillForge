package com.skillforge.app.features.auth;

import android.os.Bundle;
import android.text.TextUtils;
import android.util.Patterns;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;

import com.skillforge.app.databinding.FragmentLoginBinding;

public class LoginFragment extends Fragment {

    private FragmentLoginBinding binding;
    private FirebaseAuth mAuth;

    public LoginFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container,
                             Bundle savedInstanceState) {

        binding = FragmentLoginBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view,
                              @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        mAuth = FirebaseAuth.getInstance();
        binding.btnLogin.setOnClickListener(v -> validateLogin());
    }

    private void validateLogin() {

        // Clear previous errors
        binding.emailLayout.setError(null);
        binding.passwordLayout.setError(null);

        String email = binding.etEmail.getText().toString().trim();
        String password = binding.etPassword.getText().toString().trim();

        // Email Validation
        if (TextUtils.isEmpty(email)) {
            binding.emailLayout.setError("Email is required");
            binding.etEmail.requestFocus();
            return;
        }

        if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            binding.emailLayout.setError("Enter a valid email address");
            binding.etEmail.requestFocus();
            return;
        }

        // Password Validation
        if (TextUtils.isEmpty(password)) {
            binding.passwordLayout.setError("Password is required");
            binding.etPassword.requestFocus();
            return;
        }

        if (password.length() < 6) {
            binding.passwordLayout.setError("Password must be at least 6 characters");
            binding.etPassword.requestFocus();
            return;
        }

        // Validation Passed
        binding.emailLayout.setError(null);
        binding.passwordLayout.setError(null);

        mAuth.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener(requireActivity(), task -> {

                    if (task.isSuccessful()) {

                        Toast.makeText(
                                requireContext(),
                                "Login Successful!",
                                Toast.LENGTH_SHORT
                        ).show();

                    } else {

                        Toast.makeText(
                                requireContext(),
                                task.getException().getMessage(),
                                Toast.LENGTH_LONG
                        ).show();

                    }

                });
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}