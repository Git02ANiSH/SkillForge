package com.skillforge.app.features.timetable.dialog;

import android.app.AlertDialog;
import android.app.TimePickerDialog;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.EditText;

import com.skillforge.app.R;
import com.skillforge.app.data.entity.SubjectEntity;

import java.util.Calendar;

public class AddSubjectDialog {

    public interface OnSubjectAddedListener {
        void onSubjectAdded(SubjectEntity subject);
    }

    public static void show(Context context,
                            OnSubjectAddedListener listener) {

        View view = LayoutInflater.from(context)
                .inflate(R.layout.dialog_add_subject, null);

        EditText etSubject = view.findViewById(R.id.etSubject);
        EditText etTeacher = view.findViewById(R.id.etTeacher);
        EditText etRoom = view.findViewById(R.id.etRoom);

        AutoCompleteTextView etDay = view.findViewById(R.id.etDay);

        EditText etStartTime = view.findViewById(R.id.etStartTime);
        EditText etEndTime = view.findViewById(R.id.etEndTime);

        String[] days = {
                "Monday",
                "Tuesday",
                "Wednesday",
                "Thursday",
                "Friday",
                "Saturday"
        };

        ArrayAdapter<String> adapter =
                new ArrayAdapter<>(
                        context,
                        android.R.layout.simple_dropdown_item_1line,
                        days
                );

        etDay.setAdapter(adapter);

        etStartTime.setOnClickListener(v ->
                showTimePicker(context, etStartTime));

        etEndTime.setOnClickListener(v ->
                showTimePicker(context, etEndTime));

        new AlertDialog.Builder(context)
                .setTitle("Add Subject")
                .setView(view)
                .setNegativeButton("Cancel", null)
                .setPositiveButton("Save", (dialog, which) -> {

                    String subject = etSubject.getText().toString().trim();
                    String teacher = etTeacher.getText().toString().trim();
                    String room = etRoom.getText().toString().trim();
                    String day = etDay.getText().toString().trim();
                    String start = etStartTime.getText().toString().trim();
                    String end = etEndTime.getText().toString().trim();

                    if (subject.isEmpty()) return;

                    SubjectEntity subjectEntity =
                            new SubjectEntity(
                                    subject,
                                    teacher,
                                    room,
                                    day,
                                    start,
                                    end
                            );

                    listener.onSubjectAdded(subjectEntity);

                })
                .show();
    }

    private static void showTimePicker(Context context,
                                       EditText editText) {

        Calendar calendar = Calendar.getInstance();

        int hour = calendar.get(Calendar.HOUR_OF_DAY);
        int minute = calendar.get(Calendar.MINUTE);

        TimePickerDialog dialog =
                new TimePickerDialog(
                        context,
                        (view, h, m) -> {

                            String time = String.format("%02d:%02d", h, m);
                            editText.setText(time);

                        },
                        hour,
                        minute,
                        true
                );

        dialog.show();
    }
}