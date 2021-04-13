package com.Rai.studycenter.helpers;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.Rai.studycenter.R;

public class practical_list_ui extends AppCompatActivity {
    ListView listView;
   String[] video_ids;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.practical_list_ui);
        String[] names= getIntent().getStringArrayExtra("video_titles");
        video_ids= getIntent().getStringArrayExtra("video_ids");
        listView=findViewById(R.id.practical_ui_list);
        ArrayAdapter adapter = new ArrayAdapter<String>(practical_list_ui.this,android.R.layout.simple_list_item_1,android.R.id.text1,names);
        listView.setAdapter(adapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent intent=new Intent(practical_list_ui.this, YoutubePlayInApp.class);
                intent.putExtra("video_id",video_ids[i]);
                startActivity(intent);
            }
        });
    }
}