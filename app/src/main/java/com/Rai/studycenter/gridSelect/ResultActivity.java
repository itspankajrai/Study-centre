package com.Rai.studycenter.gridSelect;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.Environment;
import android.widget.Toast;

import com.Rai.studycenter.R;
import com.Rai.studycenter.firebase.firebaseutils.StartClass;
import com.Rai.studycenter.utils.DownloadFileAsync;
import com.google.android.material.chip.Chip;
import com.google.android.material.chip.ChipGroup;

import java.io.File;

public class ResultActivity extends AppCompatActivity {
    StartClass startClass;
    String[] sem=new String[]{"Sem 6","Sem 5"};
    ChipGroup semChipGroup;
    File checkFile;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);
        startClass=new StartClass(this);
        semChipGroup=findViewById(R.id.result_chipGroup);
        semChipGroup.setSingleSelection(true);
        addViews(sem,semChipGroup);

    }

    void addViews(String[] array,ChipGroup chipGroup){
        for(int i=0;i<array.length;i++){
            setSem(array[i],chipGroup);
        }
    }
    void setSem(String name,ChipGroup group){
        final Chip chip= (Chip) getLayoutInflater().inflate(R.layout.chip,null,false);
        chip.setText(name);

        group.addView(chip,group.getChildCount()-1);
        group.setOnCheckedChangeListener(new ChipGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(ChipGroup group, int checkedId) {
                Chip newChip=findViewById(checkedId);
                if(newChip!=null){
                    checkSem(newChip.getText().toString());
                }
                else {

                }
            }
        });
    }
    void checkSem(String sem){
        switch (sem){
            case "Sem 5":
                if(checkFile("sem_5_results.pdf")) {
                startClass.changeActivity(this,"pdf_path","sem_5_results.pdf");
                }
                else {
                    Toast.makeText(this,"Downlaoded started please wait...",Toast.LENGTH_LONG).show();
                    startClass.downloadFromFireStore("sem_5_results");
                }
                break;
            case "Sem 6":
                if(checkFile("sem_6_results.pdf")) {
                    startClass.changeActivity(this,"pdf_path","sem_6_results.pdf");
                }
                else {
                   // startClass.downloadFromFireStore("");
                Toast.makeText(this,"Results yet be released",Toast.LENGTH_LONG).show();
                }
                break;

        }
    }
    Boolean checkFile(String filename){
        checkFile= new File(Environment.getExternalStorageDirectory() , "/StudyCenter/"+filename);
        if(checkFile.exists()){
            return true;
        }
        else {
            return false;
        }
    }
}