package com.Rai.studycenter.adapters;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.Rai.studycenter.R;
import com.Rai.studycenter.mock_test.mock_testmcq;

import java.util.ArrayList;



public class TimeTable_Adapter extends RecyclerView.Adapter<TimeTable_Adapter.ViewHolder> {
    ArrayList<String> date = new ArrayList<>();
    ArrayList<String> subject = new ArrayList<>();
    ArrayList<String> time = new ArrayList<>();
    Context context;

    public TimeTable_Adapter(Context applicationContext, ArrayList<String> date, ArrayList<String> subject, ArrayList<String> time) {

        this.context = applicationContext;
        this.date = date;
        this.subject = subject;
        this.time = time;

    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        public TextView edtdate;
        public TextView edtsubject;
        public TextView edttime;

        public ViewHolder(View itemView) {
            super(itemView);
            edtdate = itemView.findViewById(R.id.time_table_date);
            edtsubject = itemView.findViewById(R.id.time_table_subject);
            edttime = itemView.findViewById(R.id.time_table_time);
        }

    }

    @Override
    public TimeTable_Adapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.time_table_adapter, parent, false);

        TimeTable_Adapter.ViewHolder viewHolder = new TimeTable_Adapter.ViewHolder(view);

        return viewHolder;
    }

    @Override
    public void onBindViewHolder(final TimeTable_Adapter.ViewHolder holder, final int position) {

        holder.edtdate.setText(date.get(position));
        holder.edtsubject.setText(subject.get(position));
        holder.edttime.setText(time.get(position));;
        holder.itemView.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                switch (position){

                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return date.size();

    }


}