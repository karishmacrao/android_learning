package com.example.roomsample;

import android.app.Application;

public class App extends Application {

    @Override
    public void onCreate() {
        super.onCreate();

        UserRoomDatabase.init(this);
    }
}
