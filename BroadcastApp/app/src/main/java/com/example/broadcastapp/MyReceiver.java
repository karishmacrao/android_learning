package com.example.broadcastapp;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;

public class MyReceiver extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
        Log.d("OnReceive() ","received");
        Toast.makeText(context,"Intent Detected",Toast.LENGTH_LONG).show();

        String intentAction = intent.getAction();
        if(intentAction.equals(Intent.ACTION_AIRPLANE_MODE_CHANGED))
        {
            Toast.makeText(context,"Airplane mode changed",Toast.LENGTH_LONG).show();
        }

    }
}
