package com.example.homeworklesson2.activity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.FragmentManager;

import com.example.homeworklesson2.FragmentCallback;
import com.example.homeworklesson2.R;
import com.example.homeworklesson2.fragment.FirstFragment;
import com.example.homeworklesson2.fragment.HostFragment;
import com.example.homeworklesson2.fragment.SecondFragment;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {


    @SuppressLint("ResourceAsColor")
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

    @SuppressLint("NonConstantResourceId")
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.settings:
                Intent intent = new Intent(this, SettingsActivity.class);
                startActivity(intent);
                break;
            case R.id.about:

        }
        return super.onOptionsItemSelected(item);
    }

    @SuppressLint("NonConstantResourceId") // зачем эта анотация?
    @Override
    public void onClick(View v) {
        FragmentCallback fragmentCallback = (FragmentCallback) getSupportFragmentManager().findFragmentByTag(HostFragment.TAG);
        if (v.getId() == R.id.button_fragment_first_show) {
            fragmentCallback.replaceFragment(FirstFragment.TAG);
        } else {
            fragmentCallback.replaceFragment(SecondFragment.TAG);
        }
    }
}