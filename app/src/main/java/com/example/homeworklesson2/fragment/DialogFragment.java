package com.example.homeworklesson2.fragment;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import com.example.homeworklesson2.R;
import com.example.homeworklesson2.SendMessage;

public class DialogFragment extends androidx.fragment.app.DialogFragment implements View.OnClickListener {
    public static final String TAG = "DialogWindowFragment";
    private EditText editText;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_dialog_window, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        editText = view.findViewById(R.id.editText_dialogWindowFragment);
        view.findViewById(R.id.button_dialogWindowFragment_sendMessage).setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        if (editText.getText().toString().isEmpty()) {
            return;
        }
        SendMessage sendData = (SendMessage) getActivity();
        sendData.sendMessage(editText.getText().toString());
        dismiss();
    }
}