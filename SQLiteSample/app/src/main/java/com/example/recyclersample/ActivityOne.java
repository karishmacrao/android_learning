package com.example.recyclersample;

import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.dialog.MaterialAlertDialogBuilder;

public class ActivityOne extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_one);

        Button submitbtn = (Button) findViewById(R.id.submit);
        Button viewbtn = (Button) findViewById(R.id.viewlist);

//        new MaterialAlertDialogBuilder(this)
//                .setTitle(R.string.app_name)
//                .setMessage("Message")
//                .setPositiveButton("Ok", new DialogInterface.OnClickListener() {
//                    @Override
//                    public void onClick(DialogInterface dialog, int which) {
//                        Log.d("MM", "onClick() called with: dialog = [" + dialog + "], which = [" + which + "]");
//                    }
//                })
//                .show();

        final LinearLayout dynamicView = findViewById(R.id.dynamicView);

        submitbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                View view = LayoutInflater.from(ActivityOne.this).inflate(R.layout.row_dynamic, null);
                EditText sample = view.findViewById(R.id.sampleEt);
                int count = dynamicView.getChildCount();
                sample.setText("Screen is " + (count + 1));
                dynamicView.addView(view);
//                Intent intent = new Intent(ActivityOne.this, MainActivity.class);
//                EditText my_name = (EditText) findViewById(R.id.name);
//                String name = my_name.getText().toString();
//                EditText my_email = (EditText) findViewById(R.id.email);
//                String email = my_email.getText().toString();
//                EditText my_phone = (EditText) findViewById(R.id.phone);
//                String phone = my_phone.getText().toString();
//                EditText my_address = (EditText) findViewById(R.id.address);
//                String address = my_address.getText().toString();
//                if (name.length() > 1 && email.length() > 1 && phone.length() == 10 && address.length() > 1) {
//                    intent.putExtra("NAME", name);
//                    intent.putExtra("NUMBER", phone);
//                    intent.putExtra("EMAIL", email);
//                    intent.putExtra("ADDRESS", address);
//
//                    FeedEntryDao feedDao = new FeedEntryDao();
//                    feedDao.insert(name, phone, email, address);
//                    startActivity(intent);
//                } else {
//                    Toast.makeText(ActivityOne.this, "Enter all fields with valid details", Toast.LENGTH_LONG).show();
//                }
//
            }
        });

        viewbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ActivityOne.this, MainActivity.class);
                startActivity(intent);
            }
        });
    }
}
