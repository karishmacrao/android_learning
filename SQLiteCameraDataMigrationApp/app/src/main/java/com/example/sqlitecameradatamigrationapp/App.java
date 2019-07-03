package com.example.sqlitecameradatamigrationapp;

import android.app.Application;

public class App extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        FeedReaderDbHelper.init(this);

    }
}
