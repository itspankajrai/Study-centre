package com.Rai.studycenter.firebase.ui.frags;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.Rai.studycenter.R;
import com.Rai.studycenter.firebase.firebaseutils.StartClass;
import com.Rai.studycenter.firebase.firebaseutils.firebaseMenu;
import com.Rai.studycenter.firebase.uploads.upload;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.material.button.MaterialButton;
import com.google.android.material.chip.Chip;
import com.google.android.material.chip.ChipGroup;
import com.google.android.material.progressindicator.LinearProgressIndicator;
import com.google.android.material.snackbar.BaseTransientBottomBar;
import com.google.android.material.snackbar.Snackbar;
import com.google.android.material.textfield.TextInputLayout;
import com.google.android.material.textview.MaterialTextView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.OnProgressListener;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

import javax.security.auth.Subject;

import static com.Rai.studycenter.constant.Constant.firebaseCollegeKey;
import static com.Rai.studycenter.constant.Constant.firebaseDefualtUrl;
import static com.Rai.studycenter.constant.Constant.firebasePref;

public class books extends Fragment implements firebaseMenu {
    final static int PICK_PDF_CODE = 2342;
    SharedPreferences sharedPreferences;
    FirebaseAuth mAuth ;
    String currentId;
    ChipGroup chipGroup;
    Chip chip;
    Chip subjectChip;
    ChipGroup bookSubjectGroup;
    CardView cardView;
    View view;
    String currentSubject,currentSem;
    MaterialButton chooseUploadBtn,uploadBtn;
    TextInputLayout books_key_edt;
    MaterialTextView tvFilename;
    LinearProgressIndicator progressIndicator;
    StorageReference mStorageReference;
    final FirebaseDatabase database = FirebaseDatabase.getInstance();
    DatabaseReference ref = database.getReference("users");
    Uri bookData;
    StartClass startClass;
    Boolean state=false,fileCHECK=true;
    Map<String,Object> map;
    String firebaseKey;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view= inflater.inflate(R.layout.fragment_books, container, false);
        setHasOptionsMenu(true);
        startClass=new StartClass();
        mStorageReference = FirebaseStorage.getInstance().getReference();
        mAuth= FirebaseAuth.getInstance();
        sharedPreferences = getActivity().getSharedPreferences(firebasePref, Context.MODE_PRIVATE);
        progressIndicator=view.findViewById(R.id.books_progress_bar);

        books_key_edt=view.findViewById(R.id.book_edt_layout);
        tvFilename=view.findViewById(R.id.books_filename);
        chooseUploadBtn=view.findViewById(R.id.books_choose_btn);
        uploadBtn=view.findViewById(R.id.books_upload_btn);
        uploadBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(final View view) {
                if(state){
                    firebaseKey=getKey(currentSubject);
                    if (fileCHECK==false){
                        update(currentSem,currentSubject,currentId,bookData);
                    }
                    else {
                        Toast.makeText(getContext(),"File not exists to update",Toast.LENGTH_LONG).show();
                    }
                }
                else {
                    upload();
                }
            }
        });
        chooseUploadBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(currentSem.isEmpty()||currentSubject.isEmpty()||books_key_edt.getEditText().getText().toString().isEmpty()){
                    Toast.makeText(getContext(), "All fields Required", Toast.LENGTH_SHORT).show();

                }
                else {
                    SharedPreferences.Editor editor=sharedPreferences.edit();
                    editor.putString(firebaseCollegeKey,books_key_edt.getEditText().getText().toString());
                    editor.apply();
                    Intent ChooseFileIntent = new Intent();
                    ChooseFileIntent.setType("application/pdf");
                    ChooseFileIntent.setAction(Intent.ACTION_GET_CONTENT);
                    startActivityForResult(Intent.createChooser(ChooseFileIntent, "Select PDF Book"), PICK_PDF_CODE);

                }
            }
        });
        cardView=view.findViewById(R.id.semCardView);
        bookSubjectGroup=view.findViewById(R.id.book_subjects);
        bookSubjectGroup.setSingleSelection(true);
        chipGroup=view.findViewById(R.id.bookschipGroup);
        chipGroup.setSingleSelection(true);
        chipGroup.setOnCheckedChangeListener(new ChipGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(ChipGroup group, int checkedId) {
                chip=view.findViewById(checkedId);
                getSemester(chip);
                cardView.setVisibility(View.VISIBLE);

            }
        });

        return view;
    }

    public void getSemester(Chip id){
        if(id!=null){
        currentSem=id.getText().toString().replaceAll(" ","");
        setSubject(id.getText().toString());
        }
        else {

        }
    }

    public void setSubject(String name){
        bookSubjectGroup.removeAllViewsInLayout();
        for (int i = 0; i <getSemArray(name).size() ; i++) {
            getSubject(getSemArray(name).get(i),bookSubjectGroup);
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
    public void getSubject(String pItem, ChipGroup pChipGroup){
        Chip lChip = (Chip) this.getLayoutInflater().inflate(R.layout.chip, null, false);
        lChip.setText(pItem);

        pChipGroup.addView(lChip, pChipGroup.getChildCount() - 1);
        pChipGroup.setOnCheckedChangeListener(new ChipGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(ChipGroup group, int checkedId) {
                subjectChip=view.findViewById(checkedId);
                getSubjectChip(subjectChip);
            }
        });
    }
    public void getSubjectChip(Chip chip){
        if(chip!=null){

            currentSubject=chip.getText().toString().replaceAll(" ","");
            chooseUploadBtn.setVisibility(View.VISIBLE);

        }
        else {
            Toast.makeText(getContext(),"Nothing selected",Toast.LENGTH_SHORT).show();
            chooseUploadBtn.setVisibility(View.INVISIBLE);
        }
    }
    @Override
    public void getAuth() {

    }


    public void checkCollegeID() {
        if(startClass.getUniqueID(getActivity()).equals("not found")){
            currentId=null;
            books_key_edt.getEditText().setText("");
        }
        else {
            currentId=startClass.getUniqueID(getActivity());
            books_key_edt.getEditText().setText(currentId);
        }

    }
    public void uploadBookToFireBase(final String sem, final String subject, final String id , Uri data){
        StorageReference sRef = mStorageReference.child("users/").child(id).child(subject + ".pdf");
        sRef.putFile(data)
                .addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                    @SuppressWarnings("VisibleForTests")
                    @Override
                    public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                        progressIndicator.setVisibility(View.INVISIBLE);
                        Snackbar snackbar = Snackbar.make(view, "Book Uploaded Successfully", BaseTransientBottomBar.LENGTH_LONG);
                        snackbar.show();
                        upload upload = new upload(sem,subject, taskSnapshot.getUploadSessionUri().toString(),id);
                        ref.child(id).child(ref.push().getKey()).setValue(upload);

                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception exception) {
                        Toast.makeText(getContext(), exception.getMessage(), Toast.LENGTH_LONG).show();
                    }
                })
                .addOnProgressListener(new OnProgressListener<UploadTask.TaskSnapshot>() {
                    @SuppressWarnings("VisibleForTests")
                    @Override
                    public void onProgress(UploadTask.TaskSnapshot taskSnapshot) {
                        double progress = (100.0 * taskSnapshot.getBytesTransferred()) / taskSnapshot.getTotalByteCount();
                        //Toast.makeText(getContext(), (int) progress + "% Uploading...", Toast.LENGTH_SHORT).show();
                        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
                            progressIndicator.setVisibility(View.VISIBLE);
                            progressIndicator.setProgress((int)progress,true);
                        }

                    }
                });

    }
    @Override
    public void onStart() {
        super.onStart();
        checkCollegeID();
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        tvFilename.setVisibility(View.VISIBLE);
        bookData=data.getData();
        tvFilename.setText(bookData.toString());
    }
    @Override
    public void onPrepareOptionsMenu(Menu menu) {
        MenuItem checkable = menu.findItem(R.id.books_state);
        checkable.setChecked(state);
    }
    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        inflater.inflate(R.menu.fragment_books_menu, menu);
        super.onCreateOptionsMenu(menu, inflater);
    }


    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();
        switch (id){
            case R.id.frag_share_books:
                String share="studyMaterial";
                Intent sharingIntent = new Intent(android.content.Intent.ACTION_SEND);
                sharingIntent.setType("text/*");
                String shareBody = "Hey There,\n"+firebaseDefualtUrl+share+"?key="+currentId;
                sharingIntent.putExtra(android.content.Intent.EXTRA_TEXT, shareBody);
                startActivity(Intent.createChooser(sharingIntent,"Choose app to send"));
                break;
            case R.id.books_state:
                state = !item.isChecked();
                item.setChecked(state);
                break;

        }

        return super.onOptionsItemSelected(item);

    }
    void upload(){
        if(bookData!=null){
            DatabaseReference chk=ref.child(currentId);
            chk.orderByChild("subjectName").equalTo(currentSubject)
                    .addListenerForSingleValueEvent(new ValueEventListener() {

                        @Override
                        public void onDataChange(DataSnapshot dataSnapshot) {
                            if(dataSnapshot.exists()){
                                Snackbar snackbar = Snackbar.make(view, "Subject Already exists", BaseTransientBottomBar.LENGTH_LONG);
                                snackbar.show();
                            } else {
                                //bus number doesn't exists.
                                uploadBookToFireBase(currentSem,currentSubject,currentId,bookData);
                            }
                        }

                        @Override
                        public void onCancelled(@NonNull DatabaseError error) {

                        }
                    });
        }

        else {
            Snackbar snackbar = Snackbar.make(view, "Select book first", BaseTransientBottomBar.LENGTH_LONG);
            snackbar.show();
        }
    }

    private HashMap<String,Object> update(final String semName, final String subjectName, final String uniqueID, Uri bookData) {
        if(bookData !=null){
             map=new HashMap<>();
            final DatabaseReference chk=ref.child(currentId);
            String key = ref.child(currentId).child("subjectName").push().getKey();
            StorageReference sRef = mStorageReference.child("users/").child(currentId).child(subjectName + ".pdf");
            sRef.putFile(bookData)
                    .addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                        @SuppressWarnings("VisibleForTests")
                        @Override
                        public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                            progressIndicator.setVisibility(View.INVISIBLE);
                            Snackbar snackbar = Snackbar.make(view, "Book Uploaded Successfully", BaseTransientBottomBar.LENGTH_LONG);
                            snackbar.show();
                            map.put("semName",semName);
                            map.put("subjectName",subjectName);
                            map.put("subjectUrl",taskSnapshot.getUploadSessionUri().toString());
                            map.put("uniqueID",uniqueID);
                            chk.child(firebaseKey).updateChildren(map).addOnSuccessListener(new OnSuccessListener<Void>() {
                                @Override
                                public void onSuccess(Void aVoid) {
                                    Snackbar snackbar = Snackbar.make(view, "Book Updated Successfully", BaseTransientBottomBar.LENGTH_LONG);
                                    snackbar.show();
                                }
                            });
                        }
                    })
                    .addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception exception) {
                            Toast.makeText(getContext(), exception.getMessage(), Toast.LENGTH_LONG).show();
                        }
                    })
                    .addOnProgressListener(new OnProgressListener<UploadTask.TaskSnapshot>() {
                        @SuppressWarnings("VisibleForTests")
                        @Override
                        public void onProgress(UploadTask.TaskSnapshot taskSnapshot) {
                            double progress = (100.0 * taskSnapshot.getBytesTransferred()) / taskSnapshot.getTotalByteCount();
                            //Toast.makeText(getContext(), (int) progress + "% Uploading...", Toast.LENGTH_SHORT).show();
                            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
                                progressIndicator.setVisibility(View.VISIBLE);
                                progressIndicator.setProgress((int)progress,true);
                            }

                        }
                    });
        }
        Toast.makeText(getContext(), "update is selected", Toast.LENGTH_SHORT).show();
        return (HashMap<String, Object>) map;
    }


    //Retrieving Firebase key for current Subject
    String getKey(final String currentSubject){
        ref.child(currentId).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for(DataSnapshot childs:snapshot.getChildren()){
                    if(childs.child("subjectName").getValue().equals(currentSubject)) {
                        fileCHECK = false;
                        firebaseKey = childs.getKey();
                    }
                    else {
                        fileCHECK=true;
                    }
                    }
            }
            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    return firebaseKey;
    }
}