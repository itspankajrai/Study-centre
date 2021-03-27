package com.Rai.studycenter.gridSelect;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.widget.Toast;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.Rai.studycenter.MainActivity;
import com.Rai.studycenter.R;
import com.Rai.studycenter.note_module.notes_ocr_camera_fragment;
import com.Rai.studycenter.note_module.Notes_Default_Page;
import com.Rai.studycenter.note_module.notes_ocr_gallery_fragment;

import java.io.File;
import java.util.Objects;

import static com.Rai.studycenter.constant.Constant.CollegeKey;
import static com.Rai.studycenter.constant.Constant.SharedPref;

public class notes extends AppCompatActivity {
    String getSubjectUniqueId="";
    SharedPreferences sharedPreferences;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.grd_notes);

        sharedPreferences = getSharedPreferences(SharedPref, Context.MODE_PRIVATE);
        Intent intent = getIntent();
        String action = intent.getAction();
        Uri data = intent.getData();
        if(data!=null){
            getSubjectUniqueId=data.toString();
        }

        if(getSubjectUniqueId.equals("")){

        }
        else {
          /*  SharedPreferences.Editor editor = sharedPreferences.edit();
            editor.putString(CollegeKey, getSubjectUniqueId);
            editor.apply();*/
        }
        switch ("openFragment") {
            case "openFragment":

                getSupportFragmentManager()
                        .beginTransaction()
                        .replace(R.id.notes_holder, new Notes_Default_Page())
                        .commit();
                break;
            case "openFragment2":
                getSupportFragmentManager()
                        .beginTransaction()
                        .replace(R.id.notes_holder, new notes_ocr_gallery_fragment())
                        .commit();
                break;
            case "openFragment3":
                getSupportFragmentManager()
                        .beginTransaction()
                        .replace(R.id.notes_holder, new notes_ocr_camera_fragment())
                        .commit();
                break;


        }
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
        }

    }
    @Override
    public void onBackPressed() {
        // super.onBackPressed();

        startActivity(new Intent(notes.this, MainActivity.class));

        return;
    }


}
