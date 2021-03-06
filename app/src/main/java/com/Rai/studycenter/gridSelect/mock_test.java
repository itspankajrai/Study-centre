package com.Rai.studycenter.gridSelect;


import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import com.Rai.studycenter.R;
import com.Rai.studycenter.firebase.firebaseutils.StartClass;
import com.Rai.studycenter.mock_test.mock_testmcq;
import com.Rai.studycenter.models.mockTestModel;
import com.google.android.material.chip.Chip;
import com.google.android.material.chip.ChipGroup;

import java.util.HashMap;

import static com.Rai.studycenter.constant.Constant.CSanswers;
import static com.Rai.studycenter.constant.Constant.CSoptions;
import static com.Rai.studycenter.constant.Constant.CSquestions;
import static com.Rai.studycenter.constant.Constant.DEAnswers;
import static com.Rai.studycenter.constant.Constant.DEOptions;
import static com.Rai.studycenter.constant.Constant.DEQuestions;
import static com.Rai.studycenter.constant.Constant.IPanswers;
import static com.Rai.studycenter.constant.Constant.IPoptions;
import static com.Rai.studycenter.constant.Constant.IPquestions;
import static com.Rai.studycenter.constant.Constant.OPanswers;
import static com.Rai.studycenter.constant.Constant.OPoptions;
import static com.Rai.studycenter.constant.Constant.OPquestions;


public class mock_test extends AppCompatActivity {
    StartClass startClass;
    ChipGroup chipGroup,chipGroupSubejcts;
    CardView cardView;
    static final String[] sem_list = new String[] {
            "Sem 6","Sem 1", "Sem 2","Sem 3", "Sem 4","Sem 5" };
    String currentSem;
    HashMap<String, mockTestModel> sem;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.grd_mock_test);
        startClass=new StartClass(this);
        mapIT();
        chipGroup=findViewById(R.id.mock_chip_group);
        chipGroup.setSingleSelection(true);
        chipGroupSubejcts=findViewById(R.id.mock_text_subjects);
        chipGroupSubejcts.setSingleSelection(true);
        cardView=findViewById(R.id.mockcardview);
        looIt(sem_list,chipGroup);
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
        }
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            // Respond to the action bar's Up/Home button
            case android.R.id.home:
                super.onBackPressed();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    public void semListChipView(String names,ChipGroup chipGroup) {
        final Chip chip=(Chip) this.getLayoutInflater().inflate(R.layout.chip,null,false);
        chip.setText(names);
        chipGroup.addView(chip,chipGroup.getChildCount()-1);
        chipGroup.setOnCheckedChangeListener(new ChipGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(ChipGroup group, int checkedId) {
                Chip chip1=findViewById(checkedId);
                if(chip1!=null) {

                        chip1.setChecked(false);
                        getSemester(chip1);
                        cardView.setVisibility(View.VISIBLE);
                }
                else {
                    cardView.setVisibility(View.INVISIBLE);
                }
            }
        });
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
        chipGroupSubejcts.removeAllViewsInLayout();
        for (int i = 0; i <startClass.getSemArray(name).size() ; i++) {
            subjectlistchip(startClass.getSemArray(name).get(i),chipGroupSubejcts);
        }

    }

    private void subjectlistchip(String s, ChipGroup chipGroupSubejcts) {
        final Chip chip=(Chip) this.getLayoutInflater().inflate(R.layout.chip,null,false);
        chip.setText(s);
        chipGroupSubejcts.addView(chip,chipGroupSubejcts.getChildCount()-1);
        chipGroupSubejcts.setOnCheckedChangeListener(new ChipGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(ChipGroup group, int checkedId) {
                Chip chip1=findViewById(checkedId);
                if(chip1!=null) {
                    String sub_name = chip1.getText().toString();
                    //Toast.makeText(mock_test.this, "Opening mock test for "+sub_name, Toast.LENGTH_SHORT).show();
                    mockTestModel model=sem.get(sub_name);
                    if(model!=null){
                    changeActivity(model.getQuestion(),model.getAnswer(),model.getOptions());
                    chip1.setChecked(false);
                    }
                    else {
                        Toast.makeText(mock_test.this, "Data for this subject will be added soon", Toast.LENGTH_SHORT).show();
                    }
                }
                else {
                }
            }
        });
    }


    void looIt(String[] array,ChipGroup chipGroup){
        for(int i=0;i<array.length;i++){
            semListChipView(array[i],chipGroup);
        }
    }
    void changeActivity(String[] questions,String[] ans,String[] options){
        Intent sem1=new Intent(mock_test.this,mock_testmcq.class);
        sem1.putExtra("mcq", questions);
        sem1.putExtra("ans", ans);
        sem1.putExtra("crt", options);
        startActivity(sem1);

    }
    void mapIT(){
       sem=new HashMap<>();
       sem.put("Digital Electronics",new mockTestModel(DEQuestions,DEAnswers,DEOptions));
       sem.put("Imprative Programming",new mockTestModel(IPquestions,IPanswers,IPoptions));
       sem.put("Operating System",new mockTestModel(OPquestions,OPanswers,OPoptions));
       sem.put("Communication Skills",new mockTestModel(CSquestions,CSanswers,CSoptions));
    }
}
