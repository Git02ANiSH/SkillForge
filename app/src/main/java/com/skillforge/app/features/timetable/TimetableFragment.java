package com.skillforge.app.features.timetable;

import android.app.AlertDialog;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.skillforge.app.core.util.WindowInsetsHelper;
import com.skillforge.app.data.database.SkillForgeDatabase;
import com.skillforge.app.data.entity.SubjectEntity;
import com.skillforge.app.databinding.FragmentTimetableBinding;
import com.skillforge.app.features.timetable.adapter.SubjectAdapter;
import com.skillforge.app.features.timetable.dialog.AddSubjectDialog;

import java.util.List;

public class TimetableFragment extends Fragment {

    private FragmentTimetableBinding binding;
    private SubjectAdapter adapter;
    private SkillForgeDatabase database;

    public TimetableFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container,
                             Bundle savedInstanceState) {

        binding = FragmentTimetableBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view,
                              @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        WindowInsetsHelper.applyTopInset(binding.getRoot());

        database = SkillForgeDatabase.getInstance(requireContext());

        adapter = new SubjectAdapter();

        binding.rvTimetable.setLayoutManager(
                new LinearLayoutManager(requireContext())
        );

        binding.rvTimetable.setAdapter(adapter);

        loadSubjects();

        // Add Subject
        binding.fabAdd.setOnClickListener(v -> {

            AddSubjectDialog.show(requireContext(), subject -> {

                database.subjectDao().insertSubject(subject);
                loadSubjects();

            });

        });

        // Delete Subject (Long Press)
        adapter.setOnSubjectLongClickListener(subject -> {

            new AlertDialog.Builder(requireContext())
                    .setTitle("Delete Subject")
                    .setMessage("Are you sure you want to delete this subject?")
                    .setPositiveButton("Delete", (dialog, which) -> {

                        database.subjectDao().deleteSubject(subject);

                        loadSubjects();

                    })
                    .setNegativeButton("Cancel", null)
                    .show();

        });

    }

    private void loadSubjects() {

        List<SubjectEntity> subjects =
                database.subjectDao().getAllSubjects();

        adapter.setSubjects(subjects);
    }

    @Override
    public void onResume() {
        super.onResume();

        if (adapter != null) {
            loadSubjects();
        }
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}