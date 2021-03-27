package com.Rai.studycenter.gridSelect;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.Context;
import android.media.Image;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.Rai.studycenter.BuildConfig;
import com.Rai.studycenter.R;
import com.Rai.studycenter.adapters.TimeTable_Adapter;
import com.Rai.studycenter.adapters.quiz_Adapter;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.remoteconfig.FirebaseRemoteConfig;
import com.google.firebase.remoteconfig.FirebaseRemoteConfigSettings;

import java.util.ArrayList;
import java.util.Arrays;

public class timetable extends AppCompatActivity {
    private static final String TAG = "timetable";
    private FirebaseRemoteConfig mFirebaseRemoteConfig;
    private NotificationManager mNotificationManager;
    public ImageView timeTableImage;
    public TextView timeTableText;
    private static final String timetables1="sem_1_timetable";
    private static final String timetables2="sem_2_timetable";
    private RecyclerView recyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;
    ArrayList<String> date=new ArrayList<>();;
    ArrayList<String> subject=new ArrayList<>();;
    ArrayList<String> time=new ArrayList<>();;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.grd_timetable);
        mFirebaseRemoteConfig = FirebaseRemoteConfig.getInstance();
        // [END get_remote_config_instance]

        // Create a Remote Config Setting to enable developer mode, which you can use to increase
        // the number of fetches available per hour during development. Also use Remote Config
        // Setting to set the minimum fetch interval.
        // [START enable_dev_mode]
       /* FirebaseRemoteConfigSettings configSettings = new FirebaseRemoteConfigSettings.Builder()
                .setDeveloperModeEnabled(BuildConfig.DEBUG)
                .setMinimumFetchIntervalInSeconds(3600)
                .build();
       mFirebaseRemoteConfig.setConfigSettings(configSettings);*/
        // [END enable_dev_mode]

        // Set default Remote Config parameter values. An app uses the in-app default values, and
        // when you need to adjust those defaults, you set an updated value for only the values you
        // want to change in the Firebase console. See Best Practices in the README for more
        // information.
        // [START set_default_values]
       // mFirebaseRemoteConfig.setDefaults(R.xml.remote_config_defaults);
        // [END set_default_values]

        fetchWelcome();
        setTitle(R.string.timetable);
        date.clear();
        subject.clear();
        time.clear();
        date.add("10/22/2019");
        subject.add("Operating System");
        time.add("2pm to 4pm");



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
    private void fetchWelcome() {

        // [START fetch_config_with_callback]
        mFirebaseRemoteConfig.fetchAndActivate()
                .addOnCompleteListener(this, new OnCompleteListener<Boolean>() {
                    @RequiresApi(api = Build.VERSION_CODES.O)
                    @Override
                    public void onComplete(@NonNull Task<Boolean> task) {
                        if (task.isSuccessful()) {
                            boolean updated = task.getResult();
                            Log.d(TAG, "Config params updated: " + updated);



                        } else {

                        }
                        check();
                    }
                });
        // [END fetch_config_with_callback]
    }
    public void first(){
        recyclerView = findViewById(R.id.time_table_recycler);
        mLayoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerView.setLayoutManager(mLayoutManager);

        mAdapter = new TimeTable_Adapter(getApplicationContext(), date, subject, time);
        recyclerView.setAdapter(mAdapter);
    }
    public void second(){

        recyclerView = findViewById(R.id.time_table_recycler2);
        mLayoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerView.setLayoutManager(mLayoutManager);

        mAdapter = new TimeTable_Adapter(getApplicationContext(), date, subject, time);
        recyclerView.setAdapter(mAdapter);
    }
    @RequiresApi(api = Build.VERSION_CODES.O)
    private void notifications(String s1,String s2){

        int notifyID = 1;
        String CHANNEL_ID = "my_channel_01";// The id of the channel.
        CharSequence name = getString(R.string.str_body_notification_four);// The user-visible name of the channel.
        int importance = NotificationManager.IMPORTANCE_HIGH;
        NotificationChannel mChannel = new NotificationChannel(CHANNEL_ID, name, importance);
// Create a notification and set the notification channel.
        Notification notification = new Notification.Builder(timetable.this)
                .setContentTitle(s2)
                .setContentText(s1)
                .setSmallIcon(R.drawable.practicals)
                .setChannelId(CHANNEL_ID)
                .build();

        NotificationManager mNotificationManager =
                (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
        mNotificationManager.createNotificationChannel(mChannel);

// Issue the notification.
        mNotificationManager.notify(notifyID , notification);
    }
    @RequiresApi(api = Build.VERSION_CODES.O)
    private void check(){
        String state="true";
        String s1 = mFirebaseRemoteConfig.getString(timetables1);
        String s2 = mFirebaseRemoteConfig.getString(timetables2);
        if(s1.equals("true")){
            notifications(s1,s2);
            first();
            second();
        }
        else if((s1.equals("false")))
        {
            notifications("not Released yet","TimeTable ");
            ImageView my =(ImageView) findViewById(R.id.timetable_img);
            my.setVisibility(View.VISIBLE);
            TextView mytxt =(TextView) findViewById(R.id.timetable_text);
            mytxt.setVisibility(View.VISIBLE);

        }
        else
        {
            notifications("Technical issue occured","TimeTable ");
            ImageView my =(ImageView) findViewById(R.id.timetable_img);
            my.setVisibility(View.VISIBLE);
            TextView mytxt =(TextView) findViewById(R.id.timetable_text);
            mytxt.setVisibility(View.VISIBLE);
        }
    }
}