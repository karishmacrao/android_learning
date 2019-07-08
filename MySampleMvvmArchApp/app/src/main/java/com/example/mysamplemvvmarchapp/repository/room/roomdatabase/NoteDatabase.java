package com.example.mysamplemvvmarchapp.repository.room.roomdatabase;

import android.content.Context;
import android.os.AsyncTask;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteDatabase;

@Database(entities = {Note.class},version = 1)
public abstract class NoteDatabase extends RoomDatabase {
    //Making this class singleton as only one time database connection should be established
    private static NoteDatabase instance;

    public abstract NoteDAO noteDAO();

    public static synchronized NoteDatabase getInstance(Context context)
    {
        if(instance==null)
        {
            instance= Room.databaseBuilder(context.getApplicationContext(),
                    NoteDatabase.class,"note_databse")
                    .fallbackToDestructiveMigration()
                    .addCallback(roomCallback)
                    .build();
        }
        return instance;
    }

    private static RoomDatabase.Callback roomCallback=new RoomDatabase.Callback(){

        @Override
        public void onCreate(@NonNull SupportSQLiteDatabase db) {
            super.onCreate(db);
        }
    };

    public static class PopulateDbAsyncTask extends AsyncTask<Void,Void,Void>
    {
        private NoteDAO noteDAO;
         private PopulateDbAsyncTask(NoteDatabase db)
         {
             noteDAO=db.noteDAO();
         }

        @Override
        protected Void doInBackground(Void... voids) {
             noteDAO.insert(new Note("title1","description1",1) );
             noteDAO.insert(new Note("title2","description2",2) );
            return null;
        }
    }



}
