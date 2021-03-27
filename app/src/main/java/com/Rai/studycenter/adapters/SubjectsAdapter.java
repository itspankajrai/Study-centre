package com.Rai.studycenter.adapters;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Environment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListAdapter;
import android.widget.TextView;
import android.widget.Toast;
import com.Rai.studycenter.R;
import com.Rai.studycenter.utils.DownloadFileAsync;

import java.util.ArrayList;



public class SubjectsAdapter extends BaseAdapter implements ListAdapter {
    private ArrayList<String> list ;
    private Context context;
    public static final String LOG_TAG = "Android";
    int notifyID=1;
    public String fileName;
    public String fileURL;
    String[] url;
    public SubjectsAdapter(ArrayList<String> list,String[] url, Context context) {
        this.list = list;
        this.url=url;
        this.context = context;
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int pos) {
        return list.get(pos);
    }


    @Override
    public long getItemId(int pos) {
        return 0;
    }

    @Override
    public boolean isEnabled(int position)
    {
        return true;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        View view = convertView;
        if (view == null) {
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = inflater.inflate(R.layout.adapter_listview, null);
        }

        //Handle TextView and display string from your list
        TextView listItemText = (TextView)view.findViewById(R.id.list_items);

        listItemText.setText(list.get(position));

        final String book_name = list.get(position);
        final String book_url = url[position];
        ImageView down_btn = (ImageView) view.findViewById(R.id.img_btn);
        down_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                download_CurrentBook(notifyID,book_name+".pdf",book_url);
            }
        });

        return view;
    }


    void download_CurrentBook(int id,String fileName,String fileURL){
        new DownloadFileAsync(context,id,fileName).execute(fileURL);
    }
    private void toast(String txt){
        Toast.makeText(context,txt,Toast.LENGTH_SHORT).show();
    }
}