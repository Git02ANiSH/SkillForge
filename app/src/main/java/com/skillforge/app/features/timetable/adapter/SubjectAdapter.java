package com.skillforge.app.features.timetable.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.skillforge.app.R;
import com.skillforge.app.data.entity.SubjectEntity;

import java.util.ArrayList;
import java.util.List;

public class SubjectAdapter extends RecyclerView.Adapter<SubjectAdapter.SubjectViewHolder> {

    private final List<SubjectEntity> subjectList = new ArrayList<>();

    public interface OnSubjectLongClickListener {
        void onLongClick(SubjectEntity subject);
    }

    private OnSubjectLongClickListener longClickListener;

    public void setOnSubjectLongClickListener(OnSubjectLongClickListener listener) {
        this.longClickListener = listener;
    }

    @NonNull
    @Override
    public SubjectViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_subject, parent, false);

        return new SubjectViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull SubjectViewHolder holder, int position) {

        SubjectEntity subject = subjectList.get(position);

        holder.txtSubject.setText(subject.getSubjectName());
        holder.txtTeacher.setText("Teacher : " + subject.getTeacherName());
        holder.txtRoom.setText("Room : " + subject.getRoomNumber());

        holder.txtTime.setText(
                subject.getDay()
                        + " • "
                        + subject.getStartTime()
                        + " - "
                        + subject.getEndTime()
        );

        holder.itemView.setOnLongClickListener(v -> {

            if (longClickListener != null) {
                longClickListener.onLongClick(subject);
            }

            return true;
        });
    }

    @Override
    public int getItemCount() {
        return subjectList.size();
    }

    public void setSubjects(List<SubjectEntity> subjects) {

        subjectList.clear();
        subjectList.addAll(subjects);
        notifyDataSetChanged();
    }

    static class SubjectViewHolder extends RecyclerView.ViewHolder {

        TextView txtSubject;
        TextView txtTeacher;
        TextView txtRoom;
        TextView txtTime;

        public SubjectViewHolder(@NonNull View itemView) {
            super(itemView);

            txtSubject = itemView.findViewById(R.id.txtSubject);
            txtTeacher = itemView.findViewById(R.id.txtTeacher);
            txtRoom = itemView.findViewById(R.id.txtRoom);
            txtTime = itemView.findViewById(R.id.txtTime);
        }
    }
}