package com.Rai.studycenter.gridSelect;

import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import com.Rai.studycenter.R;
import com.Rai.studycenter.firebase.firebaseutils.StartClass;
import com.google.android.material.chip.ChipGroup;


public class practicals extends AppCompatActivity {
    ChipGroup practicalChipGroup;
    StartClass startClass;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.grd_practicals);
        startClass=new StartClass(practicals.this);
        practicalChipGroup=findViewById(R.id.practicalChipGroup);
        practicalChipGroup.setSingleSelection(true);
        startClass.setChip(practicalChipGroup);


    }
}