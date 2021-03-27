package com.Rai.studycenter.firebase.ui.frags;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.Rai.studycenter.R;


public class mcqFragment extends Fragment {
    public String strSeparator="__,__";
    public String TAG="MCQFRAGMENT";
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_mcq, container, false);

        return view;
    }

    public  String convertArrayToString(String[] array){
        String str = "";
        for (int i = 0;i<array.length; i++) {
            str = str+array[i];
            if(i<array.length-1){
                str = str+strSeparator;
            }
        }
        return str;
    }
    public  String[] convertStringToArray(String str){
        String[] arr = str.split(strSeparator);
        return arr;
    }
}