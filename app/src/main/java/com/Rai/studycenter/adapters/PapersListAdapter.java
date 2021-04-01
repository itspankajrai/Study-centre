package com.Rai.studycenter.adapters;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.recyclerview.widget.RecyclerView;

import com.Rai.studycenter.R;
import com.Rai.studycenter.papers.paper_display;

import java.util.ArrayList;

public class PapersListAdapter extends RecyclerView.Adapter<PapersListAdapter.ViewHolder> {
    ArrayList<String> main_text;

    Context context;
    private int mExpandedPosition =-1;

    public PapersListAdapter(Context applicationContext, ArrayList<String> main_text) {

        this.context = applicationContext;
        this.main_text = main_text;
  
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        public TextView newtext;

        public Button btn;
        public Button btn2;
        public Button btn3;
        public LinearLayout c_1;
        public  LinearLayout c_2;
        public ViewHolder(View itemView) {
            super(itemView);
            newtext = itemView.findViewById(R.id.change_layout_text);
            btn = itemView.findViewById(R.id.change_layout_bt1);
            btn3 = itemView.findViewById(R.id.change_layout_bt2);
            btn2 = itemView.findViewById(R.id.change_layout_bt3);
            c_1 = (LinearLayout)itemView.findViewById(R.id.c1);
            c_2 = (LinearLayout)itemView.findViewById(R.id.c2);
            
        }

    }

    @Override
    public PapersListAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.paper_list_adapter, parent, false);
        PapersListAdapter.ViewHolder viewHolder = new PapersListAdapter.ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(final PapersListAdapter.ViewHolder holder, final int position) {

        holder.newtext.setText(main_text.get(position));
        final boolean isExpanded = position==mExpandedPosition;
        holder.c_1.setVisibility(isExpanded?View.GONE:View.VISIBLE);
        holder.c_2.setVisibility(isExpanded?View.VISIBLE:View.GONE);
        holder.itemView.setActivated(isExpanded);
        holder.btn.setVisibility(View.VISIBLE);
        holder.btn2.setVisibility(View.VISIBLE);
        holder.btn3.setVisibility(View.VISIBLE);

        //button maps
        holder.btn.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View v) {
                switch(position){
                    default:
                        Intent i1 = new Intent (context, paper_display.class);
                        i1.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                        context.startActivity(i1);
                        break;
                }
            }
        });
        holder.btn2.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View v) {
                switch(position){
                     default:
                         Intent i1 = new Intent (context, paper_display.class);
                         i1.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                         context.startActivity(i1);
                        break;

                }
            }
        });
        holder.btn3.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View v) {
                switch (position){
                    case 0:
                        Intent i1 = new Intent (context, paper_display.class);
                        i1.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                        context.startActivity(i1);
                        break;
                    case 1:
                        Intent i2 = new Intent (context, paper_display.class);
                        i2.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                        context.startActivity(i2);
                        break;
                    case 2:
                        Intent i3 = new Intent (context, paper_display.class);
                        i3.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                        context.startActivity(i3);
                        break;
                    case 3:
                        Intent i4 = new Intent (context, paper_display.class);
                        i4.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                        context.startActivity(i4);
                        break;
                    case 4:
                        Intent i5 = new Intent (context, paper_display.class);
                        i5.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                        context.startActivity(i5);
                        break;
                    case 5:
                        Intent i6 = new Intent (context, paper_display.class);
                        i6.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                        context.startActivity(i6);
                        break;
                    default:
                        Toast.makeText(context,"Not found",Toast.LENGTH_SHORT).show();
                        break;

                }
            }
        });

        //button map end
        holder.itemView.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View view) {
                mExpandedPosition = isExpanded ? -1:position;
                notifyDataSetChanged();


            }
        });
    }

    @Override
    public int getItemCount() {
        return main_text.size();
    }



}
