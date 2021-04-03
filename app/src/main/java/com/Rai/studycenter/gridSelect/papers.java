package com.Rai.studycenter.gridSelect;

import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.Rai.studycenter.R;
import com.Rai.studycenter.adapters.PapersListAdapter;
import com.Rai.studycenter.firebase.firebaseutils.StartClass;
import com.google.android.material.chip.Chip;
import com.google.android.material.chip.ChipGroup;

import java.util.ArrayList;

public class papers extends AppCompatActivity {
    private RecyclerView recyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;


    ArrayList<String> button_text = new ArrayList<>();
    ChipGroup semList,subList;
    StartClass startClass;
    static final String[] sem_list = new String[] {
            "Sem 6","Sem 1", "Sem 2","Sem 3", "Sem 4","Sem 5" };
    CardView subCard;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.grd_papers);
        setTitle(R.string.solved_paper);


        recyclerView = findViewById(R.id.paper_subject_rec);
        subCard=findViewById(R.id.paperCardView2);
        startClass=new StartClass(this);
        semList=findViewById(R.id.paper_chip_group);
        semList.setSingleSelection(true);

        addSems(sem_list,semList);
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
    void addSems(String[] array,ChipGroup chipGroup){
        for(int i=0;i<array.length;i++){
            semListChipView(array[i],chipGroup);
        }
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
                    subCard.setVisibility(View.VISIBLE);

                }
                else {
                    subCard.setVisibility(View.GONE);
                }
            }
        });
    }


// for subject chips
    public void getSemester(Chip id){
        if(id!=null){

            setSubject(id.getText().toString());
        }
        else {

        }
    }

    public void setSubject(String name){
        setRecyclerView(startClass.getSemArray(name));
        mAdapter.notifyDataSetChanged();

    }
    void setRecyclerView(ArrayList<String> arrayList){


        mLayoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerView.setLayoutManager(mLayoutManager);
        mAdapter = new PapersListAdapter(getApplicationContext(), arrayList);
        recyclerView.setAdapter(mAdapter);
    }

}