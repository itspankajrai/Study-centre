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
        Data.add(new solvedPaper("What is the role of a compiler and interpreter","A program that is written in a high-level language must, however, be translated into\n" +
                "machine language before it can be executed. This is known as compilation or\n" +
                "interpretation, depending on how it is carried out.\n" +
                "(Compilers translate the entire program into machine language before executing any of the\n" +
                "instructions. Interpreters, on the other hand, proceed through a program by translating and\n" +
                "then executing single instructions or small groups of instructions.) In either case, the\n" +
                "translation is carried out automatically within the computer. In fact, inexperienced\n" +
                "programmers may not even be aware that this process is taking place, since they typically\n" +
                "see only their original high-level program, the input data, and the calculated results. Most\n" +
                "implementations of C operate as compilers. A compiler or interpreter is itself a computer\n" +
                "program. It accepts a program written in a high-level language (e.g., C) as input, and\n" +
                "generates a corresponding machine-language program as output. The original high-level\n" +
                "program is called the source program, and the resulting machine-language program is called\n" +
                "the object program. Every computer must have its own compiler or interpreter for a\n" +
                "particular high-level language"));
        Data.add(new solvedPaper("What are the various datatypes in C ? Explain them.","C supports several different types of data, each of which may be represented differently\n" +
                "within the computer’s\n" +
                "memory. The basic data types are listed below. Typical memory requirements are also\n" +
                "given. (The memory\n" +
                "requirements for each data type will determine the permissible range of values for that\n" +
                "data type. Note that the\n" +
                "memory requirements for each data type may vary from one C compiler to another.)\n" +
                "Built in\n" +
                "i n t integer quantity 2 bytes or one word (varies fromone compiler to another)\n" +
                "char single character 1 byte\n" +
                "f l o a t floating-point number (i.e., a number containing 1 word (4 bytes) a decimal point\n" +
                "andor an exponent)\n" +
                "double double-precision floating-point number (i.e., more 2 words (8 bytes)significant\n" +
                "figures, and an exponent which maybe larger in magnitude)\n" +
                "Derived types\n" +
                "They include\n" +
                "(a) Pointer types,\n" +
                "(b) Array types,\n" +
                "(c) Structure types,\n" +
                "(d) Union types and\n" +
                "(e) Function types.\n" +
                "The array types and structure types are referred collectively as the aggregate types. The\n" +
                "type of a function specifies the type of the function's return value.\n"));
        Data.add(new solvedPaper("What are the rule for all numeric constants in C.\n","Integer and floating-point constants represent numbers. They are often referred to\n" +
                "collectively as\n" +
                "numeric-type constants.\n" +
                "The following rules apply to all numeric-type constants.\n" +
                "1. Commas and blank spaces cannot be included within the constant.\n" +
                "2. The constant can be preceded by a minus (-) sign if desired. (Actually the minus sign is an\n" +
                "operator that changes the sign of a positive constant, though it can be thought of as a part\n" +
                "of the constant itself.)\n" +
                "3. The value of a constant cannot exceed specified minimum and maximum bounds. For\n" +
                "each type of constant, these bounds will vary from one C compiler to another.\n"));
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
