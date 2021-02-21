package com.example.homeworklesson2.activity;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import com.example.homeworklesson2.R;
import com.example.homeworklesson2.fragment.FirstFragment;
import com.example.homeworklesson2.fragment.HostFragment;
import com.example.homeworklesson2.fragment.SecondFragment;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    interface Test {
        void onSendData(String data);
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if (savedInstanceState == null) {
            getSupportFragmentManager().
                    beginTransaction()
                    .setReorderingAllowed(true)
                    .replace(R.id.frameLayout_acMain_fragmentContainer, new HostFragment())
                    .replace(R.id.fragment_host, new FirstFragment())
                    .commit();
        }
        findViewById(R.id.button_fragment_first_show).setOnClickListener(this);
        findViewById(R.id.button_fragment_second_show).setOnClickListener(this);

    }

    @SuppressLint("NonConstantResourceId") // зачем эта анотация?
    @Override
    public void onClick(View v) {
        Fragment fragment = new FirstFragment();
        if (R.id.button_fragment_second_show == v.getId()) {
            fragment = new SecondFragment();
        }
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.fragment_host, fragment)
                .setReorderingAllowed(true)
                .commit();
    }
}