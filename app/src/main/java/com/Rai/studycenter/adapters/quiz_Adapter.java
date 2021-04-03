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
/**
public class quiz_Adapter extends RecyclerView.Adapter<quiz_Adapter.ViewHolder> {
    ArrayList<String> id = new ArrayList<>();
    ArrayList<String> title = new ArrayList<>();
    ArrayList<String> description = new ArrayList<>();
    Context context;
    private int mExpandedPosition;

    public quiz_Adapter(Context applicationContext, ArrayList<String> id, ArrayList<String> title, ArrayList<String> description) {

        this.context = applicationContext;
        this.id = id;
        this.title = title;
        this.description = description;

    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        public TextView idTextView;
        public TextView titleTextView;
        public TextView descriptionTextView;
        public ImageView imageView;

        public ViewHolder(View itemView) {
            super(itemView);
            idTextView = itemView.findViewById(R.id.quiz_sem);
            imageView = itemView.findViewById(R.id.grid_item_image);
           // titleTextView = itemView.findViewById(R.id.quiz_sub_title);
            //descriptionTextView = itemView.findViewById(R.id.quiz_btn);
        }

    }

    @Override
    public quiz_Adapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.adapter_quiz, parent, false);

        quiz_Adapter.ViewHolder viewHolder = new quiz_Adapter.ViewHolder(view);

        return viewHolder;
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, final int position) {

        holder.idTextView.setText(id.get(position));
       // holder.titleTextView.setText(title.get(position));
       // holder.descriptionTextView.setText(description.get(position));;
        holder.itemView.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                switch (position){
                    case 0: Intent in = new Intent(context, mock_testmcq.class);
                        in.putExtra("mcq", questions);
                        in.putExtra("ans", answers);
                        in.putExtra("crt", opt);
                        in.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                        context.startActivity(in);
                        break;
                    case 1:  Intent i1 = new Intent (context, mock_testmcq.class);
                        i1.putExtra("mcq", questions2);
                        i1.putExtra("ans", answers2);
                        i1.putExtra("crt", opt2);
                        i1.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                        context.startActivity(i1);
                        break;
                }
            }
        });


    }

    @Override
    public int getItemCount() {
        return id.size();
    }


} **/