package com.example.homeworklesson2.fragment;


import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.homeworklesson2.FragmentCallback;
import com.example.homeworklesson2.R;

public class FirstFragment extends Fragment implements View.OnClickListener {
    public static final String TAG = "FirstFragmentTag";
    private EditText editText;
    public static final String MESSAGE_KEY = "com.example.homeworklesson2.fragment.firstFragment.MESSAGE";

    public FirstFragment() {
        super(R.layout.fragment_first);
    }

    public static Fragment newInstance(String message) {
        Fragment fragment = new FirstFragment();
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
            TextView textView = getActivity().findViewById(R.id.textView_fragmentFirst);
            textView.setText(requireArguments().getString(MESSAGE_KEY));
        }
        editText = getActivity().findViewById(R.id.editText_fragmentFirst);
        getActivity().findViewById(R.id.button_fragmentFirst_sendMessage).setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        FragmentCallback fragmentCallback = (FragmentCallback) getParentFragment();
        fragmentCallback.setText(editText.getText().toString(), getTag());
    }
}
