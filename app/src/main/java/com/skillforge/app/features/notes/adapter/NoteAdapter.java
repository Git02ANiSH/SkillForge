package com.skillforge.app.features.notes.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.skillforge.app.R;

import java.util.ArrayList;
import java.util.List;

public class NoteAdapter extends RecyclerView.Adapter<NoteAdapter.NoteViewHolder> {

    private final List<String> notes = new ArrayList<>();

    public void setNotes(List<String> noteList) {
        notes.clear();
        notes.addAll(noteList);
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public NoteViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_note, parent, false);

        return new NoteViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull NoteViewHolder holder, int position) {

        String note = notes.get(position);

        holder.txtNoteTitle.setText("Note " + (position + 1));
        holder.txtNoteContent.setText(note);
    }

    @Override
    public int getItemCount() {
        return notes.size();
    }

    static class NoteViewHolder extends RecyclerView.ViewHolder {

        TextView txtNoteTitle;
        TextView txtNoteContent;

        public NoteViewHolder(@NonNull View itemView) {
            super(itemView);

            txtNoteTitle = itemView.findViewById(R.id.txtNoteTitle);
            txtNoteContent = itemView.findViewById(R.id.txtNoteContent);
        }
    }
}