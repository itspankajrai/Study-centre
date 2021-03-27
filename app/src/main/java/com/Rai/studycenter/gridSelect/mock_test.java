package com.Rai.studycenter.gridSelect;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import com.Rai.studycenter.R;
import com.Rai.studycenter.mock_test.mock_testmcq;
import com.google.android.material.chip.Chip;
import com.google.android.material.chip.ChipGroup;
import static com.Rai.studycenter.constant.Constant.answers;
import static com.Rai.studycenter.constant.Constant.opt;
import static com.Rai.studycenter.constant.Constant.questions;
import static com.Rai.studycenter.constant.Constant.sicAnswers;
import static com.Rai.studycenter.constant.Constant.sicOptions;
import static com.Rai.studycenter.constant.Constant.sicQuestions;

public class mock_test extends AppCompatActivity {

    ChipGroup chipGroup;
    static final String[] sem_list = new String[] {
            "Sem 6","Sem 1", "Sem 2","Sem 3", "Sem 4","Sem 5" };
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.grd_mock_test);
        chipGroup=findViewById(R.id.mock_chip_group);
        chipGroup.setSingleSelection(true);
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
                        checkSem(chip1.getText().toString());
                        chip1.setChecked(false);
                }
                else {
                }
            }
        });
    }
    void checkSem(String name){
        switch (name){
            case "Sem 1":
                changeActivity(questions,answers,opt);
                break;
            case "Sem 2":
                changeActivity(sicQuestions,sicAnswers,sicOptions);
                break;
        }
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
}
