package com.Rai.studycenter.utils;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Environment;
import android.util.Log;
import android.widget.Toast;

import androidx.annotation.RequiresApi;
import androidx.core.app.NotificationCompat;

import com.Rai.studycenter.R;
import com.Rai.studycenter.helpers.Pdf_View;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

public class DownloadFileAsync extends AsyncTask<String, String, String> {
    //initialize root directory
    File rootDir = Environment.getExternalStorageDirectory();
    public String fileName;
    public String fileURL;
    public int notificationID=1;
    public String notificationMsg="";
    public Context context;
    public String TAG="Async Task";
    public DownloadFileAsync(Context context,int notificationID,String fileName) {
        this.context=context;
        this.notificationID=notificationID;
        this.fileName=fileName;
    }
        @Override
        protected void onPreExecute() {
            super.onPreExecute();

            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                notifications(Integer.parseInt("0"),"Download Starting","Connecting to Server");
            }
            Log.d(TAG, "onPreExecute() returned: " + "Async Started" );

        }


        @Override
        protected String doInBackground(String... strings) {
                fileURL =strings[0];
            Log.d(TAG, "doInBackground() called with: strings = [" + strings + "]");
            try {
                //connecting to url
                URL u = new URL(fileURL);
                HttpURLConnection c = (HttpURLConnection) u.openConnection();
                c.setRequestMethod("GET");
                c.setDoOutput(true);
                c.getInstanceFollowRedirects();
                c.connect();

                //lenghtOfFile is used for calculating download progress
                int lenghtOfFile = c.getContentLength();
                File folder = new File(Environment.getExternalStorageDirectory() +
                        File.separator + "StudyCenter");

                boolean success = true;
                if (!folder.exists()) {
                    success = folder.mkdir();
                }
                if (success) {
                    boolean existsfile=checkFile(new File(rootDir + "/StudyCenter/", fileName));
                    if(existsfile==true){
                        notificationMsg="file already exists";
                        Log.d(TAG, "doInBackground() called with: strings = [" + strings + "]"+"file already exists"+existsfile);

                    }
                    else {
                        notificationMsg="File Downloaded Successfully";

                        FileOutputStream f = new FileOutputStream(new File(rootDir + "/StudyCenter/", fileName));
                        InputStream in = c.getInputStream();
                        //here’s the download code
                        byte[] buffer = new byte[1024];
                        int len1 = 0;
                        long total = 0;

                        while ((len1 = in.read(buffer)) > 0) {
                            total += len1; //total = total + len1
                            publishProgress("" + (int)((total*100)/lenghtOfFile));
                            f.write(buffer, 0, len1);
                        }

                        f.close();
                    }

                } else if(folder.exists()) {
                    boolean existsfile=checkFile(new File(rootDir + "/StudyCenter/", fileName));
                    if(existsfile==true){
                        notificationMsg="file already exists";

                        Log.d(TAG, "doInBackground() called with: strings = [" + strings + "]"+"file already exists"+existsfile);
                    }
                    else {
                        notificationMsg="File Downloaded Successfully";
                    FileOutputStream f = new FileOutputStream(new File(rootDir + "/StudyCenter/", fileName));
                    InputStream in = c.getInputStream();

                    //here’s the download code
                    byte[] buffer = new byte[1024];
                    int len1 = 0;
                    long total = 0;

                    while ((len1 = in.read(buffer)) > 0) {
                        total += len1; //total = total + len1
                        publishProgress("" + (int)((total*100)/lenghtOfFile));
                        f.write(buffer, 0, len1);
                    }
                    f.close();
                    }
                }
                //this is where the file will be seen after the download
               // FileOutputStream f = new FileOutputStream(new File(rootDir + "/StudyCenter/", fileName));
                //file input is from the url


            } catch (Exception ex) {
                String err = (ex.getMessage()==null)?"SD Card failed":ex.getMessage();
                Log.d("LOG_TAG", err);
            }

            return null;
        }

        @RequiresApi(api = Build.VERSION_CODES.O)
        protected void onProgressUpdate(String...  progress) {
            Log.d("LOG_TAG",progress[0]);

            notifications(Integer.parseInt(progress[0]),"Downloading","100/"+progress[0]);


        }

        @Override
        protected void onPostExecute(String unused) {
            //dismiss the dialog after the file was downloaded

            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                notifications(Integer.parseInt("100"),"Downloaded",notificationMsg);
            }

        }

    @RequiresApi(api = Build.VERSION_CODES.O)
    private void notifications(int inta ,String title,String content){


        Intent pdfViewAcitivity = new Intent(context, Pdf_View.class);
        pdfViewAcitivity.putExtra("pdf_path", fileName);
        String CHANNEL_ID = "abc";// The id of the channel.
        CharSequence name = "Download Notifications";
        int flags = PendingIntent.FLAG_UPDATE_CURRENT; // cancel old intent and create new one
        PendingIntent pIntent = PendingIntent.getActivity(context, notificationID, pdfViewAcitivity, flags);
        int importance = NotificationManager.IMPORTANCE_HIGH;
        NotificationChannel mChannel = new NotificationChannel(CHANNEL_ID, name, importance);
        Notification noti = new NotificationCompat.Builder(context,CHANNEL_ID)
                .setSmallIcon(R.drawable.dl_files)
                .setContentTitle(title)
                .setContentText(content)
                .setProgress(100, inta, false)
                .setContentIntent(pIntent)
                .setOnlyAlertOnce(true)
                .setAutoCancel(true) // Hides the notification after its been selected
                .build();
        // Get the notification manager system service
        NotificationManager mNotificationManager =
                (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);
        mNotificationManager.createNotificationChannel(mChannel);
// mId allows you to update the notification later on.
        mNotificationManager.notify(notificationID, noti);
    }
    public Boolean checkFile(File currentFile){
        boolean file=false;
        if(currentFile.exists()){
            file=true;
        }
        return file;
    }
}