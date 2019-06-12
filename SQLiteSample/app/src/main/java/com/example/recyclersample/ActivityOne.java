package com.example.recyclersample;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class ActivityOne extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_one);

        Button submitbtn = (Button) findViewById(R.id.submit);
        Button viewbtn = (Button) findViewById(R.id.viewlist);
        submitbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ActivityOne.this, MainActivity.class);
                EditText my_name = (EditText) findViewById(R.id.name);
                String name = my_name.getText().toString();
                EditText my_email = (EditText) findViewById(R.id.email);
                String email = my_email.getText().toString();
                EditText my_phone = (EditText) findViewById(R.id.phone);
                String phone = my_phone.getText().toString();
                EditText my_address = (EditText) findViewById(R.id.address);
                String address = my_address.getText().toString();
                if (name.length() > 1 && email.length() > 1 && phone.length() == 10 && address.length() > 1) {
                    intent.putExtra("NAME", name);
                    intent.putExtra("NUMBER", phone);
                    intent.putExtra("EMAIL", email);
                    intent.putExtra("ADDRESS", address);

                    FeedEntryDao feedDao = new FeedEntryDao();
                    feedDao.insert(name, phone, email, address);
                    startActivity(intent);
                } else {
                    Toast.makeText(ActivityOne.this, "Enter all fields with valid details", Toast.LENGTH_LONG).show();
                }

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
