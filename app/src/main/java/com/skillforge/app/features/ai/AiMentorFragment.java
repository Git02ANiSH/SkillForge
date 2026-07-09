package com.skillforge.app.features.ai;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.skillforge.app.core.util.WindowInsetsHelper;
import com.skillforge.app.databinding.FragmentAiMentorBinding;

public class AiMentorFragment extends Fragment {

    private FragmentAiMentorBinding binding;

    public AiMentorFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container,
                             Bundle savedInstanceState) {

        binding = FragmentAiMentorBinding.inflate(inflater, container, false);

        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view,
                              @Nullable Bundle savedInstanceState) {

        super.onViewCreated(view, savedInstanceState);

        WindowInsetsHelper.applyTopInset(binding.getRoot());

        binding.btnStudyPlan.setOnClickListener(v -> {

            binding.txtAiResponse.setText(
                    "📚 Today's Study Plan\n\n" +
                            "• Revise Operating System Unit 3\n\n" +
                            "• Complete DBMS assignment\n\n" +
                            "• Practice Java Collections"
            );

            Toast.makeText(
                    requireContext(),
                    "Study plan generated",
                    Toast.LENGTH_SHORT
            ).show();

        });

        binding.btnSuggestTopics.setOnClickListener(v -> {

            binding.txtAiResponse.setText(
                    "📖 Recommended Topics\n\n" +
                            "• CPU Scheduling\n\n" +
                            "• Database Normalization\n\n" +
                            "• Android Fragments"
            );

        });

        binding.btnMotivate.setOnClickListener(v -> {

            binding.txtAiResponse.setText(
                    "💪 Keep going!\n\n" +
                            "Small progress every day leads to big results.\n\n" +
                            "Stay consistent and trust the process."
            );

        });
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();

        binding = null;
    }
}