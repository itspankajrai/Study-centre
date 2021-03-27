package com.Rai.studycenter.firebase.adapter;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.Rai.studycenter.R;
import com.Rai.studycenter.firebase.models.subjectsData;
import com.Rai.studycenter.firebase.models.timetableFormat;
import com.Rai.studycenter.firebase.uploads.upload;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;

import java.util.HashMap;

public class firebaseTimeTableAdapter extends FirebaseRecyclerAdapter<timetableFormat,firebaseTimeTableAdapter.dataholder> {
    public String TAG="Adapter";


    /**
     * Initialize a {@link RecyclerView.Adapter} that listens to a Firebase query. See
     * {@link FirebaseRecyclerOptions} for configuration options.
     *
     * @param options
     */
    public firebaseTimeTableAdapter(@NonNull FirebaseRecyclerOptions<timetableFormat> options) {
        super(options);
    }

    @Override
    protected void onBindViewHolder(@NonNull dataholder holder, int position, @NonNull timetableFormat model) {
        holder.tvday.setText(model.getDay());
        holder.tvdate.setText(model.getDate());
        holder.tvsubject.setText(model.getSubject());
        holder.tvtime.setText(model.getTime());
        Log.d(TAG, "onBindViewHolder() returned: " +model.getSubject() );


    }


    @NonNull
    @Override
    public dataholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {


        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.firebase_timetable_layout, parent, false);

        return new dataholder(view);
    }

    public class dataholder extends  RecyclerView.ViewHolder{
        TextView tvday,tvdate,tvsubject,tvtime;
        public dataholder(@NonNull View itemView) {
            super(itemView);
            tvday=itemView.findViewById(R.id.tvDay);
            tvdate=itemView.findViewById(R.id.tvDate);
            tvsubject=itemView.findViewById(R.id.tvSubjects);
            tvtime=itemView.findViewById(R.id.tvTime);

        }
    }
}
