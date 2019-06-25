package com.example.mycamapp;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ActionBar;
import android.app.Activity;
import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.FrameLayout;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


    }

    public void onStart() {

        super.onStart();

        ImageButton camBtn = (ImageButton) findViewById(R.id.camIdImg);
        camBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                popupDialog(v);
            }

            private void popupDialog(View v) {
                Dialog dialog = new Dialog(MainActivity.this);
                dialog.setContentView(R.layout.option_layout);
                final ImageButton op2 =(ImageButton)dialog.getWindow().findViewById(R.id.gallery);
                final ImageButton op1 =(ImageButton)dialog.getWindow().findViewById(R.id.camIdImg);
                dialog.setTitle("Select One Option");
                dialog.show();
                op1.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent camIntent = new Intent(MainActivity.this,ActivityCamera.class);
                        startActivity(camIntent);
                    }
                });

                op2.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent camIntent = new Intent(MainActivity.this,ActivityCamera.class);
                        startActivity(camIntent);
                    }
                });
            }
        });




    }
}
