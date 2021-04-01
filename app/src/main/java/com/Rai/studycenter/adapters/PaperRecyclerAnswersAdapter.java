package com.Rai.studycenter.adapters;

import android.content.Context;
import androidx.recyclerview.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.Rai.studycenter.R;
import com.Rai.studycenter.models.solvedPaper;

import java.util.ArrayList;
import java.util.List;

public class PaperRecyclerAnswersAdapter extends RecyclerView.Adapter<PaperRecyclerAnswersAdapter.ViewHolder> {
    List<solvedPaper> questionData;
    Context context;
    private int mExpandedPosition =-1;
    ArrayList<String> question,answer;

    public PaperRecyclerAnswersAdapter(Context applicationContext,  List<solvedPaper> questionData) {
        this.context = applicationContext;
        this.questionData=questionData;
        question=new ArrayList<>();
        answer=new ArrayList<>();
        for(solvedPaper paper:questionData){
            question.add(paper.getQuestion());
            answer.add(paper.getAnswers());
        }
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {

        public TextView idTextView;
        public TextView titleTextView;
        public TextView descriptionTextView;
        public Button btn;
        public Button btn2;
        public  LinearLayout expand;
        public  LinearLayout collapse;
        public TextView mu_question;
        public TextView mu_answer;
        public ViewHolder(View itemView) {
            super(itemView);
            idTextView = itemView.findViewById(R.id.no);
            titleTextView = itemView.findViewById(R.id.title);
            descriptionTextView = itemView.findViewById(R.id.description);
            btn = itemView.findViewById(R.id.expand);
            btn2 = itemView.findViewById(R.id.collap);
            expand = (LinearLayout)itemView.findViewById(R.id.expand_card);
            collapse = (LinearLayout)itemView.findViewById(R.id.collapase);
            mu_question=itemView.findViewById(R.id.mu_quest);
            mu_answer=itemView.findViewById(R.id.mu_ans);

            btn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    expand.setVisibility(View.VISIBLE);
                    collapse.setVisibility(View.GONE);
                }
            });
            btn2.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    //linearLayout.setVisibility(View.GONE);
                    expand.setVisibility(View.GONE);
                    collapse.setVisibility(View.VISIBLE);
                }
            });
        }

    }

    @Override
    public PaperRecyclerAnswersAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.adapter_paper_answer, parent, false);

        PaperRecyclerAnswersAdapter.ViewHolder viewHolder = new PaperRecyclerAnswersAdapter.ViewHolder(view);

        return viewHolder;
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, final int position) {

        holder.titleTextView.setText(question.get(position));
        holder.descriptionTextView.setText("Tap to show answer...");
        holder.mu_question.setText(question.get(position));
        holder.mu_answer.setText(answer.get(position));
        final boolean isExpanded = position==mExpandedPosition;
        holder.collapse.setVisibility(isExpanded?View.GONE:View.VISIBLE);
        holder.expand.setVisibility(isExpanded?View.VISIBLE:View.GONE);
        holder.itemView.setActivated(isExpanded);

        holder.itemView.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                mExpandedPosition = isExpanded ? -1:position;
                notifyDataSetChanged();
            }
        });
    }

    @Override
    public int getItemCount() {
        return question.size();
    }


}