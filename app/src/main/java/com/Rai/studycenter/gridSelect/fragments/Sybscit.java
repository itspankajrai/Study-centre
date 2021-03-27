package com.Rai.studycenter.gridSelect.fragments;


import android.content.Intent;
import android.os.Bundle;
import android.os.Environment;
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

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;

/**
 * A simple {@link Fragment} subclass.
 */
public class Sybscit extends Fragment {

    String[] urlSem3;
    String[] urlSem4;
    StartClass startClass;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        final View view = inflater.inflate(R.layout.fragment_sybscit, container, false);
        //generate list
        addBooks();
        startClass=new StartClass(getActivity());
        ArrayList<String> list = new ArrayList<String>(Arrays.asList(getResources().getStringArray(R.array.Sem3)));
        ArrayList<String> list2 = new ArrayList<String>(Arrays.asList(getResources().getStringArray(R.array.Sem4)));

        //instantiate custom adapter
        final SubjectsAdapter adapter = new SubjectsAdapter(list,urlSem3, getContext());

        //handle listview and assign adapter
        ListView lView = (ListView)view.findViewById(R.id.list3);
        ListView listview2 =(ListView)view.findViewById(R.id.list4);
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
                        Toast.makeText(getActivity(),"Opening\nPython Programming",Toast.LENGTH_SHORT).show();
                        startClass.changeActivity(getActivity(),"pdf_path",adapter.getItem(position).toString()+".pdf");
                        break;

                    case 1:
                        Toast.makeText(getActivity(),"Opening \nData Structures",Toast.LENGTH_SHORT).show();
                        startClass.changeActivity(getActivity(),"pdf_path",adapter.getItem(position).toString()+".pdf");
                        break;
                    case 2:
                        startClass.changeActivity(getActivity(),"pdf_path",adapter.getItem(position).toString()+".pdf");
                        break;
                    case 3:
                        Toast.makeText(getActivity(),"Opening \nDatabase Management Systems",Toast.LENGTH_SHORT).show();
                        startClass.changeActivity(getActivity(),"pdf_path",adapter.getItem(position).toString()+".pdf");
                        break;
                    case 4:
                        Toast.makeText(getActivity(),"Opening \nApplied Mathematics",Toast.LENGTH_SHORT).show();
                        startClass.changeActivity(getActivity(),"pdf_path",adapter.getItem(position).toString()+".pdf");
                        break;
                    default:
                        Toast.makeText(getActivity(),"invalid choice",Toast.LENGTH_SHORT).show();
                        break;
                }

            }
        });
        final SubjectsAdapter adapter2 = new SubjectsAdapter(list2,urlSem4, getContext());
        listview2.setAdapter(adapter2);
        listview2.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> parent, View view1,final int position, long id) {
                switch(position)
                {
                    case 0:
                        startClass.changeActivity(getActivity(),"pdf_path",adapter2.getItem(position).toString()+".pdf");
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
                        Toast.makeText(getActivity(),"invalid choice",Toast.LENGTH_SHORT).show();
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
        urlSem3 = new String[]{
                "http://archive.mu.ac.in/myweb_test/syllFybscit/PCS.pdf",
                "http://archive.mu.ac.in/myweb_test/syllFybscit/PCS.pdf",
                "http://archive.mu.ac.in/myweb_test/syllFybscit/PCS.pdf",
                "http://archive.mu.ac.in/myweb_test/syllFybscit/PCS.pdf",
                "http://archive.mu.ac.in/myweb_test/syllFybscit/PCS.pdf"
        };
        urlSem4 = new String[]{
                "http://archive.mu.ac.in/myweb_test/syllFybscit/PCS.pdf",
                "http://archive.mu.ac.in/myweb_test/syllFybscit/PCS.pdf",
                "http://archive.mu.ac.in/myweb_test/syllFybscit/PCS.pdf",
                "http://archive.mu.ac.in/myweb_test/syllFybscit/PCS.pdf",
                "http://archive.mu.ac.in/myweb_test/syllFybscit/PCS.pdf"
        };
    }
}
