package com.Rai.studycenter.firebase.ui.frags;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.appcompat.widget.AppCompatSpinner;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;
import com.Rai.studycenter.R;
import com.Rai.studycenter.firebase.adapter.localTImeTableHolderLayoutAdapter;
import com.Rai.studycenter.firebase.firebaseutils.StartClass;
import com.Rai.studycenter.firebase.firebaseutils.firebaseMenu;
import com.Rai.studycenter.firebase.models.timetableFormat;
import com.google.android.material.chip.Chip;
import com.google.android.material.chip.ChipGroup;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import java.util.ArrayList;
import java.util.Arrays;

import static com.Rai.studycenter.constant.Constant.firebaseCollegeKey;
import static com.Rai.studycenter.constant.Constant.firebasePref;


public class exams extends Fragment implements firebaseMenu {
    String TAG="exams";
    FirebaseAuth mAuth ;
    String currentId;
    View view;
    Chip addChip;
    ChipGroup semChipGroup;
    FloatingActionButton fab_uploadBTN;
    ArrayList<timetableFormat> putExam=new ArrayList<timetableFormat>();
    RecyclerView timeTableUploadHolder;
    localTImeTableHolderLayoutAdapter adapter;
    StartClass startClass;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view= inflater.inflate(R.layout.fragment_exams, container, false);
        startClass=new StartClass();
        mAuth= FirebaseAuth.getInstance();
        timeTableUploadHolder=view.findViewById(R.id.exam_layout_inflate);
        timeTableUploadHolder.setLayoutManager(new LinearLayoutManager(getContext()));
        fab_uploadBTN=view.findViewById(R.id.fab_add_exam);
        fab_uploadBTN.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
              //upload from here
                adapter.update();
            }
        });
        semChipGroup=view.findViewById(R.id.exam_sems);
        semChipGroup.setSingleSelection(true);
        addChipNames();
        return view;
    }

    @Override
    public void getAuth() {

    }

    public void checkCollegeID() {

        if(startClass.getUniqueID(getActivity()).equals("not found")){
            currentId=null;

        }
        else {
            currentId=startClass.getUniqueID(getActivity());

        }
        Log.d(TAG, "checkCollegeID() returned: " +currentId );
    }

    @Override
    public void onStart() {
        super.onStart();
        checkCollegeID();
    }
    public  void setSem(String pItem,ChipGroup pChipGroup){
        Chip lChip = (Chip) this.getLayoutInflater().inflate(R.layout.chip, null, false);
        lChip.setText(pItem);

        pChipGroup.addView(lChip, pChipGroup.getChildCount() - 1);
        pChipGroup.setOnCheckedChangeListener(new ChipGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(ChipGroup group, int checkedId) {
                addChip=view.findViewById(checkedId);
                if(addChip!=null){
                    String name=addChip.getText().toString();
                    populateTimeTable(getSemArray(name),name);
                }
               else {
                    Toast.makeText(getContext(), "Value unchecked", Toast.LENGTH_SHORT).show();
                }

            }
        });
    }
    public void addChipNames(){

        //String names[]={"Sem 6","Sem 5","Sem 4","Sem 3","Sem 2","Sem 1"};
        ArrayList<String> names=new ArrayList();
        names.add("Sem 6");
        names.add("Sem 1");
        names.add("Sem 2");
        names.add("Sem 3");
        names.add("Sem 4");
        names.add("Sem 5");

        for(int i=0;i<names.size();i++){
            setSem(names.get(i),semChipGroup);
            Log.d(TAG, "addChipNames() returned: " + names.get(i));
        }
    }
    public ArrayList<String> getSemArray(String name){
        ArrayList<String> sem = null;
        if(name.equals("Sem 1")){
            sem =new ArrayList<String>(Arrays.asList(getResources().getStringArray(R.array.Sem1)));
        }
        else if(name.equals("Sem 2")){
            sem=new ArrayList<String>(Arrays.asList(getResources().getStringArray(R.array.Sem2)));
        }
        else if(name.equals("Sem 3")){
            sem=new ArrayList<String>(Arrays.asList(getResources().getStringArray(R.array.Sem3)));
        }
        else if(name.equals("Sem 4")){
            sem=new ArrayList<String>(Arrays.asList(getResources().getStringArray(R.array.Sem4)));
        }
        else if(name.equals("Sem 5")){
            sem=new ArrayList<String>(Arrays.asList(getResources().getStringArray(R.array.Sem5)));
        }
        else if(name.equals("Sem 6")){
            sem=new ArrayList<String>(Arrays.asList(getResources().getStringArray(R.array.Sem6)));
        }
        else {
            sem= new ArrayList<String>(Arrays.asList(getResources().getStringArray(R.array.Sem1)));
        }
        return sem;
    }
    public  void populateTimeTable(ArrayList<String> subjects,String currentSem){
        adapter =new localTImeTableHolderLayoutAdapter(getContext(),subjects,currentId,currentSem);
        timeTableUploadHolder.setAdapter(adapter);

    }

    void addExams(String day, String date, String time, String subject){
        
        Log.d(TAG, "addExams() returned: " + putExam.toString());

    }
}