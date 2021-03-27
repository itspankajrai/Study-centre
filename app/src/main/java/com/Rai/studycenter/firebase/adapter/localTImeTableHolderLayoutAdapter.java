package com.Rai.studycenter.firebase.adapter;

import android.content.Context;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.AppCompatSpinner;
import androidx.recyclerview.widget.RecyclerView;

import com.Rai.studycenter.R;
import com.Rai.studycenter.firebase.models.timetableFormat;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.HashMap;

public class localTImeTableHolderLayoutAdapter extends RecyclerView.Adapter<localTImeTableHolderLayoutAdapter.layoutHolder> {
    FirebaseDatabase database ;
    DatabaseReference ref ;
    ArrayList<String> semNames;
    Context mContext;
    ArrayList<String> subject;
    String TAG="Adapter";
    String currentID,currentSem;
    HashMap<Integer, String> dateMap,timeMap,dayMap;

    String[] days=new String[]{"Monday","Tuesday","Wednesday","Thursday","Friday","Saturday","Sunday"};
    public localTImeTableHolderLayoutAdapter(Context context,ArrayList<String> semNames,String currentID,String currentSem){
        this.mContext=context;
        this.semNames=semNames;
        this.currentID=currentID;
        this.currentSem=currentSem;
    }

    @NonNull
    @Override
    public layoutHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        database= FirebaseDatabase.getInstance();
        ref= database.getReference("timetable");
        View View = LayoutInflater.from(parent.getContext()).inflate(R.layout.upload_timetable_layout, parent, false);
        return new layoutHolder(View);
    }

    @Override
    public void onBindViewHolder(@NonNull final layoutHolder holder,  int position) {
        subject=new ArrayList<>();
        dateMap =new HashMap<>();
        timeMap=new HashMap<>();
        dayMap=new HashMap<>();
        holder.TVSubject.setText(semNames.get(position));
        final int key=position;
        ArrayAdapter arrayAdapter=new ArrayAdapter<String>(mContext, android.R.layout.simple_spinner_item,days);
        holder.SpnDay.setAdapter(arrayAdapter);
        holder.SpnDay.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                String value = dayMap.get(key);  // here position is the key
                if (value == null) {
                    dayMap.put(key,adapterView.getItemAtPosition(i).toString());
                } else {
                    dayMap.put(key,adapterView.getItemAtPosition(i).toString());
                    // Key might be present...
                    if (dayMap.containsKey(key)) {
                        // Okay, there's a key but the value is null
                    } else {
                        // Definitely no such key
                    }
                }
                Log.d(TAG, "onBindViewHolder() returned: " + dayMap.toString());
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
        holder.EDTDate.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {

                String value = dateMap.get(key);  // here position is the key
                if (value == null) {
                    dateMap.put(key,holder.EDTDate.getText().toString());
                } else {
                    dateMap.put(key,holder.EDTDate.getText().toString());
                    // Key might be present...
                    if (dateMap.containsKey(key)) {
                        // Okay, there's a key but the value is null
                    } else {
                        // Definitely no such key
                    }
                }
            }
        });
        holder.EDTTime.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                String value = dateMap.get(key);  // here position is the key
                if (value == null) {
                    timeMap.put(key,holder.EDTTime.getText().toString());
                } else {
                    timeMap.put(key,holder.EDTTime.getText().toString());
                    // Key might be present...
                    if (timeMap.containsKey(key)) {
                        // Okay, there's a key but the value is null
                    } else {
                        // Definitely no such key
                    }
                }
                Log.d(TAG, "onBindViewHolder() returned: " + timeMap.toString());
            }
        });
        for (int i = 0; i <semNames.size() ; i++) {
            subject.add(semNames.get(i));
        }



    }

    @Override
    public int getItemCount() {
        return semNames.size();
    }

    public class layoutHolder extends RecyclerView.ViewHolder{
            TextView TVSubject;
            EditText EDTDate,EDTTime;
            AppCompatSpinner SpnDay;

        public layoutHolder(@NonNull View itemView) {
            super(itemView);
            TVSubject=itemView.findViewById(R.id.upload_subject);
            EDTDate=itemView.findViewById(R.id.upload_date);
            EDTTime= itemView.findViewById(R.id.upload_time);
            SpnDay=itemView.findViewById(R.id.upload_day);
        }
    }

    public void update(){
        for (int i = 0; i <semNames.size() ; i++) {
            timetableFormat timetableFormat=new timetableFormat(dayMap.get(i),dateMap.get(i),timeMap.get(i),subject.get(i));
            ref.child(currentID).child(currentSem).child(ref.push().getKey()).setValue(timetableFormat);
        }


    }
}
