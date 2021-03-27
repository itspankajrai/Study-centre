package com.Rai.studycenter.firebase.ui;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;

import com.Rai.studycenter.R;
import com.Rai.studycenter.firebase.adapter.firebaseTimeTableAdapter;
import com.Rai.studycenter.firebase.firebaseutils.StartClass;
import com.Rai.studycenter.firebase.firebaseutils.start_interface;
import com.Rai.studycenter.firebase.models.timetableFormat;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import static com.Rai.studycenter.constant.Constant.firebaseCollegeKey;
import static com.Rai.studycenter.constant.Constant.firebasePref;

public class TimeTableFirebaseActivity extends AppCompatActivity  {
        RecyclerView timeTableRecycler;
        firebaseTimeTableAdapter adapter;
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference ref = database.getReference("timetable");
        StartClass startClass;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_time_table_firebase);
        startClass =new StartClass();
        timeTableRecycler=findViewById(R.id.firebaseTimeTable);
        timeTableRecycler.setLayoutManager(new LinearLayoutManager(this));
        FirebaseRecyclerOptions<timetableFormat> options=
                new FirebaseRecyclerOptions.Builder<timetableFormat>()
                        .setQuery(ref.child(startClass.getUniqueID(TimeTableFirebaseActivity.this)).child("Sem 1").orderByChild("date"),timetableFormat.class)
                        .build();
        adapter=new firebaseTimeTableAdapter(options);
        timeTableRecycler.setAdapter(adapter);
    }
    @Override
    public void onStart(){
        super.onStart();
        adapter.notifyDataSetChanged();
        adapter.startListening();
    }
    @Override
    public void onStop(){
        super.onStop();
        adapter.stopListening();
    }

}