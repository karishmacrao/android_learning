package com.example.roomsample;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelProviders;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import static com.example.roomsample.ActivityOne.NEW_USER_ACTIVITY_REQUEST_CODE;

public class MainActivity extends AppCompatActivity {
    private UserViewModel mUserViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mUserViewModel = ViewModelProviders.of(this).get(UserViewModel.class);
    }


    protected void onStart() {
        super.onStart();

        Button submitBtn = (Button) findViewById(R.id.submit);
        Button viewBtn = (Button) findViewById(R.id.viewlist);

        final EditText nameEt = (EditText) findViewById(R.id.name);
        final EditText phoneEt = (EditText) findViewById(R.id.phone);
        final EditText emailEt = (EditText) findViewById(R.id.email);
        final EditText addressEt = (EditText) findViewById(R.id.address);

        submitBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String name = nameEt.getText().toString();
                String phone = phoneEt.getText().toString();
                String email = emailEt.getText().toString();
                String address = addressEt.getText().toString();

                Intent intent = new Intent(MainActivity.this, ActivityOne.class);
                if (TextUtils.isEmpty(name)) {
                    Toast.makeText(MainActivity.this, "Name field is empty", Toast.LENGTH_LONG).show();
                } else if (TextUtils.isEmpty(phone)) {
                    Toast.makeText(MainActivity.this, "Phone field is empty", Toast.LENGTH_LONG).show();
                } else if (TextUtils.isEmpty(email)) {
                    Toast.makeText(MainActivity.this, "Email field is empty", Toast.LENGTH_LONG).show();
                } else if (TextUtils.isEmpty(address)) {
                    Toast.makeText(MainActivity.this, "Address field is empty", Toast.LENGTH_LONG).show();
                } else {
                    User user = new User(name, phone, email, address);
                    mUserViewModel.insert(user);

                    nameEt.setText("");
                    phoneEt.setText("");
                    emailEt.setText("");
                    addressEt.setText("");

                    startActivity(intent);
                }
            }
        });


        viewBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, ActivityOne.class);
                startActivity(intent);
            }
        });
    }
}
