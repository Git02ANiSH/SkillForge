package com.skillforge.app.features.onboarding;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.skillforge.app.R;
import com.skillforge.app.databinding.FragmentOnboardingBinding;
import com.skillforge.app.features.onboarding.adapter.OnboardingAdapter;
import com.skillforge.app.features.onboarding.model.OnboardingPage;

import java.util.ArrayList;
import java.util.List;

public class OnboardingFragment extends Fragment {

    private FragmentOnboardingBinding binding;

    public OnboardingFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container,
                             Bundle savedInstanceState) {

        binding = FragmentOnboardingBinding.inflate(inflater, container, false);

        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view,
                              @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        List<OnboardingPage> pages = new ArrayList<>();

        pages.add(new OnboardingPage(
                R.drawable.skillforge_logo,
                "Welcome to SkillForge",
                "Study Smarter.\nStay Organized.\nGrow Every Day."
        ));

        OnboardingAdapter adapter = new OnboardingAdapter(pages);

        binding.viewPager.setAdapter(adapter);

        binding.btnNext.setOnClickListener(v -> {

            int current = binding.viewPager.getCurrentItem();

            if (current < pages.size() - 1) {
                binding.viewPager.setCurrentItem(current + 1, true);
            }

        });
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}