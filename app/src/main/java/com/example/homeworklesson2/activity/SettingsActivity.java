package com.example.homeworklesson2.activity;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;

import com.example.homeworklesson2.R;
import com.example.homeworklesson2.SendChanges;
import com.example.homeworklesson2.SendMessage;
import com.example.homeworklesson2.fragment.SettingsFragment;

import java.util.Map;

public class SettingsActivity extends AppCompatActivity implements SendChanges {

    private Map<String, String> settings;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);
        if (savedInstanceState == null) {
            getSupportFragmentManager().
                    beginTransaction()
                    .setReorderingAllowed(true)
                    .add(R.id.frameLayout_settingsActivity_mainContainer, new SettingsFragment(), SettingsFragment.TAG)
                    .commit();
        }
    }

    @Override
    public void onBackPressed() {
        System.out.println("onBackPressed()");
        if (settings != null) {
            Intent intent = new Intent();
            for (String key : settings.keySet()) {
                intent.putExtra(key, settings.get(key));
            }
            setResult(RESULT_OK, intent);
        }
        super.onBackPressed();
    }

    @Override
    protected void onStop() {
        System.out.println("onStop");
        if (settings != null) {
            Intent intent = new Intent();
            for (String key : settings.keySet()) {
                intent.putExtra(key, settings.get(key));
            }
            setResult(RESULT_OK, intent);
        }
        super.onBackPressed();
        super.onStop();
    }

    @Override
    public void sendChanges(Map<String, String> settings) {
        if (settings != null) {
            this.settings = settings;
        }
    }
}