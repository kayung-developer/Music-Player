package com.slogan.musicplayer.ui.fragments.mainactivity;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import com.slogan.musicplayer.ui.activities.MainActivity;

public abstract class AbsMainActivityFragment extends Fragment {

    public MainActivity getMainActivity() {
        return (MainActivity) getActivity();
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        setHasOptionsMenu(true);
    }
}
