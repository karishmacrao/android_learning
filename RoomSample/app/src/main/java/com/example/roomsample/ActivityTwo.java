package com.example.roomsample;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.lifecycle.ViewModelProviders;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class ActivityTwo extends AppCompatActivity {
    private static final int ON_DELETE = 1;
    int id;
    String name, phone, email, address;
    private UserViewModel mUserViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_two);
        Toolbar myToolbar = (Toolbar) findViewById(R.id.my_toolbar);
        setSupportActionBar(myToolbar);

        ActionBar ab = getSupportActionBar();

        assert ab != null;
        ab.setDisplayHomeAsUpEnabled(true);

    }

    public void onStart() {
        super.onStart();
        mUserViewModel = ViewModelProviders.of(this).get(UserViewModel.class);

        final TextView tv1 = (TextView) findViewById(R.id.tname);
        final TextView tv2 = (TextView) findViewById(R.id.tphone);
        final TextView tv3 = (TextView) findViewById(R.id.temail);
        final TextView tv4 = (TextView) findViewById(R.id.taddress);
        final Button editBtn = (Button) findViewById(R.id.teditBtn);
        final Button deleteBtn = (Button) findViewById(R.id.tdeleteBtn);

        final Bundle bundle = getIntent().getExtras();
        assert bundle != null;
        id = bundle.getInt("id");
        final User user = mUserViewModel.findById(id);

        tv1.setText(user.name);
        tv2.setText(user.phone);
        tv3.setText(user.email);
        tv4.setText(user.address);
        editBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ActivityTwo.this, ActivityThree.class);
                Bundle bundle = new Bundle();
                bundle.putInt("id", id);
                intent.putExtras(bundle);
                startActivity(intent);

            }
        });
        deleteBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mUserViewModel.delete(user);
                finish();

            }
        });

    }

    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.home_menu, menu);
        return super.onCreateOptionsMenu(menu);
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == android.R.id.home) {
            finish();
        }
        return super.onOptionsItemSelected(item);

    }

}