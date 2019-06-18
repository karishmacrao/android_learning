package com.example.roomsample;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProviders;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class ActivityThree extends AppCompatActivity {
    UserViewModel mUserViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_three);
    }

    public void onStart() {
        super.onStart();

        final TextView tv1 = (TextView) findViewById(R.id.name3);
        final TextView tv2 = (TextView) findViewById(R.id.phone3);
        final TextView tv3 = (TextView) findViewById(R.id.email3);
        final TextView tv4 = (TextView) findViewById(R.id.address3);

        mUserViewModel = ViewModelProviders.of(this).get(UserViewModel.class);

        final Bundle bundle = getIntent().getExtras();
        final int id = bundle.getInt("id");

        final User user = mUserViewModel.findById(id);

        tv1.setText(user.name);
        tv2.setText(user.phone);
        tv3.setText(user.email);
        tv4.setText(user.address);

        Button save = (Button) findViewById(R.id.saveBtn);

        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String uname, uphone, uemail, uaddress;

                uname = ((EditText) findViewById(R.id.name3)).getText().toString();
                uphone = ((EditText) findViewById(R.id.phone3)).getText().toString();
                uemail = ((EditText) findViewById(R.id.email3)).getText().toString();
                uaddress = ((EditText) findViewById(R.id.address3)).getText().toString();
                user.setName(uname);
                user.setEmail(uemail);
                user.setPhone(uphone);
                user.setAddress(uaddress);
                mUserViewModel.updated(user);
                finish();
            }
        });
    }
}
