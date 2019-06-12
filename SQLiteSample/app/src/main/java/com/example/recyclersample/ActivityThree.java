package com.example.recyclersample;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.TextView;

public class ActivityThree extends AppCompatActivity {

    private static final int ON_DELETE = 1;
    long id;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_three);
    }
    public void onStart() {
        super.onStart();
        Bundle bundle = getIntent().getExtras();
        id = bundle.getLong("id");

        DatabaseModel dbModel = new FeedEntryDao().getDetailsById(id);

        TextView tv1 = findViewById(R.id.sname);
        TextView tv2 = (TextView) findViewById(R.id.sphone);
        TextView tv3 = (TextView) findViewById(R.id.semail);
        TextView tv4 = (TextView) findViewById(R.id.saddress);

        if (dbModel == null) {
            tv1.setText("");
            tv2.setText("");
            tv3.setText("");
            tv4.setText("");
            return;
        }


        tv1.setText(dbModel.getName());
        tv2.setText(dbModel.getEmail());
        tv3.setText(dbModel.getNumber());
        tv4.setText(dbModel.getAddress());
    }
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
         inflater.inflate(R.menu.edit_menu, menu);
        return true;

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        Intent intent = new Intent(ActivityThree.this, ActivityFive.class);
        Bundle bundle = new Bundle();

        bundle.putLong("id", id);

        intent.putExtras(bundle);
        startActivityForResult(intent, ON_DELETE);
        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == ON_DELETE && resultCode == 1000) {
            finish();
        }
    }
    @Override
    public void onResume() {
        super.onResume();
       onStart();
    }
}
