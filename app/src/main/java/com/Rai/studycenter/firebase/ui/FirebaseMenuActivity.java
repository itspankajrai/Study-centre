package com.Rai.studycenter.firebase.ui;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Switch;
import android.widget.Toast;

import com.Rai.studycenter.R;
import com.Rai.studycenter.firebase.firebaseutils.FirebaseMenuInterface;
import com.Rai.studycenter.firebase.login.Login;
import com.Rai.studycenter.firebase.ui.frags.books;
import com.Rai.studycenter.firebase.ui.frags.exams;
import com.Rai.studycenter.firebase.ui.frags.mcqFragment;
import com.Rai.studycenter.firebase.ui.frags.profile;
import com.Rai.studycenter.note_module.Notes_Default_Page;
import com.Rai.studycenter.note_module.notes_ocr_camera_fragment;
import com.Rai.studycenter.utils.NetworUtils;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class FirebaseMenuActivity extends AppCompatActivity implements FirebaseMenuInterface, BottomNavigationView.OnNavigationItemSelectedListener {
    BottomNavigationView navigationView;
    FirebaseAuth mAuth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_firebase_menu);
        mAuth= FirebaseAuth.getInstance();
        replaceFragment(new profile());
        navigationView=findViewById(R.id.fbnavigation);
        bottomNavBar();
    }

    @Override
    public Boolean replaceFragment(Fragment fragment) {
        if (fragment!=null) {
            getSupportFragmentManager()
                    .beginTransaction()
                    .replace(R.id.firebase_menu_holder, fragment)
                    .commit();
        return true;
        }
        return  false;
    }



    @Override
    public void bottomNavBar() {
    navigationView.setOnNavigationItemSelectedListener(this);

    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        Fragment fragment=null;

        switch (item.getItemId()){
            case R.id.fb_menu_profile:
                getSupportActionBar().setTitle("Profile");
                fragment=new profile();
                break;
            case R.id.fb_menu_books:
                getSupportActionBar().setTitle("Books");
                fragment=new books();
                break;
            case R.id.fb_menu_exam:
                getSupportActionBar().setTitle("Exams");
                fragment = new exams();
                break;
            case R.id.fb_menu_mcq:
                getSupportActionBar().setTitle("Mcq");
                fragment = new mcqFragment();
                break;

        }
        return replaceFragment(fragment);
    }
    @Override
    protected void onStart() {

        super.onStart();
    }

}