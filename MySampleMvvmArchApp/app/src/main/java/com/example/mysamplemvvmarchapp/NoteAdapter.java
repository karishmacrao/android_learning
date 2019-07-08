package com.example.mysamplemvvmarchapp;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.mysamplemvvmarchapp.repository.room.roomdatabase.Note;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class NoteAdapter extends RecyclerView.Adapter<NoteAdapter.NoteHolder> {
    private List<Note> notes=new ArrayList<>();
    @NonNull
    @Override
    public NoteHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.note_items,parent,false);
        return new NoteHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull NoteHolder holder, int position) {
        Note currentNote=notes.get(position);
        holder.textViewTitle.setText(currentNote.getTitle());
        holder.textViewDescription.setText(currentNote.getDescription());
        holder.textViewPriority.setText(""+currentNote.getPriority());

    }

    @Override
    public int getItemCount() {
        return notes.size();
    }

    public void setNotes(List<Note> notes)
    {
        this.notes=notes;
        notifyDataSetChanged();
    }

    public Note getNoteAt(int position)
    {
        return notes.get(position);
    }

    class NoteHolder extends RecyclerView.ViewHolder{
        private TextView textViewTitle,textViewDescription,textViewPriority;

        public NoteHolder(@NonNull View itemView) {
            super(itemView);
            textViewTitle=itemView.findViewById(R.id.tvTitle);
            textViewDescription=itemView.findViewById(R.id.tvDescription);
            textViewPriority=itemView.findViewById(R.id.tvPriority);
        }
    }
}
