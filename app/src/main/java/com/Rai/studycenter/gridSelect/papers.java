package com.Rai.studycenter.gridSelect;

import android.os.Bundle;
import android.view.MenuItem;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.Rai.studycenter.R;
import com.Rai.studycenter.adapters.PapersAdapter;

import java.util.ArrayList;
import java.util.Arrays;

public class papers extends AppCompatActivity {
    private RecyclerView recyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;


    ArrayList<String> button_text = new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.grd_papers);
        setTitle(R.string.solved_paper);
        ArrayList<String> main_text = new ArrayList<String>(Arrays.asList(getResources().getStringArray(R.array.Sem1)));

        button_text.clear();

        button_text.add("1");

        button_text.add("2");

        button_text.add("3");

        button_text.add("4");

        button_text.add("5");

        recyclerView = findViewById(R.id.recycler_view3);
        mLayoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setLayoutManager(mLayoutManager);
        //recyclerView.addItemDecoration(new DividerItemDecoration(recyclerView.getContext(), DividerItemDecoration.VERTICAL));
        mAdapter = new PapersAdapter(getApplicationContext(), main_text, button_text);
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