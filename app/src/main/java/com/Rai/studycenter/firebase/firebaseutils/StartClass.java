package com.Rai.studycenter.firebase.firebaseutils;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Build;
import android.os.Environment;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import androidx.annotation.RequiresApi;

import com.Rai.studycenter.R;
import com.Rai.studycenter.helpers.Pdf_View;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.material.chip.Chip;
import com.google.android.material.chip.ChipGroup;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;

import static com.Rai.studycenter.constant.Constant.firebaseCollegeKey;
import static com.Rai.studycenter.constant.Constant.firebasePref;

public  class StartClass implements start_interface{
    StorageReference mStorageReference;
    SharedPreferences sharedPreferences;
    Context mContext;
    File checkF ;
    Activity activity;
    public StartClass(){

    }
    public StartClass(Context mContext) {
        this.mContext = mContext;

    }
    public StartClass(Activity activity){
        this.activity=activity;
    }

    @Override
    public String getUniqueID(Activity mContext) {
        sharedPreferences = mContext.getSharedPreferences(firebasePref, Context.MODE_PRIVATE);
        String CheckCId =sharedPreferences.getString(firebaseCollegeKey,"not found");
        if(CheckCId.equals("not found")){
            return  "none";
        }
        Log.d("TAG", "checkCollegeId() returned: " + CheckCId);
        return CheckCId;
    }

    public void changeActivity(Context activity,String key,String data){
        file_detection(data);
        if(checkF.exists()){
            Intent intent = new Intent(activity, Pdf_View.class);
            intent.putExtra(key, data);
            mContext.startActivity(intent);

        }
        else {
            Toast.makeText(activity, "Please Download book first", Toast.LENGTH_SHORT).show();
        }
    }
    private void file_detection(String file){
        checkF= new File(Environment.getExternalStorageDirectory() , "/StudyCenter/"+file);
    }



    public ArrayList<String> getSemArray(String name){
        ArrayList<String> sem = null;
        if(name.equals("Sem 1")){
            sem =new ArrayList<String>(Arrays.asList(mContext.getResources().getStringArray(R.array.Sem1)));
        }
        else if(name.equals("Sem 2")){
            sem=new ArrayList<String>(Arrays.asList(mContext.getResources().getStringArray(R.array.Sem2)));
        }
        else if(name.equals("Sem 3")){
            sem=new ArrayList<String>(Arrays.asList(mContext.getResources().getStringArray(R.array.Sem3)));
        }
        else if(name.equals("Sem 4")){
            sem=new ArrayList<String>(Arrays.asList(mContext.getResources().getStringArray(R.array.Sem4)));
        }
        else if(name.equals("Sem 5")){
            sem=new ArrayList<String>(Arrays.asList(mContext.getResources().getStringArray(R.array.Sem5)));
        }
        else if(name.equals("Sem 6")){
            sem=new ArrayList<String>(Arrays.asList(mContext.getResources().getStringArray(R.array.Sem6)));
        }
        else {
            sem= new ArrayList<String>(Arrays.asList(mContext.getResources().getStringArray(R.array.Sem1)));
        }
        return sem;
    }
   public void downloadFromFireStore(String name){
        mStorageReference = FirebaseStorage.getInstance().getReference();
        StorageReference books=mStorageReference.child("users")
                .child("content")
                .child(name+".pdf");
        long oneMegaByte=1024*1024*15;
        final File saved_filed=new File(Environment.getExternalStorageDirectory()+
                File.separator + "StudyCenter",name+".pdf");
        if(saved_filed.exists()){

            Toast.makeText(mContext, "File Already Exits", Toast.LENGTH_SHORT).show();
        }
        else {

            books.getBytes(oneMegaByte).addOnSuccessListener(new OnSuccessListener<byte[]>() {
                @RequiresApi(api = Build.VERSION_CODES.N)
                @Override
                public void onSuccess(byte[] bytes) {
                    try (FileOutputStream fileOutputStream = new FileOutputStream(saved_filed)) {
                        fileOutputStream.write(bytes);
                        fileOutputStream.close();
                        Log.d("TAG", "onSuccess() returned: " + saved_filed);
                        Toast.makeText(mContext, "Blackbook Downloaded", Toast.LENGTH_SHORT).show();
                    } catch (FileNotFoundException e) {
                        e.printStackTrace();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            });
        }
    }

    public void setChip(ChipGroup semChipsGroup){
        String[] sem={"Sem 1","Sem 2","Sem 3","Sem 4","Sem 5","Sem 6"};

        for(int i=0;i<sem.length;i++){
            Chip chip=(Chip)activity.getLayoutInflater().inflate(R.layout.chip,null,false);
            chip.setText(sem[i]);
            semChipsGroup.addView(chip,semChipsGroup.getChildCount()-1);
        }
    }
}
