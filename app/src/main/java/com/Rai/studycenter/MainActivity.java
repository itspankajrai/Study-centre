package com.Rai.studycenter;

import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.res.AssetManager;
import android.graphics.drawable.AnimationDrawable;
import android.os.Bundle;
import android.os.Environment;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import com.Rai.studycenter.adapters.Grid_Adapter;
import com.Rai.studycenter.firebase.firebaseutils.StartClass;
import com.Rai.studycenter.firebase.login.Login;
import com.Rai.studycenter.firebase.profile.UserProfile;
import com.Rai.studycenter.firebase.ui.FirebaseMenuActivity;
import com.Rai.studycenter.firebase.ui.Firebase_StudyMaterial;
import com.Rai.studycenter.firebase.ui.TimeTableFirebaseActivity;
import com.Rai.studycenter.gridSelect.ResultActivity;
import com.Rai.studycenter.gridSelect.Study_Material;
import com.Rai.studycenter.gridSelect.blackbook;
import com.Rai.studycenter.gridSelect.mock_test;
import com.Rai.studycenter.gridSelect.notes;
import com.Rai.studycenter.gridSelect.papers;
import com.Rai.studycenter.gridSelect.practicals;
import com.Rai.studycenter.settings.SettingsActivity;
import com.Rai.studycenter.utils.DownloadFileAsync;
import com.Rai.studycenter.utils.Downloads;
import com.Rai.studycenter.utils.NetworUtils;
import com.google.android.material.navigation.NavigationView;
import com.google.android.material.snackbar.Snackbar;
import com.google.firebase.auth.FirebaseAuth;

import java.io.File;

import static android.Manifest.permission.ACCESS_NETWORK_STATE;
import static android.Manifest.permission.INTERNET;
import static android.Manifest.permission.READ_PHONE_STATE;
import static android.Manifest.permission.WRITE_EXTERNAL_STORAGE;

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {
    StartClass startClass;
    String blackBookUrl="http://archive.mu.ac.in/myweb_test/syllFybscit/PCS.pdf";
    File checkFile;
    private FirebaseAuth mAuth;
    GridView gridView;
    MenuItem menuItem;
    static final String[] OPTION_main = new String[] {
            "Study Material", "Practicals","Mock Test", "Solved Papers","Black Book" ,"Time Table","Notes","Result"};
    public static final int RequestPermissionCode = 1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        startClass=new StartClass(this);
        mAuth = FirebaseAuth.getInstance();
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        checkAll();
        gridView = (GridView) findViewById(R.id.gridView1);
        gridView.setAdapter(new Grid_Adapter(this, OPTION_main));
        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> parent, View v,
                                    int position, long id) {
                switch(position)
                {
                    case 0:
                        changeActivity(Study_Material.class);
                        break;
                    case 1:
                        changeActivity(practicals.class);
                        break;
                    case 2:
                        changeActivity(mock_test.class);
                        break;
                    case 3:
                        changeActivity(papers.class);
                        break;
                    case 4:
                        if(checkFile("blackbook.pdf")){
                            startClass.changeActivity(MainActivity.this,"pdf_path", "blackbook.pdf");
                        }
                        else {
                            snack("Blackbook download started");
                            startClass.downloadFromFireStore("blackbook");
                            //new DownloadFileAsync(MainActivity.this,1,"blackbook.pdf").execute(blackBookUrl);
                        }
                             /*changeActivity(blackbook.class);*/
                        break;
                    case 5:
                        changeActivity(TimeTableFirebaseActivity.class);
                        break;
                    case 6:
                        changeActivity(notes.class);
                        break;
                    case 7:
                        changeActivity(ResultActivity.class);
                        break;
                    default:
                        snack();
                        break;
                }


            }
        });



        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
        slider();

    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);

         menuItem = menu.findItem(R.id.action_log_out);
        if(mAuth.getCurrentUser()==null){
        menuItem.setTitle("log in");
        }
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        switch (id){
            case R.id.action_settings:
                changeActivity(SettingsActivity.class,"EXTRA","openFragment");
                break;
            case R.id.action_about:
                changeActivity(SettingsActivity.class,"EXTRA","openFragment2");
                break;
            case R.id.action_support:
                changeActivity(SettingsActivity.class,"EXTRA","openFragment3");
                break;
            case R.id.action_log_out:
                if(mAuth.getCurrentUser()==null){
                   changeActivity(Login.class);
                }
                else {
                    logOut();
                    menuItem.setTitle("log in");
                }

                break;
            case R.id.action_exit:
                finish();
                System.exit(0);
                break;

        }


        return super.onOptionsItemSelected(item);
    }


    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();
        switch (id){
            case R.id.nav_firebaseBooks:
            changeActivity(Firebase_StudyMaterial.class);
                break;

            case R.id.nav_FriebaseMenu:
                changeActivity(FirebaseMenuActivity.class);
                break;

            case R.id.nav_result:
                snack();
                break;

            case R.id.nav_timetable:
                snack();
                break;

            case R.id.nav_downloads :
                changeActivity(Downloads.class);
                break;

            case R.id.nav_settings:
                changeActivity(SettingsActivity.class,"EXTRA","openFragment");
                break;

            case  R.id.nav_support:
                changeActivity(SettingsActivity.class,"EXTRA","openFragment3");
                break;

            case R.id.nav_about:
                changeActivity(SettingsActivity.class,"EXTRA","openFragment2");
                break;

            case R.id.nav_faq:
                changeActivity(SettingsActivity.class,"EXTRA","openFragment4");
                break;

        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
    private void slider(){
        AnimationDrawable animation = new AnimationDrawable();
        animation.addFrame(getResources().getDrawable(R.drawable.d), 2000);
        animation.addFrame(getResources().getDrawable(R.drawable.b), 2000);
        animation.addFrame(getResources().getDrawable(R.drawable.c), 2000);
        animation.setOneShot(false);
        ImageView imageAnim =  (ImageView) findViewById(R.id.imageslider);
        imageAnim.setBackgroundDrawable(animation);
        animation.start();
    }
    private void checkAll(){

        if(checkPermission()){
           // if all permissions are satisfied then do nothing & load MainActivity
        }
        else {
            requestPermission();
        }

    }
    private void requestPermission() {

        ActivityCompat.requestPermissions(MainActivity.this, new String[]
                {
                        ACCESS_NETWORK_STATE,
                        WRITE_EXTERNAL_STORAGE,
                        READ_PHONE_STATE,
                        INTERNET
                }, RequestPermissionCode);

    }
    public boolean checkPermission() {
        int FirstPermissionResult = ContextCompat.checkSelfPermission(getApplicationContext(), ACCESS_NETWORK_STATE);
        int SecondPermissionResult = ContextCompat.checkSelfPermission(getApplicationContext(), WRITE_EXTERNAL_STORAGE);
        int ThirdPermissionResult = ContextCompat.checkSelfPermission(getApplicationContext(), READ_PHONE_STATE);
        int FourthPermissionResult = ContextCompat.checkSelfPermission(getApplicationContext(), INTERNET);
        return FirstPermissionResult == PackageManager.PERMISSION_GRANTED &&
                SecondPermissionResult == PackageManager.PERMISSION_GRANTED &&
                ThirdPermissionResult == PackageManager.PERMISSION_GRANTED &&
                FourthPermissionResult == PackageManager.PERMISSION_GRANTED;
    }
    private void snack(){
        Snackbar snackbar = Snackbar.make(findViewById(android.R.id.content), "We are working on this function...", Snackbar.LENGTH_LONG);
        snackbar.show();
    }
    private void snack(String value){
        Snackbar snackbar = Snackbar.make(findViewById(android.R.id.content), value, Snackbar.LENGTH_LONG);
        snackbar.show();
    }
    @Override
    protected void onStart() {
        NetworUtils b = new NetworUtils();

        if(b.isNetworkAvailable(this)==true){

        }
        else {
            Toast.makeText(this, "Check your internet connection", Toast.LENGTH_SHORT).show();
        }
        super.onStart();
    }

    public void changeActivity(Class activity){
        Intent intent=new Intent(MainActivity.this,activity);
        startActivity(intent);
    }
    void changeActivity(Class activity,String key,String value){
        Intent intent=new Intent(MainActivity.this,activity);
        intent.putExtra(key,value);
        startActivity(intent);
    }
    public void logOut(){
            Toast.makeText(MainActivity.this,"Logging out "+mAuth.getCurrentUser().getDisplayName(),Toast.LENGTH_LONG).show();
            mAuth.signOut();
    }
    Boolean checkFile(String filename){
        checkFile= new File(Environment.getExternalStorageDirectory() , "/StudyCenter/"+filename);
        if(checkFile.exists()){
            return true;
        }
        else {
            return false;
        }
    }
}
