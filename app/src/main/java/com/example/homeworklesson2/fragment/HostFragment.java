package com.example.homeworklesson2.fragment;

import android.os.Bundle;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import com.example.homeworklesson2.FragmentCallback;
import com.example.homeworklesson2.R;

public class HostFragment extends Fragment implements FragmentCallback {
    public static final String TAG = "HostFragmentTag";
    // private final FragmentManager fragmentManager = getChildFragmentManager(); // почему не работает?
    private String currentFragmentTag;

    public HostFragment() {
        super(R.layout.fragment_host);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        if (savedInstanceState == null) {
            setFragment(new FirstFragment(), FirstFragment.TAG);
        }
    }

    @Override
    public void replaceFragment(String tag) {
        if (tag.equals(currentFragmentTag)) {
            return;
        }
        if (tag.equals(FirstFragment.TAG)) {
            setFragment(new FirstFragment(), FirstFragment.TAG);
        }
        if (tag.equals(SecondFragment.TAG)) {
            setFragment(new SecondFragment(), SecondFragment.TAG);
        }

    }

    @Override
    public void setText(String message, String tag) {
        if (tag.equals(FirstFragment.TAG)) {
            Fragment fragment = SecondFragment.newInstance(message);
            setFragment(fragment, SecondFragment.TAG);
        }
        if (tag.equals(SecondFragment.TAG)) {
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

}