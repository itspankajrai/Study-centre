package com.Rai.studycenter.firebase.ui;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.Rai.studycenter.R;
import com.Rai.studycenter.firebase.adapter.firebaseDBAdapter;
import com.Rai.studycenter.firebase.firebaseutils.StartClass;
import com.Rai.studycenter.firebase.login.Login;
import com.Rai.studycenter.firebase.uploads.upload;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import static com.Rai.studycenter.constant.Constant.CollegeKey;
import static com.Rai.studycenter.constant.Constant.SharedPref;
import static com.Rai.studycenter.constant.Constant.firebaseCollegeKey;
import static com.Rai.studycenter.constant.Constant.firebasePref;

public class Firebase_StudyMaterial extends AppCompatActivity {
    firebaseDBAdapter firebaseDbAdapter;
    RecyclerView firebaseRecyclerView;
    final FirebaseDatabase database = FirebaseDatabase.getInstance();
    DatabaseReference ref = database.getReference("users");
    String fromDeepLink="";
    String DbId;
    StartClass startClass;
    private FirebaseAuth mAuth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_firebase_study_material);
        startClass=new StartClass();
        mAuth=FirebaseAuth.getInstance();
        Intent intent=getIntent();
        Uri data = intent.getData();
        if(data!=null){
         fromDeepLink=data.getQueryParameter("key");
        }
            if(fromDeepLink.equals("")){
                DbId=startClass.getUniqueID(Firebase_StudyMaterial.this);
            }
            else {
                DbId=fromDeepLink;
            }


        firebaseRecyclerView=findViewById(R.id.firebaseRecyclerView);
        firebaseRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        FirebaseRecyclerOptions<upload> options=
                new FirebaseRecyclerOptions.Builder<upload>()
                        .setQuery(ref.child(DbId).orderByChild("unique_ID"),upload.class)
                        .build();
        firebaseDbAdapter =new firebaseDBAdapter(options);
        firebaseRecyclerView.setAdapter(firebaseDbAdapter);

    }
    @Override
    public void onStart(){
        logIn();
        firebaseDbAdapter.notifyDataSetChanged();
        firebaseDbAdapter.startListening();
        super.onStart();


    }
    @Override
    public void onStop(){
        firebaseDbAdapter.stopListening();
        super.onStop();
    }
    void logIn(){
        if(mAuth.getCurrentUser()==null){
            Intent intent=new Intent(Firebase_StudyMaterial.this, Login.class);
            startActivity(intent);
        }
        else {
            Toast.makeText(this, "Welcome "+mAuth.getCurrentUser().getDisplayName(), Toast.LENGTH_SHORT).show();
        }
    }
}