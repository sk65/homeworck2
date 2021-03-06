package com.example.homeworklesson2.fragment;


import android.os.Bundle;
import android.util.Log;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.preference.ListPreference;
import androidx.preference.Preference;
import androidx.preference.PreferenceFragmentCompat;
import androidx.preference.SwitchPreference;

import com.example.homeworklesson2.R;
import com.example.homeworklesson2.SendChanges;
import com.example.homeworklesson2.activity.MainActivity;
import com.google.android.material.snackbar.Snackbar;

import java.util.HashMap;
import java.util.Map;

import static com.example.homeworklesson2.activity.MainActivity.DEV_TEG;

public class SettingsFragment extends PreferenceFragmentCompat implements Preference.OnPreferenceChangeListener, SendChanges {
    public static final String TAG = "SettingsFragmentTag";
    private boolean languageChangesFlag;
    private boolean themeChangesFlag;
    private ListPreference listPreferenceSelectLanguage;
    private SwitchPreference switchPreferenceSelectTheme;
    private final String PREFERENCE_SELECT_LANGUAGE_KEY = "list_preference_selectLanguage";
    private final String PREFERENCE_CHANGE_THEME_KEY = "switchPreference_changeTheme";
    public static final String LANGUAGE_KEY = "Language";
    public static final String THEME_KEY = "Theme";


    @Override
    public void onCreatePreferences(Bundle savedInstanceState, String rootKey) {
        setPreferencesFromResource(R.xml.root_preferences, rootKey);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        switchPreferenceSelectTheme = findPreference(PREFERENCE_CHANGE_THEME_KEY);
        listPreferenceSelectLanguage = findPreference(PREFERENCE_SELECT_LANGUAGE_KEY);
        listPreferenceSelectLanguage.setOnPreferenceChangeListener(this);
        switchPreferenceSelectTheme.setOnPreferenceChangeListener(this);
    }

    @Override
    public boolean onPreferenceChange(Preference preference, Object newValue) {
        Snackbar snackbar =
                Snackbar.make(getActivity().findViewById(
                        R.id.frameLayout_settingsActivity_mainContainer),
                        R.string.text_snackBar,
                        Snackbar.LENGTH_LONG);
        switch (preference.getKey()) {
            case PREFERENCE_CHANGE_THEME_KEY:
                themeChangesFlag = true;
                snackbar.setAction(R.string.snackBar_button_text, (v) -> {
                    themeChangesFlag = false;
                    if (switchPreferenceSelectTheme.isChecked()) {
                        switchPreferenceSelectTheme.setChecked(false);
                    }
                });
                break;
            case PREFERENCE_SELECT_LANGUAGE_KEY:
                languageChangesFlag = true;
                snackbar.setAction(R.string.snackBar_button_text, (v) -> {
                    languageChangesFlag = false;
                    if (listPreferenceSelectLanguage.getValue().equals(getString(R.string.language_english))) {
                        listPreferenceSelectLanguage.setValue(getString(R.string.language_russian));
                    }
                });
                break;
        }
        snackbar.show();
        return true;
    }


    @Override
    public Map<String, String> sendChanges() {
        if (languageChangesFlag || themeChangesFlag) {
            Map<String, String> settings = new HashMap<>();
            if (languageChangesFlag) {
                settings.put(LANGUAGE_KEY, listPreferenceSelectLanguage.getValue());
            }
            if (themeChangesFlag) {
                if (switchPreferenceSelectTheme.isChecked()) {
                    settings.put(THEME_KEY, "white");
                } else {
                    settings.put(THEME_KEY, "black");
                }
            }
            return settings;
        }
        return null;
    }
}