package com.Rai.studycenter.note_module;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.Rai.studycenter.R;
import com.Rai.studycenter.caching.DBHelper;
import com.Rai.studycenter.gridSelect.notes;
import com.Rai.studycenter.models.Notesmodel;

public class NewNote extends AppCompatActivity {
        EditText new_Note_Title,new_Note_Desc;
        Button save,discard;
        DBHelper dbHelper;
        String update_ID="0";
        int id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_note);
        dbHelper=new DBHelper(this);
        new_Note_Title=findViewById(R.id.new_note_title);
        new_Note_Desc=findViewById(R.id.new_note_desc);
        save=findViewById(R.id.note_save);
        discard=findViewById(R.id.note_discard);
        update_ID=getIntent().getStringExtra("update_id");
        if (!TextUtils.isEmpty(update_ID) && TextUtils.isDigitsOnly(update_ID)) {
            id= Integer.parseInt(update_ID);
            updateNote();
        } else {
            id = 0;
            insertNote();

        }


    }
    public void updateNote(){

        Notesmodel abc=dbHelper.getNote(Integer.parseInt(update_ID));
        new_Note_Title.setText(abc.getName());
        new_Note_Desc.setText(abc.getDesc());
        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String update_title=new_Note_Title.getText().toString();
                String update_desc=new_Note_Desc.getText().toString();
                Notesmodel notesmodel=new Notesmodel(update_title,update_desc,Integer.parseInt(update_ID));
                dbHelper.updateNote(notesmodel);
                Toast.makeText(NewNote.this, "Note updated Successfully" , Toast.LENGTH_SHORT).show();
            }
        });
    }
    public void insertNote(){
        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String title=new_Note_Title.getText().toString();
                String desc=new_Note_Desc.getText().toString();
                dbHelper.insertNewNote(title,desc);

                new_Note_Title.setText("");
                new_Note_Desc.setText("");
                Context context=NewNote.this;
                CharSequence text="Note inserted Successfully...";
                Toast toast = Toast.makeText(context, text, Toast.LENGTH_LONG);
                toast.show();

                Intent intent=new Intent(NewNote.this, notes.class);
                startActivity(intent);
            }
        });
    }

}