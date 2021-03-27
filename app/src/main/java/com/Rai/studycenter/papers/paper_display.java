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
import com.Rai.studycenter.adapters.RecyclerAdapter;

import java.util.ArrayList;

public class paper_display extends AppCompatActivity {
    private RecyclerView recyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;
    ArrayList<String> id = new ArrayList<>();
    ArrayList<String> title = new ArrayList<>();
    ArrayList<String> description = new ArrayList<>();
    ArrayList<String> question=new ArrayList<>();
    ArrayList<String> answeraaray=new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_paper_display);
        setTitle(R.string.solved_paper);
        int resId = R.anim.layout_animation_fall_down;
        id.clear();
        title.clear();
        description.clear();
        question.clear();
        answeraaray.clear();

        id.add("1");
        title.add("What is object oriented language ?");
        description.add("Tap to show answer..");
        question.add("What is object oriented language ?");
        answeraaray.add("C++ is object oriented language.\njava is also object oriented language.\nbut c is procedure oriented language");

        id.add("2");
        title.add("What is object oriented language ?");
        description.add("Tap to show answer..");
        question.add("What is object oriented language ?");
        answeraaray.add("C++ is object oriented language.\njava is also object oriented language.\nbut c is procedure oriented language");

        id.add("3");
        title.add("What is object oriented language ?");
        description.add("Tap to show answer..");
        question.add("What is object oriented language ?");
        answeraaray.add("C++ is object oriented language.\njava is also object oriented language.\nbut c is procedure oriented language");

        id.add("4");
        title.add("What is object oriented language ?");
        description.add("Tap to show answer..");
        question.add("What is object oriented language ?");
        answeraaray.add("C++ is object oriented language.\njava is also object oriented language.\nbut c is procedure oriented language");

        id.add("5");
        title.add("What is object oriented language ?");
        description.add("Tap to show answer..");
        question.add("What is object oriented language ?");
        answeraaray.add("C++ is object oriented language.\njava is also object oriented language.\nbut c is procedure oriented language");

        id.add("6");
        title.add("What is object oriented language ?");
        description.add("Tap to show answer..");
        question.add("What is object oriented language ?");
        answeraaray.add("C++ is object oriented language.\njava is also object oriented language.\nbut c is procedure oriented language");

        id.add("7");
        title.add("What is object oriented language ?");
        description.add("Tap to show answer..");
        question.add("What is object oriented language ?");
        answeraaray.add("C++ is object oriented language.\njava is also object oriented language.\nbut c is procedure oriented language");

        id.add("8");
        title.add("What is object oriented language ?");
        description.add("Tap to show answer..");
        question.add("What is object oriented language ?");
        answeraaray.add("C++ is object oriented language.\njava is also object oriented language.\nbut c is procedure oriented language");

        recyclerView = findViewById(R.id.recycler_view);
        mLayoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerView.setLayoutManager(mLayoutManager);
        LayoutAnimationController animation = AnimationUtils.loadLayoutAnimation(getApplication(), resId);
        recyclerView.setLayoutAnimation(animation);
        mAdapter = new RecyclerAdapter(getApplicationContext(), id, title, description,question,answeraaray);
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
