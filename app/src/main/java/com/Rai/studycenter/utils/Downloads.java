package com.Rai.studycenter.utils;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.Environment;
import android.util.Log;
import android.view.ContextMenu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.Rai.studycenter.R;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.text.DecimalFormat;
import java.util.ArrayList;

public class Downloads extends AppCompatActivity {
    File rootDir = Environment.getExternalStorageDirectory();
    ListView Download_list;
    ArrayAdapter adapter;
    int itemPosition;
    int Position;
    ArrayList<String> file_list = new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.downloads_activity);

        File folder = new File(Environment.getExternalStorageDirectory() +
                File.separator + "StudyCenter/");
        Download_list=findViewById(R.id.download_list);
        boolean success = true;
        if (!folder.exists()) {
            success = folder.mkdir();
        }
        if (success) {
            String path = Environment.getExternalStorageDirectory().toString()+"/StudyCenter";
            Log.d("Files", "Path: " + path);
            File directory = new File(path);
            File[] files = directory.listFiles();
            Log.d("Files", "Size: "+ files.length);
            for (int i = 0; i < files.length; i++)
            {
                file_list.add(files[i].getName()+"\n Size : "+formatFileSize(files[i].length()));

                Log.d("Files", "FileName:" + files[i].getName()+files[i].getTotalSpace());
            }

            adapter = new ArrayAdapter<String>(this,
                    android.R.layout.simple_list_item_1, file_list);
            Download_list.setAdapter(adapter);
        }
        Download_list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                itemPosition    = i;
                String  itemValue    = (String) Download_list.getItemAtPosition(i);
                Toast.makeText(Downloads.this, "you clicked at :"+itemPosition+"  "+itemValue, Toast.LENGTH_SHORT).show();
            }
        });
        registerForContextMenu(Download_list);
    }

    public static String formatFileSize(long size) {
        String hrSize = null;

        double b = size;
        double k = size/1024.0;
        double m = ((size/1024.0)/1024.0);
        double g = (((size/1024.0)/1024.0)/1024.0);
        double t = ((((size/1024.0)/1024.0)/1024.0)/1024.0);

        DecimalFormat dec = new DecimalFormat("0.00");

        if ( t>1 ) {
            hrSize = dec.format(t).concat(" TB");
        } else if ( g>1 ) {
            hrSize = dec.format(g).concat(" GB");
        } else if ( m>1 ) {
            hrSize = dec.format(m).concat(" MB");
        } else if ( k>1 ) {
            hrSize = dec.format(k).concat(" KB");
        } else {
            hrSize = dec.format(b).concat(" Bytes");
        }

        return hrSize;
    }
    @Override
    public void onCreateContextMenu(ContextMenu menu, View v,
                                    ContextMenu.ContextMenuInfo menuInfo) {
        // TODO Auto-generated method stub
        super.onCreateContextMenu(menu, v, menuInfo);
        MenuInflater m = getMenuInflater();
        m.inflate(R.menu.download_menu, menu);
    }

    @Override
    public boolean onContextItemSelected(MenuItem item) {
        switch(item.getItemId()){
            case R.id.delete_item:
                AdapterView.AdapterContextMenuInfo info = (AdapterView.AdapterContextMenuInfo) item.getMenuInfo();
                Position = (int) info.id;
                file_list.remove(item);

                this.adapter.notifyDataSetChanged();
                return true;
        }
        return super.onContextItemSelected(item);
    }


}
