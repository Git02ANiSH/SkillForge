package com.skillforge.app.features.attendance;

import android.os.Bundle;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.skillforge.app.core.util.WindowInsetsHelper;
import com.skillforge.app.data.database.SkillForgeDatabase;
import com.skillforge.app.data.entity.SubjectEntity;
import com.skillforge.app.databinding.FragmentAttendanceBinding;
import com.skillforge.app.features.attendance.adapter.AttendanceAdapter;

import java.util.List;

public class AttendanceFragment extends Fragment {

    private FragmentAttendanceBinding binding;
    private AttendanceAdapter adapter;
    private SkillForgeDatabase database;

    public AttendanceFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container,
                             Bundle savedInstanceState) {

        binding = FragmentAttendanceBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view,
                              @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        WindowInsetsHelper.applyTopInset(binding.getRoot());

        database = SkillForgeDatabase.getInstance(requireContext());

        adapter = new AttendanceAdapter();

        binding.rvAttendance.setLayoutManager(
                new LinearLayoutManager(requireContext())
        );

        binding.rvAttendance.setAdapter(adapter);

        loadSubjects();

        // Temporary demo actions
        adapter.setAttendanceListener(new AttendanceAdapter.AttendanceListener() {

            @Override
            public void onPresentClicked(SubjectEntity subject) {
                Toast.makeText(
                        requireContext(),
                        subject.getSubjectName() + " marked Present",
                        Toast.LENGTH_SHORT
                ).show();
            }

            @Override
            public void onAbsentClicked(SubjectEntity subject) {
                Toast.makeText(
                        requireContext(),
                        subject.getSubjectName() + " marked Absent",
                        Toast.LENGTH_SHORT
                ).show();
            }
        });

        // Demo overall attendance
        binding.txtOverallAttendance.setText("100%");
    }

    private void loadSubjects() {

        List<SubjectEntity> subjects =
                database.subjectDao().getAllSubjects();

        adapter.setSubjects(subjects);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}