package com.Rai.studycenter.settings.fragments;


import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.preference.PreferenceFragmentCompat;

import com.Rai.studycenter.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class Support extends PreferenceFragmentCompat {

    public static final String TAG = "Support";

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        addPreferencesFromResource(R.xml.support_preferences);
    }

    @Override
    public void onCreatePreferences(Bundle savedInstanceState, String rootKey) {

    }

}
