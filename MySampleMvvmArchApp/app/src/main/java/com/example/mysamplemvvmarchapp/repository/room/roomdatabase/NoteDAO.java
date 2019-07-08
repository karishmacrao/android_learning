package com.example.mysamplemvvmarchapp.repository.room.roomdatabase;

import com.example.mysamplemvvmarchapp.repository.room.roomdatabase.Note;

import java.util.List;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

@Dao
public interface NoteDAO {
    @Insert
    void insert(Note note);

    @Update
    void update(Note note);

    @Delete
    void delete(Note note);

    @Query("delete from note_table")
    void deleteAllNotes();

    @Query("Select * from note_table order by priority desc")
    LiveData<List<Note>> getAllNotes();
}
