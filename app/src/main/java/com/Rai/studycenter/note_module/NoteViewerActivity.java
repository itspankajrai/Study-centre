package com.Rai.studycenter.note_module;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

import com.Rai.studycenter.R;

public class NoteViewerActivity extends AppCompatActivity {
    TextView title,note;
    String getNote,getNoteTitle;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_note_viewer);
        title=findViewById(R.id.view_note_title);
        note=findViewById(R.id.view_note_desc);
        getNoteTitle=getIntent().getStringExtra("noteTitle");
        getNote=getIntent().getStringExtra("noteData");
        title.setText(getNoteTitle);
        note.setText(getNote);


    }



}