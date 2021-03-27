package com.Rai.studycenter.gridSelect.fragments;



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
import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;


public class Tybscit extends Fragment {

    String[] urlSem5;
    String[] urlSem6;
    StartClass startClass;
    File checkF ;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_tybscit, container, false);
        addBooks();
        startClass=new StartClass(getActivity());
        ArrayList<String> list = new ArrayList<String>(Arrays.asList(getResources().getStringArray(R.array.Sem5)));
        ArrayList<String> list2 = new ArrayList<String>(Arrays.asList(getResources().getStringArray(R.array.Sem6)));

        //instantiate custom adapter
        SubjectsAdapter adapter = new SubjectsAdapter(list,urlSem5, getContext());

        //handle listview and assign adapter
        ListView lView = (ListView)view.findViewById(R.id.list5);
        ListView listview2 =(ListView)view.findViewById(R.id.list6);
        lView.setDivider(null);
        lView.setDividerHeight(0);
        listview2.setDivider(null);
        listview2.setDividerHeight(0);
        lView.setAdapter(adapter);
        lView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> parent, View view1,final int position, long id) {
                switch(position)
                {
                    case 0:
                        startClass.changeActivity(getActivity(),"pdf_path","Core_java.pdf");
                        break;
                    case 1:
                        startClass.changeActivity(getActivity(),"pdf_path","Embedded_Systems.pdf");
                        break;
                    case 2:
                        startClass.changeActivity(getActivity(),"pdf_path","Statistical_Techniques.pdf");

                        break;
                    case 3:
                        startClass.changeActivity(getActivity(),"pdf_path","Software_Engineering.pdf");
                        break;
                    case 4:
                        startClass.changeActivity(getActivity(),"pdf_path","Computer_Graphics.pdf");
                        break;
                    default:
                        Toast.makeText(getActivity(),"invalid choice",Toast.LENGTH_SHORT).show();
                        break;
                }

            }
        });
        SubjectsAdapter adapter2 = new SubjectsAdapter(list2,urlSem6, getContext());
        listview2.setAdapter(adapter2);
        listview2.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> parent, View view1,final int position, long id) {
                switch(position)
                {
                    case 0:
                        startClass.changeActivity(getActivity(),"pdf_path","Core_java.pdf");
                        break;
                    case 1:
                        startClass.changeActivity(getActivity(),"pdf_path","Embedded_Systems.pdf");
                        break;
                    case 2:
                        startClass.changeActivity(getActivity(),"pdf_path","Statistical_Techniques.pdf");
                        break;
                    case 3:
                        startClass.changeActivity(getActivity(),"pdf_path","Software_Engineering.pdf");
                        break;
                    case 4:
                        startClass.changeActivity(getActivity(),"pdf_path","Computer_Graphics.pdf");
                        break;
                    default:
                        Toast.makeText(getActivity(),"invalid choice",Toast.LENGTH_SHORT).show();
                        break;
                }

            }
        });
         return view;
    }
    @Override
    public void onStart() {
        addBooks();
        super.onStart();
    }

    void addBooks() {
        urlSem5 = new String[]{
                "http://archive.mu.ac.in/myweb_test/syllFybscit/PCS.pdf",
                "http://archive.mu.ac.in/myweb_test/syllFybscit/PCS.pdf",
                "http://archive.mu.ac.in/myweb_test/syllFybscit/PCS.pdf",
                "http://archive.mu.ac.in/myweb_test/syllFybscit/PCS.pdf",
                "http://archive.mu.ac.in/myweb_test/syllFybscit/PCS.pdf",
                "http://archive.mu.ac.in/myweb_test/syllFybscit/PCS.pdf"

        };
        urlSem6 = new String[]{
                "http://archive.mu.ac.in/myweb_test/syllFybscit/PCS.pdf",
                "http://archive.mu.ac.in/myweb_test/syllFybscit/PCS.pdf",
                "http://archive.mu.ac.in/myweb_test/syllFybscit/PCS.pdf",
                "http://archive.mu.ac.in/myweb_test/syllFybscit/PCS.pdf",
                "http://archive.mu.ac.in/myweb_test/syllFybscit/PCS.pdf",
                "http://archive.mu.ac.in/myweb_test/syllFybscit/PCS.pdf",
                "http://archive.mu.ac.in/myweb_test/syllFybscit/PCS.pdf",
                "http://archive.mu.ac.in/myweb_test/syllFybscit/PCS.pdf"
        };
    }
}
