package com.example.homeworklesson2.fragment;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.example.homeworklesson2.FragmentCallback;
import com.example.homeworklesson2.R;


public class SecondFragment extends Fragment implements View.OnClickListener {

    public static final String TAG = "SecondFragmentTag";
    private EditText editTextSendMessage;
    private static final String MESSAGE_KEY = "com.example.homeworklesson2.fragment.secondFragmentMESSAGE";

    public SecondFragment() {
        super(R.layout.fragment_second);
    }

    public static Fragment newInstance(String message) {
        Fragment fragment = new SecondFragment();
        Bundle args = new Bundle();
        args.putString(MESSAGE_KEY, message);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        Bundle args = getArguments();
        if (args != null && args.containsKey(MESSAGE_KEY)) {
            TextView textView = view.findViewById(R.id.textView_fragmentSecond);
            textView.setText(requireArguments().getString(MESSAGE_KEY));
        }
        editTextSendMessage = view.findViewById(R.id.editText_fragmentSecond);
        view.findViewById(R.id.button_fragmentSecond_sendMesage).setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        FragmentCallback fragmentCallback = (FragmentCallback) getParentFragment();
        fragmentCallback.setText(editTextSendMessage.getText().toString(), getTag());
    }
}