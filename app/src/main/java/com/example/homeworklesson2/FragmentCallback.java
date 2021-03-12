package com.example.homeworklesson2;

public interface FragmentCallback {
    String getCurrentFragmentTeg();

    void replaceFragment(String tag);

    void setText(String text, String tag);
}
