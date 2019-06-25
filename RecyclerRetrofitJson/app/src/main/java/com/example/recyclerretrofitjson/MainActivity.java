package com.example.recyclerretrofitjson;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.lifecycle.Observer;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    RecyclerView myRecyclerView;
    LinearLayoutManager mLayoutManager;
    ItemList itemListResponse;
    MyAdapter myAdapter;
    RecyclerView.Adapter adapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Toolbar toolbar = (Toolbar) findViewById(R.id.my_toolbar);
        setSupportActionBar(toolbar);

        myRecyclerView = (RecyclerView) findViewById(R.id.recyclerView);
        myRecyclerView.setHasFixedSize(true);

        mLayoutManager = new LinearLayoutManager(this);
        myRecyclerView.setLayoutManager(mLayoutManager);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.fetchId:
                fetchData();
                return true;
            case R.id.clearId:
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    public void fetchData() {
        HttpLoggingInterceptor httpLoggingInterceptor = new HttpLoggingInterceptor();
        httpLoggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);

        OkHttpClient okHttpClient = new OkHttpClient.Builder().addInterceptor(httpLoggingInterceptor).build();
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(Api.URL)
                .client(okHttpClient)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        Api api = retrofit.create(Api.class);

        final ArrayList<ItemList> itemArrayList = new ArrayList<>();

        Call<ItemList> call = api.getItemObject();
        call.enqueue(new Callback<ItemList>(){

            @Override
            public void onResponse(Call<ItemList> call, Response<ItemList> response) {

                Log.d("MM", "onResponse() called with: call = [" + call + "], response = [" + response + "]");
                if(response.isSuccessful()) {
                    itemListResponse=response.body();
                    setDataInRecyclerView();
                }
            }

            @Override
            public void onFailure(Call<ItemList> call, Throwable t) {

            }
        });
    }
    private void setDataInRecyclerView() {
        mLayoutManager = new LinearLayoutManager(this);
        myRecyclerView.setLayoutManager(mLayoutManager);
        // call the constructor of UsersAdapter to send the reference and data to Adapter
        MyAdapter usersAdapter = new MyAdapter(itemListResponse, MainActivity.this);
        myRecyclerView.setAdapter(usersAdapter); // set the Adapter to RecyclerView
    }

}


