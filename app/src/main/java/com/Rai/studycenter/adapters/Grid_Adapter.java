package com.Rai.studycenter.adapters;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.Rai.studycenter.R;

public class Grid_Adapter extends BaseAdapter {
    private Context context;
    private final String[] OPTION_main;
    public Grid_Adapter(Context context, String[] OPTION_main) {
        this.context = context;
        this.OPTION_main = OPTION_main;
    }
    public View getView(int position, View convertView, ViewGroup parent) {

        LayoutInflater inflater = (LayoutInflater) context
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View gridView;
        if (convertView == null) {
            gridView = new View(context);

            gridView = inflater.inflate(R.layout.adapter_grid_view, null);
            TextView textView = (TextView) gridView
                    .findViewById(R.id.grid_item_label);
            textView.setText(OPTION_main[position]);
            ImageView imageView = (ImageView) gridView
                    .findViewById(R.id.grid_item_image);
            String mobile = OPTION_main[position];
            if (mobile.equals("Study Material")) {
                imageView.setImageResource(R.drawable.study_material);
            }
            else if (mobile.equals("Practicals")) {
                imageView.setImageResource(R.drawable.practicals);
            }
            else if (mobile.equals("Mock Test")) {
                imageView.setImageResource(R.drawable.mock_test);
            }
            else if(mobile.equals("Solved Papers")){
                imageView.setImageResource(R.drawable.papers);
            }
            else if(mobile.equals("Black Book")){
                imageView.setImageResource(R.drawable.black_book);
            }
            else if(mobile.equals("Time Table")){
                imageView.setImageResource(R.drawable.time_table);
            }
            else if(mobile.equals("Notes")){
                imageView.setImageResource(R.drawable.notes);
            }
            else if(mobile.equals("Result")){
                imageView.setImageResource(R.drawable.developer);
            }
        } else {
            gridView = (View) convertView;
        }

        return gridView;
    }

    @Override
    public int getCount() {
        return OPTION_main.length;
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

}