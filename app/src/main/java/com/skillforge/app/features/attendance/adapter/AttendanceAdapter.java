package com.skillforge.app.features.attendance.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.button.MaterialButton;
import com.skillforge.app.R;
import com.skillforge.app.data.entity.SubjectEntity;

import java.util.ArrayList;
import java.util.List;

public class AttendanceAdapter extends RecyclerView.Adapter<AttendanceAdapter.AttendanceViewHolder> {

    public interface AttendanceListener {
        void onPresentClicked(SubjectEntity subject);
        void onAbsentClicked(SubjectEntity subject);
    }

    private final List<SubjectEntity> subjectList = new ArrayList<>();
    private AttendanceListener listener;

    public void setAttendanceListener(AttendanceListener listener) {
        this.listener = listener;
    }

    public void setSubjects(List<SubjectEntity> subjects) {
        subjectList.clear();
        subjectList.addAll(subjects);
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public AttendanceViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_attendance, parent, false);

        return new AttendanceViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull AttendanceViewHolder holder, int position) {

        SubjectEntity subject = subjectList.get(position);

        holder.txtSubjectName.setText(subject.getSubjectName());

        // Temporary percentage for UI demo
        holder.txtAttendance.setText("Attendance : 100%");

        holder.btnPresent.setOnClickListener(v -> {
            if (listener != null) {
                listener.onPresentClicked(subject);
            }
        });

        holder.btnAbsent.setOnClickListener(v -> {
            if (listener != null) {
                listener.onAbsentClicked(subject);
            }
        });
    }

    @Override
    public int getItemCount() {
        return subjectList.size();
    }

    static class AttendanceViewHolder extends RecyclerView.ViewHolder {

        TextView txtSubjectName;
        TextView txtAttendance;
        MaterialButton btnPresent;
        MaterialButton btnAbsent;

        AttendanceViewHolder(@NonNull View itemView) {
            super(itemView);

            txtSubjectName = itemView.findViewById(R.id.txtSubjectName);
            txtAttendance = itemView.findViewById(R.id.txtAttendance);
            btnPresent = itemView.findViewById(R.id.btnPresent);
            btnAbsent = itemView.findViewById(R.id.btnAbsent);
        }
    }
}