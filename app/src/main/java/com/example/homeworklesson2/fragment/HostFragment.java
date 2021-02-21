package com.example.homeworklesson2.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.homeworklesson2.FragmentCallback;
import com.example.homeworklesson2.R;


public class HostFragment extends Fragment implements FragmentCallback {

    private TextView textView;

    public HostFragment() {
        super(R.layout.fragment_host);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
       // textView = view.findViewById(R.id.)
    }

    @Override
    public void passData(String data) {
        textView.setText(data);
    }
}