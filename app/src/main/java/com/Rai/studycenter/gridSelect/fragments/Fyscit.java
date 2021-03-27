package com.Rai.studycenter.gridSelect.fragments;


import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import androidx.fragment.app.Fragment;

import com.Rai.studycenter.R;
import com.Rai.studycenter.adapters.SubjectsAdapter;
import com.Rai.studycenter.firebase.firebaseutils.StartClass;
import com.Rai.studycenter.helpers.Pdf_View;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * A simple {@link Fragment} subclass.
 */
public class Fyscit extends Fragment {
    String[] urlSem1;
    String[] urlSem2;
    StartClass startClass;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        final View view = inflater.inflate(R.layout.fragment_fybscit, container, false);
        addBooks();
        startClass =new StartClass(getActivity());
        ListView listview = view.findViewById(R.id.list);
        ListView listview2 = view.findViewById(R.id.list2);
        ArrayList<String> list = new ArrayList<String>(Arrays.asList(getResources().getStringArray(R.array.Sem1)));
        ArrayList<String> list2 = new ArrayList<String>(Arrays.asList(getResources().getStringArray(R.array.Sem2)));
        listview.setDivider(null);
        listview.setDividerHeight(0);
        listview2.setDivider(null);
        listview2.setDividerHeight(0);
        final SubjectsAdapter adapter = new SubjectsAdapter(list,urlSem1, getContext());

        listview.setAdapter(adapter);
        listview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> parent, View view1, final int position, long id) {
                switch (position) {
                    case 0:
                        startClass.changeActivity(getActivity(),"pdf_path",adapter.getItem(position).toString()+".pdf");
                        break;
                    case 1:
                        startClass.changeActivity(getActivity(),"pdf_path",adapter.getItem(position).toString()+".pdf");
                        break;
                    case 2:
                        startClass.changeActivity(getActivity(),"pdf_path",adapter.getItem(position).toString()+".pdf");
                        break;
                    case 3:
                        startClass.changeActivity(getActivity(),"pdf_path",adapter.getItem(position).toString()+".pdf");
                        break;
                    case 4:
                        startClass.changeActivity(getActivity(),"pdf_path",adapter.getItem(position).toString()+".pdf");
                        break;
                    default:
                        Toast.makeText(getActivity(), "invalid choice", Toast.LENGTH_SHORT).show();
                        break;
                }

            }
        });
        SubjectsAdapter adapter2 = new SubjectsAdapter(list2,urlSem2, getContext());
        listview2.setAdapter(adapter2);
        listview2.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> parent, View view1, final int position, long id) {
                switch (position) {
                    case 0:

                        startClass.changeActivity(getActivity(),"pdf_path",adapter.getItem(position).toString()+".pdf");
                        break;

                    case 1:

                        startClass.changeActivity(getActivity(),"pdf_path",adapter.getItem(position).toString()+".pdf");
                        break;
                    case 2:

                        startClass.changeActivity(getActivity(),"pdf_path",adapter.getItem(position).toString()+".pdf");
                        break;
                    case 3:

                        startClass.changeActivity(getActivity(),"pdf_path",adapter.getItem(position).toString()+".pdf");
                        break;
                    case 4:

                        startClass.changeActivity(getActivity(),"pdf_path",adapter.getItem(position).toString()+".pdf");
                        break;
                    default:
                        Toast.makeText(getActivity(), "invalid choice", Toast.LENGTH_SHORT).show();
                        break;
                }

            }
        });
        return view;

    }

    @Override
    public void onStart() {

        super.onStart();
    }

    void addBooks() {
    urlSem1 = new String[]{
            "http://archive.mu.ac.in/myweb_test/syllFybscit/PCS.pdf",
            "http://archive.mu.ac.in/myweb_test/syllFybscit/PCS.pdf",
            "http://archive.mu.ac.in/myweb_test/syllFybscit/PCS.pdf",
            "http://archive.mu.ac.in/myweb_test/syllFybscit/PCS.pdf",
            "http://archive.mu.ac.in/myweb_test/syllFybscit/PCS.pdf"
    };
        urlSem2 = new String[]{
                "http://archive.mu.ac.in/myweb_test/syllFybscit/PCS.pdf",
                "http://archive.mu.ac.in/myweb_test/syllFybscit/PCS.pdf",
                "http://archive.mu.ac.in/myweb_test/syllFybscit/PCS.pdf",
                "http://archive.mu.ac.in/myweb_test/syllFybscit/PCS.pdf",
                "http://archive.mu.ac.in/myweb_test/syllFybscit/PCS.pdf"
        };
    }

}


