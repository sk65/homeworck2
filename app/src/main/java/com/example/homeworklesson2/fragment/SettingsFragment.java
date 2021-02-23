package com.example.homeworklesson2.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.preference.ListPreference;
import androidx.preference.Preference;
import androidx.preference.PreferenceFragmentCompat;

import com.example.homeworklesson2.R;
import com.google.android.material.snackbar.Snackbar;

import static android.app.Activity.RESULT_OK;

public class SettingsFragment extends PreferenceFragmentCompat implements Preference.OnPreferenceChangeListener, View.OnClickListener {
    public static final String TAG = "SettingsFragmentTag";
    private boolean flag;
    private ListPreference listPreferenceSelectLanguage;
    public static final String MESSAGE_KEY = "com.example.homeworklesson2.fragment.settingsFragment.MESSAGE";
    private final String PREFERENCE_SELECT_LANGUAGE_KEY = "list_preference_selectLanguage";
    private final String PREFERENCE_CHANGE_THEME_KEY = "switchPreference_changeTheme";

    @Override
    public void onCreatePreferences(Bundle savedInstanceState, String rootKey) {
        setPreferencesFromResource(R.xml.root_preferences, rootKey);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        listPreferenceSelectLanguage = findPreference(PREFERENCE_SELECT_LANGUAGE_KEY);
        if (listPreferenceSelectLanguage != null) {
            listPreferenceSelectLanguage.setOnPreferenceChangeListener(this);
        }
    }

    @Override
    public boolean onPreferenceChange(Preference preference, Object newValue) {
        Snackbar snackbar =
                Snackbar.make(getActivity().findViewById(
                        R.id.frameLayout_settingsActivity_mainContainer),
                        R.string.text_snackBar,
                        Snackbar.LENGTH_LONG);
        snackbar.setAction(R.string.snackBar_button_text, this);
        snackbar.show();
        return true;
    }

    @Override
    public void onClick(View v) {
        Intent intent = new Intent();
        intent.putExtra(MESSAGE_KEY, listPreferenceSelectLanguage.getValue());
        getActivity().setResult(RESULT_OK, intent);

        if (listPreferenceSelectLanguage.getValue().equals("English")) {
            listPreferenceSelectLanguage.setValue("Russian");
        }

    }

}