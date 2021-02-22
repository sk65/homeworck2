package com.example.homeworklesson2.activity;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;

import com.example.homeworklesson2.FragmentCallback;
import com.example.homeworklesson2.R;
import com.example.homeworklesson2.fragment.FirstFragment;
import com.example.homeworklesson2.fragment.HostFragment;
import com.example.homeworklesson2.fragment.SecondFragment;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    private final FragmentManager fragmentManager = getSupportFragmentManager();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if (savedInstanceState == null) {
            fragmentManager.
                    beginTransaction()
                    .setReorderingAllowed(true)
                    .add(R.id.frameLayout_acMain_fragmentContainer, new HostFragment(),HostFragment.TAG)
                    .commit();
        }
        findViewById(R.id.button_fragment_first_show).setOnClickListener(this);
        findViewById(R.id.button_fragment_second_show).setOnClickListener(this);

    }



    @SuppressLint("NonConstantResourceId") // зачем эта анотация?
    @Override
    public void onClick(View v) {
        FragmentCallback fragmentCallback = (FragmentCallback) fragmentManager.findFragmentByTag(HostFragment.TAG);
        if (v.getId() == R.id.button_fragment_first_show) {
            fragmentCallback.replaceFragment(FirstFragment.TAG);
        } else {
            fragmentCallback.replaceFragment(SecondFragment.TAG);
        }

    }

}