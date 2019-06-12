package com.example.recyclersample;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.util.List;

public class ActivityFive extends AppCompatActivity {
    long id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_five);


        Bundle bundle = getIntent().getExtras();
        id = bundle.getLong("id");


        DatabaseModel dbModel = new FeedEntryDao().getDetailsById(id);

        EditText name = (EditText) findViewById(R.id.fname);

        EditText phone = (EditText) findViewById(R.id.fphone);

        EditText email = (EditText) findViewById(R.id.femail);

        EditText address = (EditText) findViewById(R.id.faddress);


        TextView tv1 = findViewById(R.id.fname);
        TextView tv2 = (TextView) findViewById(R.id.fphone);
        TextView tv3 = (TextView) findViewById(R.id.femail);
        TextView tv4 = (TextView) findViewById(R.id.faddress);

        tv1.setText(dbModel.getName());
        tv2.setText(dbModel.getEmail());
        tv3.setText(dbModel.getNumber());
        tv4.setText(dbModel.getAddress());


        Button buttonSave = findViewById(R.id.fsave);
        buttonSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EditText name = (EditText) findViewById(R.id.fname);
                String Ename = name.getText().toString();

                EditText phone = (EditText) findViewById(R.id.fphone);
                String Pnumber = phone.getText().toString();

                EditText email = (EditText) findViewById(R.id.femail);
                String Email = email.getText().toString();

                EditText address = (EditText) findViewById(R.id.faddress);
                String Address = address.getText().toString();


                FeedEntryDao feedDao = new FeedEntryDao();
                feedDao.update(id, Ename, Pnumber, Email, Address);

                finish();
            }
        });

        Button buttonDelete = findViewById(R.id.fdelete);
        buttonDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EditText name = (EditText) findViewById(R.id.fname);
                String Ename = name.getText().toString();

                EditText phone = (EditText) findViewById(R.id.fphone);
                String Pnumber = phone.getText().toString();

                EditText email = (EditText) findViewById(R.id.femail);
                String Email = email.getText().toString();

                EditText address = (EditText) findViewById(R.id.faddress);
                String Address = address.getText().toString();


                FeedEntryDao feedDao = new FeedEntryDao();
                feedDao.delete(id);

                setResult(1000);
                finish();
            }
        });
    }
}
