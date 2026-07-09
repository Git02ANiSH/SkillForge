package com.skillforge.app.core.util;

import android.content.Context;
import android.content.SharedPreferences;

public class SessionManager {

    private static final String PREF_NAME = "SkillForgePrefs";
    private static final String KEY_FIRST_LAUNCH = "isFirstLaunch";

    private final SharedPreferences preferences;

    public SessionManager(Context context) {
        preferences = context.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE);
    }

    public boolean isFirstLaunch() {
        return preferences.getBoolean(KEY_FIRST_LAUNCH, true);
    }

    public void setFirstLaunchCompleted() {
        preferences.edit().putBoolean(KEY_FIRST_LAUNCH, false).apply();
    }

    // Reset onboarding (useful during development)
    public void resetFirstLaunch() {
        preferences.edit().putBoolean(KEY_FIRST_LAUNCH, true).apply();
    }
}