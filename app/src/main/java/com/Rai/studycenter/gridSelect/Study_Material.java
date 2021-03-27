package com.Rai.studycenter.gridSelect;

import android.os.Bundle;
import android.view.MenuItem;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import com.Rai.studycenter.R;
import com.Rai.studycenter.adapters.Study_material_Adpater;

public class Study_Material extends  AppCompatActivity {
    private Study_Material viewPager;
    Study_material_Adpater myAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.grd_study_material);
        setTitle(R.string.study_material);

        ViewPager vpPager = (ViewPager) findViewById(R.id.vpPager);
        vpPager.setOffscreenPageLimit(3);
        myAdapter = new Study_material_Adpater(getSupportFragmentManager());
        vpPager.setAdapter(myAdapter);
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
}
