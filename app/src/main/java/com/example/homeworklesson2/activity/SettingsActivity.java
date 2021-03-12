package com.example.homeworklesson2.activity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import com.example.homeworklesson2.R;
import com.example.homeworklesson2.SendChanges;
import com.example.homeworklesson2.fragment.SettingsFragment;

import java.util.Map;

import static com.example.homeworklesson2.activity.MainActivity.DEV_TEG;

public class SettingsActivity extends AppCompatActivity {

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
        Fragment fragment = getSupportFragmentManager().findFragmentByTag(SettingsFragment.TAG);
        SendChanges sendChanges = (SendChanges) fragment;
        if (sendChanges != null) {
            Map<String, String> settings = sendChanges.sendChanges();
            Log.i(DEV_TEG, "SettingsActivity onBackPressed " + settings.toString());
            Intent intent = new Intent();
            for (String key : settings.keySet()) {
                intent.putExtra(key, settings.get(key));
            }
            Log.i(DEV_TEG, "SettingsActivity onBackPressed " + intent.getExtras().toString());
            setResult(RESULT_OK, intent);
        }
        finish();
        super.onBackPressed();
    }

}