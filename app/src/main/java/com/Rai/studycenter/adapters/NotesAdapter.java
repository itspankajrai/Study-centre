package com.Rai.studycenter.adapters;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.ContextMenu;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import com.Rai.studycenter.R;
import com.Rai.studycenter.caching.DBHelper;
import com.Rai.studycenter.models.Notesmodel;
import com.Rai.studycenter.note_module.NewNote;
import com.Rai.studycenter.note_module.NoteViewerActivity;

import java.util.ArrayList;
import java.util.List;

public class NotesAdapter extends RecyclerView.Adapter<NotesAdapter.NotesViewHolder>  {
    Context mContext;
    DBHelper dbHelper;
    ArrayList<String> names,subname;
    List<Notesmodel> notesmodelList;
    ArrayList<String> idArray=new ArrayList<>();
    String currentID;
    public  NotesAdapter(Context mContext){
        this.mContext=mContext;
        dbHelper=new DBHelper(mContext);

        notesmodelList= dbHelper.getNotes();
        names=new ArrayList<>();
        subname=new ArrayList<>();
        for (Notesmodel nl: notesmodelList){
            names.add(nl.getName());
            subname.add(nl.getDesc());
            idArray.add(String.valueOf(nl.getId()));
        }
        Log.d("SqlData", "onBindViewHolder: "+names);
    }
    @NonNull
    @Override
    public NotesViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {


        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.notes_recycler_view, parent, false);
        NotesAdapter.NotesViewHolder NotesHolder = new NotesViewHolder(view);
        return NotesHolder;

    }

    @Override
    public void onBindViewHolder(@NonNull final NotesViewHolder holder, final int position) {
        //getPosition(position);
        currentID=idArray.get(position);
        holder.Note_Title.setText(names.get(position));
        holder.Note_Desc.setText(subname.get(position));
        holder.mainLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent=new Intent(holder.itemView.getContext(), NoteViewerActivity.class);
                        intent.putExtra("noteTitle",names.get(position));
                        intent.putExtra("noteData",subname.get(position));
                holder.itemView.getContext().startActivity(intent);
                Toast.makeText(mContext, "Click at " +position, Toast.LENGTH_SHORT).show();
            }
        });

    }


    @Override
    public int getItemCount() {
        return notesmodelList.size();
    }

    public class NotesViewHolder extends RecyclerView.ViewHolder implements View.OnCreateContextMenuListener {
        public TextView Note_Title,Note_Desc;
        public ConstraintLayout mainLayout;
        public NotesViewHolder(View view) {
            super(view);
            Note_Title = view.findViewById(R.id.notes_rec_title);
            Note_Desc=view.findViewById(R.id.notes_rec_desc);
            mainLayout=view.findViewById(R.id.notes_rec_layout);
            view.setOnCreateContextMenuListener(this);
        }

        @Override
        public void onCreateContextMenu(ContextMenu contextMenu, View view, ContextMenu.ContextMenuInfo contextMenuInfo) {
            contextMenu.setHeaderTitle("MENU");
            final Context context=mContext;
            contextMenu.add("Delete").setOnMenuItemClickListener(new MenuItem.OnMenuItemClickListener() {
                @Override
                public boolean onMenuItemClick(MenuItem menuItem) {
                    dbHelper.deleteNote(notesmodelList.get(getAdapterPosition()));
                    notesmodelList.remove(getAdapterPosition());
                    CharSequence text="delete";
                    Toast toast = Toast.makeText(context, text, Toast.LENGTH_LONG);
                    toast.show();
                    //notifyItemChanged(getAdapterPosition());
                    //notifyItemRemoved(getAdapterPosition());
                    notifyItemChanged(getAdapterPosition());
                    notifyItemRangeRemoved(getAdapterPosition(),notesmodelList.size());
                    return true;
                }
            });
           /* contextMenu.add("Edit").setOnMenuItemClickListener(new MenuItem.OnMenuItemClickListener() {
                @Override
                public boolean onMenuItemClick(MenuItem menuItem) {
                    CharSequence text="Edit";
                    Toast toast = Toast.makeText(context, text, Toast.LENGTH_LONG);
                    toast.show();
                    return true;
                }
            });*/
            contextMenu.add("Update").setOnMenuItemClickListener(new MenuItem.OnMenuItemClickListener() {
                @Override
                public boolean onMenuItemClick(MenuItem menuItem) {
                    CharSequence text="Update";
                    Toast toast = Toast.makeText(context, text, Toast.LENGTH_LONG);
                    toast.show();
                    Intent intent=new Intent(mContext, NewNote.class);
                    intent.putExtra("update_id", currentID);
                    mContext.startActivity(intent);
                    return true;
                }
            });
        }

    }

}
