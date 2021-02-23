package com.example.homeworklesson2.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

import com.example.homeworklesson2.R;
import com.example.homeworklesson2.fragment.SettingsFragment;

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
        //если передавать данные отсюда в маёне всё видно
//        Intent intent = new Intent();
//        intent.putExtra(SettingsFragment.MESSAGE_KEY, "text");
//        setResult(RESULT_OK, intent);

    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        Intent intent = new Intent();
        intent.putExtra(SettingsFragment.MESSAGE_KEY, "text");
        setResult(RESULT_OK, intent);
    }


}