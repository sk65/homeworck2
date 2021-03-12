package com.example.homeworklesson2.fragment;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.homeworklesson2.FragmentCallback;
import com.example.homeworklesson2.R;

public class HostFragment extends Fragment implements FragmentCallback {

    public static final String TAG = "HostFragmentTag";
    private String currentFragmentTag;
    private final String PREF_KEY = "PrefKey";
    private final String LAST_OPEN_FRAGMENT_TAG = "lastOpenFragmentTag";
    private SharedPreferences sharedPreferences;

    public HostFragment() {
        super(R.layout.fragment_host);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        sharedPreferences = getActivity().getSharedPreferences(PREF_KEY, Context.MODE_PRIVATE);
        String lastFragmentTag = sharedPreferences.getString(LAST_OPEN_FRAGMENT_TAG, FirstFragment.TAG);

        if (savedInstanceState == null) {
            if (lastFragmentTag.equals(FirstFragment.TAG)) {
                setFragment(new FirstFragment(), FirstFragment.TAG);
                currentFragmentTag = FirstFragment.TAG;
            } else {
                setFragment(new SecondFragment(), SecondFragment.TAG);
                currentFragmentTag = SecondFragment.TAG;
            }
        }
        return super.onCreateView(inflater, container, savedInstanceState);
    }

    @Override
    public String getCurrentFragmentTeg() {
        return currentFragmentTag;
    }

    @Override
    public void replaceFragment(String tag) {
        if (tag.equals(currentFragmentTag)) {
            return;
        }
        if (tag.equals(FirstFragment.TAG)) {
            setFragment(new FirstFragment(), FirstFragment.TAG);
            currentFragmentTag = FirstFragment.TAG;
        } else {
            setFragment(new SecondFragment(), SecondFragment.TAG);
            currentFragmentTag = SecondFragment.TAG;
        }
    }

    @Override
    public void setText(String message, String tag) {
        if (tag.equals(FirstFragment.TAG)) {
            Fragment fragment = SecondFragment.newInstance(message);
            setFragment(fragment, SecondFragment.TAG);
        } else {
            Fragment fragment = FirstFragment.newInstance(message);
            setFragment(fragment, FirstFragment.TAG);
        }
    }

    private void setFragment(Fragment fragment, String tag) {
        getChildFragmentManager()
                .beginTransaction()
                .setReorderingAllowed(true)
                .replace(R.id.fragment_host, fragment, tag)
                .commit();
        currentFragmentTag = tag;
    }

    @Override
    public void onStop() {
        saveCurrentFragmentTag();
        super.onStop();
    }

    private void saveCurrentFragmentTag() {
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString(LAST_OPEN_FRAGMENT_TAG, currentFragmentTag);
        editor.apply();
    }
}