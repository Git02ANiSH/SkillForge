package com.skillforge.app.core.util;

import android.view.View;

import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class WindowInsetsHelper {

    private WindowInsetsHelper() {
        // Prevent instantiation
    }

    public static void applyTopInset(View view) {

        final int originalLeft = view.getPaddingLeft();
        final int originalTop = view.getPaddingTop();
        final int originalRight = view.getPaddingRight();
        final int originalBottom = view.getPaddingBottom();

        ViewCompat.setOnApplyWindowInsetsListener(view, (v, insets) -> {

            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());

            v.setPadding(
                    originalLeft,
                    originalTop + systemBars.top,
                    originalRight,
                    originalBottom
            );

            return insets;
        });

        ViewCompat.requestApplyInsets(view);
    }
}