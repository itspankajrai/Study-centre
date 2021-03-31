package com.Rai.studycenter.firebase.firebaseutils;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Environment;
import android.util.Log;
import android.widget.Toast;

import com.Rai.studycenter.R;
import com.Rai.studycenter.helpers.Pdf_View;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;

import static com.Rai.studycenter.constant.Constant.firebaseCollegeKey;
import static com.Rai.studycenter.constant.Constant.firebasePref;

public  class StartClass implements start_interface{
    SharedPreferences sharedPreferences;
    Context mContext;
    File checkF ;
    public StartClass(){

    }
    public StartClass(Context mContext) {
        this.mContext = mContext;
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
}
