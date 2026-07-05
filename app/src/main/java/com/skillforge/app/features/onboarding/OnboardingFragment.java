package com.skillforge.app.features.onboarding;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.viewpager2.widget.ViewPager2;

import com.skillforge.app.R;
import com.skillforge.app.databinding.FragmentOnboardingBinding;
import com.skillforge.app.features.onboarding.adapter.OnboardingAdapter;
import com.skillforge.app.features.onboarding.model.OnboardingPage;

import java.util.ArrayList;
import java.util.List;

public class OnboardingFragment extends Fragment {

    private FragmentOnboardingBinding binding;
    private List<OnboardingPage> pages;

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

        pages = new ArrayList<>();

        pages.add(new OnboardingPage(
                R.drawable.skillforge_logo,
                getString(R.string.onboarding_title_1),
                getString(R.string.onboarding_desc_1)
        ));

        pages.add(new OnboardingPage(
                R.drawable.skillforge_logo,
                getString(R.string.onboarding_title_2),
                getString(R.string.onboarding_desc_2)
        ));

        pages.add(new OnboardingPage(
                R.drawable.skillforge_logo,
                getString(R.string.onboarding_title_3),
                getString(R.string.onboarding_desc_3)
        ));

        OnboardingAdapter adapter = new OnboardingAdapter(pages);
        binding.viewPager.setAdapter(adapter);

        // Show first indicator
        updateIndicator(0);

        // Move indicator when page changes
        binding.viewPager.registerOnPageChangeCallback(
                new ViewPager2.OnPageChangeCallback() {
                    @Override
                    public void onPageSelected(int position) {
                        super.onPageSelected(position);

                        updateIndicator(position);
                    }
                });

        // Next button
        binding.btnNext.setOnClickListener(v -> {

            int current = binding.viewPager.getCurrentItem();

            if (current < pages.size() - 1) {

                binding.viewPager.setCurrentItem(current + 1, true);

            }

        });
    }

    /**
     * Updates the custom page indicator.
     */
    private void updateIndicator(int position) {

        ImageView[] indicators = {
                binding.indicator1,
                binding.indicator2,
                binding.indicator3
        };

        for (int i = 0; i < indicators.length; i++) {

            if (i == position) {

                indicators[i].setImageResource(R.drawable.indicator_selected);

            } else {

                indicators[i].setImageResource(R.drawable.indicator_unselected);

            }
        }
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}