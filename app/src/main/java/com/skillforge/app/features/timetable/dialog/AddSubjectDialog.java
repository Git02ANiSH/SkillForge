package com.skillforge.app.features.timetable.dialog;

import android.app.AlertDialog;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;

import com.skillforge.app.R;

public class AddSubjectDialog {

    public static void show(Context context) {

        View view = LayoutInflater.from(context)
                .inflate(R.layout.dialog_add_subject, null);

        new AlertDialog.Builder(context)
                .setTitle("Add Subject")
                .setView(view)
                .setPositiveButton("Save", null)
                .setNegativeButton("Cancel", null)
                .show();
    }
}