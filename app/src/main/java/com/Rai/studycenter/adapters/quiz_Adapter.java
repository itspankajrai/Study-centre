package com.Rai.studycenter.adapters;

import android.content.Context;
import android.content.Intent;
import androidx.recyclerview.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.Rai.studycenter.R;
import android.widget.BaseAdapter;

public class quiz_Adapter extends BaseAdapter {
    private Context context;
    private final String[] sem_list;

    public quiz_Adapter(Context context, String[] sem_list) {
        this.context = context;
        this.sem_list = sem_list;
    }

    public View getView(int position, View convertView, ViewGroup parent) {

        LayoutInflater inflater = (LayoutInflater) context
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        View gridView;

        if (convertView == null) {

            gridView = new View(context);

            // get layout from mobile.xml
            gridView = inflater.inflate(R.layout.adapter_quiz, null);

            // set value into textview
            TextView textView = (TextView) gridView
                    .findViewById(R.id.quiz_sem);
            textView.setText(sem_list[position]);

            // set image based on selected text
            ImageView imageView = (ImageView) gridView
                    .findViewById(R.id.quiz_item_image);

                String mobile = sem_list[position];
                imageView.setImageResource(R.drawable.study_material);


        } else {
            gridView = (View) convertView;
        }

        return gridView;
    }

    @Override
    public int getCount() {
        return sem_list.length;
    }

    @Override
    public Object getItem(int position) {
        return position;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

}