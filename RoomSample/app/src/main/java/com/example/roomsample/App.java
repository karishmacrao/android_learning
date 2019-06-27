package com.example.roomsample;

import android.app.Application;

import com.facebook.stetho.Stetho;

public class App extends Application {

    @Override
    public void onCreate() {
        super.onCreate();

        UserRoomDatabase.init(this);

        Stetho.initializeWithDefaults(this);
    }
}
