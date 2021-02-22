package com.example.homeworklesson2.fragment;

import android.os.Bundle;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.preference.ListPreference;
import androidx.preference.Preference;
import androidx.preference.PreferenceFragmentCompat;

import com.example.homeworklesson2.R;
import com.google.android.material.snackbar.Snackbar;

public class SettingsFragment extends PreferenceFragmentCompat implements Preference.OnPreferenceChangeListener, View.OnClickListener {
    public static final String TAG = "SettingsFragmentTag";
    private Snackbar snackbar;
    private boolean flag;
    private final String PREFERENCE_SELECT_LANGUAGE_KEY = "list_preference_selectLanguage";
    private final String PREFERENCE_CHANGE_THEME_KEY = "switchPreference_changeTheme";

    @Override
    public void onCreatePreferences(Bundle savedInstanceState, String rootKey) {
        setPreferencesFromResource(R.xml.root_preferences, rootKey);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        ListPreference listPreferenceSelectLanguage = findPreference(PREFERENCE_SELECT_LANGUAGE_KEY);
        if (listPreferenceSelectLanguage != null) {
            listPreferenceSelectLanguage.setOnPreferenceChangeListener(this);
        }
    }

    @Override
    public boolean onPreferenceChange(Preference preference, Object newValue) {
        Snackbar snackbar =
                Snackbar.make(getActivity().findViewById(
                        R.id.frameLayout_settingsActivity_mainContainer),
                        "This is a SnackBar",
                        Snackbar.LENGTH_LONG);
        snackbar.setAction("cancel", this
        );
        snackbar.show();
        return flag;

    }

    private void showSnackBar() {

    }

    @Override
    public void onClick(View v) {
        flag = true;
    }
}