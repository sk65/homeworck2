package com.example.homeworklesson2.activity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;

import com.example.homeworklesson2.FragmentCallback;
import com.example.homeworklesson2.R;
import com.example.homeworklesson2.SendMessage;
import com.example.homeworklesson2.fragment.DialogFragment;
import com.example.homeworklesson2.fragment.FirstFragment;
import com.example.homeworklesson2.fragment.HostFragment;
import com.example.homeworklesson2.fragment.SecondFragment;
import com.example.homeworklesson2.fragment.SettingsFragment;

import java.util.List;
import java.util.Map;

public class MainActivity extends AppCompatActivity implements View.OnClickListener, SendMessage {
    public static final String DEV_TEG = "DEV";
    private AlertDialog alertDialog;


    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar_acMain);
        setSupportActionBar(toolbar);

        if (savedInstanceState == null) {
            getSupportFragmentManager().
                    beginTransaction()
                    .setReorderingAllowed(true)
                    .add(R.id.frameLayout_acMain_fragmentContainer, new HostFragment(), HostFragment.TAG)
                    .commit();
        }


        findViewById(R.id.button_fragment_first_show).setOnClickListener(this);
        findViewById(R.id.button_fragment_second_show).setOnClickListener(this);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.settings:
                Intent intent = new Intent(this, SettingsActivity.class);
                startActivityForResult(intent, 1);
                break;
            case R.id.about:
                new DialogFragment().show(getSupportFragmentManager(), DialogFragment.TAG);
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onClick(View v) {
        FragmentCallback fragmentCallback = (FragmentCallback) getSupportFragmentManager().findFragmentByTag(HostFragment.TAG);
        if (fragmentCallback != null) {
            if (v.getId() == R.id.button_fragment_first_show) {
                fragmentCallback.replaceFragment(FirstFragment.TAG);
            } else {
                fragmentCallback.replaceFragment(SecondFragment.TAG);
            }
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (data == null) {
            return;
        }
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        Log.i(DEV_TEG, "onActivityResult " + data.getExtras().toString());
        builder.setTitle(R.string.alertDialog_mainActivity_title);
        StringBuilder messageBuilder = new StringBuilder();
        if (data.hasExtra(SettingsFragment.LANGUAGE_KEY)) {
            messageBuilder.append(SettingsFragment.LANGUAGE_KEY + " ").append(data.getStringExtra(SettingsFragment.LANGUAGE_KEY));
        }
        if (data.hasExtra(SettingsFragment.THEME_KEY)) {
            if (messageBuilder.length() != 0) {
                messageBuilder.append("\n");
            }
            messageBuilder.append(SettingsFragment.THEME_KEY + " ").append(data.getStringExtra(SettingsFragment.THEME_KEY));
        }
        builder.setMessage(messageBuilder.toString());
        builder.setNegativeButton("OK", (dialog, which) -> alertDialog.hide());
        alertDialog = builder.create();
        alertDialog.show();
    }

    @Override
    public void sendMessage(String message) {
        Toast.makeText(this, message, Toast.LENGTH_LONG).show();
    }
}