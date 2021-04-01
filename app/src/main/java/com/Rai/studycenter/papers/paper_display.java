package com.Rai.studycenter.papers;

import android.os.Bundle;
import android.view.MenuItem;
import android.view.animation.AnimationUtils;
import android.view.animation.LayoutAnimationController;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.Rai.studycenter.R;
import com.Rai.studycenter.adapters.PaperRecyclerAnswersAdapter;
import com.Rai.studycenter.models.solvedPaper;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class paper_display extends AppCompatActivity {
    private RecyclerView recyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;
    List<solvedPaper> Data = new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_paper_display);
        setTitle(R.string.solved_paper);
        int resId = R.anim.layout_animation_fall_down;
        Data.add(new solvedPaper("2+1=?","3"));
        Data.add(new solvedPaper("2+2=?","4"));
        Data.add(new solvedPaper("2+4=?","6"));
        recyclerView = findViewById(R.id.recycler_view);
        mLayoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerView.setLayoutManager(mLayoutManager);
        LayoutAnimationController animation = AnimationUtils.loadLayoutAnimation(getApplication(), resId);
        recyclerView.setLayoutAnimation(animation);
        mAdapter = new PaperRecyclerAnswersAdapter(getApplicationContext(), Data);
        recyclerView.setAdapter(mAdapter);
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
