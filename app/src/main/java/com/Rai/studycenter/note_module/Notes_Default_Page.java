package com.Rai.studycenter.note_module;


import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.Rai.studycenter.R;
import com.Rai.studycenter.adapters.NotesAdapter;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.Objects;

/**
 * A simple {@link Fragment} subclass.
 */
public class Notes_Default_Page extends Fragment {
    SharedPreferences sharedPreferences;
    RecyclerView NotesRecyclerView;
    LinearLayoutManager linearLayoutManager;
    NotesAdapter notesAdapter;
    FloatingActionButton createNoteBtn;
    public Notes_Default_Page() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        // Inflate the layout for this fragment
        View view =inflater.inflate(R.layout.fragment_notes_ocr_default, container, false);
        createNoteBtn = view.findViewById(R.id.fab_new_note_btn);
        NotesRecyclerView =view.findViewById(R.id.notes_recview);
        linearLayoutManager = new LinearLayoutManager(view.getContext());
        NotesRecyclerView.setLayoutManager(linearLayoutManager);
        notesAdapter =new NotesAdapter(view.getContext());
        NotesRecyclerView.setAdapter(notesAdapter);
        notesAdapter.notifyDataSetChanged();

                CreateNewNote();
                return view;
    }
    public void CreateNewNote(){
        createNoteBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(),NewNote.class);
                getActivity().startActivity(intent);

                Toast.makeText(getContext(), "Data has been uploaded", Toast.LENGTH_SHORT).show();
            }
        });
    }




}
