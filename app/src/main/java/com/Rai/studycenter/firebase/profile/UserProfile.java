package com.Rai.studycenter.firebase.profile;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.Rai.studycenter.R;
import com.Rai.studycenter.firebase.ui.Firebase_StudyMaterial;
import com.Rai.studycenter.firebase.uploads.upload;
import com.Rai.studycenter.utils.Utils;
import com.bumptech.glide.Glide;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.IgnoreExtraProperties;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.OnProgressListener;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.io.File;
import java.net.URI;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;

import static com.Rai.studycenter.constant.Constant.CollegeKey;
import static com.Rai.studycenter.constant.Constant.SharedPref;

public class UserProfile extends AppCompatActivity {
    SharedPreferences sharedPreferences;

    List<upload> SemDetails = new ArrayList<>();
    EditText editText;
    Button button;
    //private DatabaseReference mDatabase;
    private FirebaseAuth mAuth;
    String currentUser,semName,subjectName;
    TextView userName,userEmail;
    ImageView userProfile;
    EditText collegeIdEdt;
    Spinner spinnerSem,spinnerSubject;
    final static int PICK_PDF_CODE = 2342;
    String uniqueID;
    StorageReference mStorageReference;
    final FirebaseDatabase database = FirebaseDatabase.getInstance();
    DatabaseReference ref = database.getReference("users");
    Utils utils;

    String TAG="UserProfile";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mStorageReference = FirebaseStorage.getInstance().getReference();
        //mDatabase = FirebaseDatabase.getInstance().getReference();
        uniqueID=database.getReference("users").push().getKey();
        setContentView(R.layout.activity_user_profile);
        sharedPreferences = getSharedPreferences(SharedPref, Context.MODE_PRIVATE);


        collegeIdEdt=findViewById(R.id.userProfileClgId);
        editText = findViewById(R.id.userProfileEdt);
        button =findViewById(R.id.userProfileBtn);
        userName=findViewById(R.id.userProfileName);
        userEmail=findViewById(R.id.userProfileEmail);
        userProfile=findViewById(R.id.userProfileIcon);
        spinnerSem=findViewById(R.id.userProfileSpinnerSem);
        spinnerSubject=findViewById(R.id.userProfileSpinnerSub);
        mAuth = FirebaseAuth.getInstance();
        currentUser = mAuth.getCurrentUser().getUid();
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent();
                intent.setType("application/pdf");
                intent.setAction(Intent.ACTION_GET_CONTENT);
                startActivityForResult(Intent.createChooser(intent, "Select Picture"), PICK_PDF_CODE);
            }
        });
        setSpinner();

    }




    public void writeNewUser(String userId, String name, String email) {
        User user = new User(name, email);

        ref.child("/College/").child(userId).child(uniqueID).setValue(user);
    }

    public void getDetailsUser(){
        Glide.with(this).load(mAuth.getCurrentUser().getPhotoUrl()).placeholder(R.drawable.user_avatar).dontAnimate().into(userProfile);
       userName.setText(mAuth.getCurrentUser().getDisplayName());
       userEmail.setText(mAuth.getCurrentUser().getPhoneNumber());
    }

    public  void setSpinner(){
        final ArrayList<String> semOne = new ArrayList<String>(Arrays.asList(getResources().getStringArray(R.array.Sem1)));
        final ArrayList<String> semTwo = new ArrayList<String>(Arrays.asList(getResources().getStringArray(R.array.Sem2)));
        final ArrayList<String> semThree = new ArrayList<String>(Arrays.asList(getResources().getStringArray(R.array.Sem3)));
        final ArrayList<String> semFour = new ArrayList<String>(Arrays.asList(getResources().getStringArray(R.array.Sem4)));
        final ArrayList<String> semFive = new ArrayList<String>(Arrays.asList(getResources().getStringArray(R.array.Sem5)));
        final ArrayList<String> semSix = new ArrayList<String>(Arrays.asList(getResources().getStringArray(R.array.Sem6)));
        ArrayList<String> semesters=new ArrayList<>();
        semesters.add("Semester 1");
        semesters.add("Semester 2");
        semesters.add("Semester 3");
        semesters.add("Semester 4");
        semesters.add("Semester 5");
        semesters.add("Semester 6");
        ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, semesters);
        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerSem.setAdapter(dataAdapter);
        spinnerSem.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                 semName = adapterView.getItemAtPosition(i).toString();

                switch (i){
                    case 0:{
                        setSubject(new ArrayAdapter<String>(UserProfile.this, android.R.layout.simple_spinner_item, semOne));
                    }
                    break;
                    case 1:{
                        setSubject(new ArrayAdapter<String>(UserProfile.this, android.R.layout.simple_spinner_item, semTwo));
                    }
                    break;

                    case 2:{
                        setSubject(new ArrayAdapter<String>(UserProfile.this, android.R.layout.simple_spinner_item, semThree));
                    }
                    break;
                    case 3:{
                        setSubject(new ArrayAdapter<String>(UserProfile.this, android.R.layout.simple_spinner_item, semFour));
                    }
                    break;
                    case 4:{
                        setSubject(new ArrayAdapter<String>(UserProfile.this, android.R.layout.simple_spinner_item, semFive));
                    }
                    break;
                    case 5:{
                        setSubject(new ArrayAdapter<String>(UserProfile.this, android.R.layout.simple_spinner_item, semSix));
                    }
                    break;

                }

                // Showing selected spinner item
                //Toast.makeText(adapterView.getContext(), "Selected: " + item, Toast.LENGTH_LONG).show();

            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

    }
    public void setSubject(ArrayAdapter<String> dataAdapter){

        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        spinnerSubject.setAdapter(dataAdapter);
        spinnerSubject.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                subjectName=adapterView.getItemAtPosition(i).toString().replaceAll(" ","");
                    Toast.makeText(UserProfile.this, "Subject is :" + adapterView.getItemAtPosition(i).toString().replaceAll(" ", ""), Toast.LENGTH_SHORT).show();

            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        //when the user choses the file
        if (requestCode == PICK_PDF_CODE && resultCode == RESULT_OK && data != null && data.getData() != null) {
            //if a file is selected
            if (data.getData() != null) {
                if(collegeIdEdt.getText().toString().isEmpty()){
                    Toast.makeText(this, "All feilds are required", Toast.LENGTH_SHORT).show();
                }
                else{
                    if(collegeIdEdt.length()==5){
                    SharedPreferences.Editor editor=sharedPreferences.edit();
                    editor.putString(CollegeKey,collegeIdEdt.getText().toString());
                    //editor.commit();
                    editor.apply();
                    //uploading the file
                    uploadPdf(semName,subjectName,data.getData());
                    }
                    else{
                        Toast.makeText(this, "College ID must 5 Digits", Toast.LENGTH_SHORT).show();
                    }
                }

            }else{
                Toast.makeText(this, "No file chosen", Toast.LENGTH_SHORT).show();
            }
        }
    }
    public void checkCollegeId(){
       String CheckCId =sharedPreferences.getString(CollegeKey,"not found");
       if(CheckCId.equals("not found")){
           collegeIdEdt.setText("");
       }
       else {
           collegeIdEdt.setText(CheckCId);
       }
        Log.d(TAG, "checkCollegeId() returned: " + CheckCId);

    }
    @Override
    protected void onStart() {
        super.onStart();
        getDetailsUser();
        checkCollegeId();
        getBook();
    }
    public void uploadPdf(final String yearName, final String fileName, Uri data){
        final String collegeId=collegeIdEdt.getText().toString();
        Calendar cal = new GregorianCalendar();
        DateFormat df = new SimpleDateFormat("yyyy-MM-dd-hh-mm-ss");
        final String unique_id = df.format(cal.getTime()).replaceAll("-","").trim();
        //StorageReference sRef = mStorageReference.child("users/" + fileName + ".pdf");
        StorageReference sRef = mStorageReference.child("users/").child(collegeId).child(fileName + ".pdf");
        sRef.putFile(data)
                .addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                    @SuppressWarnings("VisibleForTests")
                    @Override
                    public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                        Toast.makeText(UserProfile.this, "File Uploaded Successfully", Toast.LENGTH_SHORT).show();

                        upload upload = new upload(yearName,fileName, taskSnapshot.getUploadSessionUri().toString(),collegeId);
                        ref.child(collegeId).child(ref.push().getKey()).setValue(upload);

                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception exception) {
                        Toast.makeText(getApplicationContext(), exception.getMessage(), Toast.LENGTH_LONG).show();
                    }
                })
                .addOnProgressListener(new OnProgressListener<UploadTask.TaskSnapshot>() {
                    @SuppressWarnings("VisibleForTests")
                    @Override
                    public void onProgress(UploadTask.TaskSnapshot taskSnapshot) {
                        double progress = (100.0 * taskSnapshot.getBytesTransferred()) / taskSnapshot.getTotalByteCount();
                        Toast.makeText(UserProfile.this, (int) progress + "% Uploading...", Toast.LENGTH_SHORT).show();

                    }
                });
    }
    public void getBook() {
    /*    ref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                // This method is called once with the initial value and again
                // whenever data at this location is updated.
                //
                for (DataSnapshot postSnapshot : dataSnapshot.getChildren()) {

                    upload getDatafromFirebase = postSnapshot.getValue(upload.class);
                    SemDetails.add(getDatafromFirebase);
                    Log.i(TAG, "Data = " + getDatafromFirebase.getSubjectName() + "\n" + getDatafromFirebase.getSubjectUrl());

                    // here you can access to name property like university.name

                }

            }

            @Override
            public void onCancelled(DatabaseError error) {
                // Failed to read value
                Log.w(TAG, "Failed to read value.", error.toException());
            }
        });*/

        ref.child(collegeIdEdt.getText().toString()).addChildEventListener(new ChildEventListener() {

            @Override
            public void onChildAdded(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {
                upload getDatafromFirebase = snapshot.getValue(upload.class);
                    SemDetails.add(getDatafromFirebase);
                    Log.i(TAG, "Data = " + getDatafromFirebase.getUniqueID() + "\n" + getDatafromFirebase.getSubjectUrl());

            }

            @Override
            public void onChildChanged(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {

            }

            @Override
            public void onChildRemoved(@NonNull DataSnapshot snapshot) {

            }

            @Override
            public void onChildMoved(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }


}
@IgnoreExtraProperties
class User {

    public String username;
    public String email;

    public User() {
        // Default constructor required for calls to DataSnapshot.getValue(User.class)
    }

    public User(String username, String email) {
        this.username = username;
        this.email = email;
    }

}

