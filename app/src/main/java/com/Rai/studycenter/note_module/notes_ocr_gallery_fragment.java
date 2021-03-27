package com.Rai.studycenter.note_module;


import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.Rai.studycenter.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class notes_ocr_gallery_fragment extends Fragment {


    public notes_ocr_gallery_fragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_notes_ocr_gallery, container, false);
    }

}
