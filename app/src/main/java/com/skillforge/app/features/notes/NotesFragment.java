package com.skillforge.app.features.notes;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.skillforge.app.core.util.WindowInsetsHelper;
import com.skillforge.app.databinding.FragmentNotesBinding;
import com.skillforge.app.features.notes.adapter.NoteAdapter;

import java.util.ArrayList;
import java.util.List;

public class NotesFragment extends Fragment {

    private FragmentNotesBinding binding;
    private NoteAdapter adapter;
    private final List<String> notes = new ArrayList<>();

    public NotesFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container,
                             Bundle savedInstanceState) {

        binding = FragmentNotesBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view,
                              @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        WindowInsetsHelper.applyTopInset(binding.getRoot());

        adapter = new NoteAdapter();

        binding.rvNotes.setLayoutManager(
                new LinearLayoutManager(requireContext())
        );

        binding.rvNotes.setAdapter(adapter);

        // Demo notes for presentation

        notes.add("Operating System - Revise Process Scheduling.");
        notes.add("DBMS - Practice normalization questions.");
        notes.add("Java - Complete Collections Framework.");

        adapter.setNotes(notes);

        updateUI();

        binding.fabAddNote.setOnClickListener(v -> {

            Toast.makeText(
                    requireContext(),
                    "Add Note feature coming soon",
                    Toast.LENGTH_SHORT
            ).show();

        });
    }

    private void updateUI() {

        if (notes.isEmpty()) {

            binding.layoutEmpty.setVisibility(View.VISIBLE);
            binding.rvNotes.setVisibility(View.GONE);

        } else {

            binding.layoutEmpty.setVisibility(View.GONE);
            binding.rvNotes.setVisibility(View.VISIBLE);

        }
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}