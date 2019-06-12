package com.example.recyclersample;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private RecyclerView recyclerView;

    MyAdapter adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setHasFixedSize(true);

        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        adapter = new MyAdapter(this);
        recyclerView.setAdapter(adapter);

    }

    private void updateListItem() {
        FeedEntryDao feedEntryDao = new FeedEntryDao();
        List<DatabaseModel> dbList = feedEntryDao.getAllDetails();
        adapter.setItems(dbList);
    }

    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.delete_menu, menu);
        return true;

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        FeedEntryDao feedDao = new FeedEntryDao();
        feedDao.deleteAll();

        updateListItem();

        return super.onOptionsItemSelected(item);

    }
    public void onResume() {
        super.onResume();
        updateListItem();
    }
}
