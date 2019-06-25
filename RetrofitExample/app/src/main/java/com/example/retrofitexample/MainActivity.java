package com.example.retrofitexample;

import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {
    TextView mDataText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Toolbar toolbar = (Toolbar) findViewById(R.id.my_toolbar);
        setSupportActionBar(toolbar);

        mDataText = (TextView) findViewById(R.id.data_text);
        Log.d("MMM", "Main activity created");
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.fetch_action:
                Log.d("MMM", "Clicked fetch");
                getItems();
                return true;

            case R.id.clear_action:
                mDataText.setText("");
                return true;
        }
        return super.onOptionsItemSelected(item);

    }

    private void getItems() {
        HttpLoggingInterceptor httpLoggingInterceptor = new HttpLoggingInterceptor();
        httpLoggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);

        OkHttpClient okHttpClient = new OkHttpClient.Builder().addInterceptor(httpLoggingInterceptor).build();
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(Api.URL)
                .client(okHttpClient)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        Api api = retrofit.create(Api.class);

        final ArrayList<Item> itemArrayList = new ArrayList<>();

        Call<ItemList> call = api.getItemObject();

        call.enqueue(new Callback<ItemList>() {
            @Override
            public void onResponse(Call<ItemList> call, Response<ItemList> response) {
                if (response.isSuccessful()) {
                    ArrayList<Item> localItemList = response.body().getItems();

                    if (response.body().getItems() != null && response.body().getItems().size() > 0) {
                        itemArrayList.addAll(response.body().getItems());
                    }

                    for (int i = 0; i < localItemList.size(); i++) {
                        Item item = localItemList.get(i);

                        Log.d("MMM", "Item Id: " + item.getId());
                        Log.d("MMM", "Name: " + item.getName());
                        Log.d("MMM", "Full Name: " + item.getFullName());
                        Log.d("MMM", "Node ID: " + item.getNodeId());
                        Log.d("MMM", "Private: " + item.getPrivateStatus());
                        Owner owner = item.getOwner();
                        Log.d("MMM", "Owner Id: " + owner.getOwnerId());

                    }
                    for (int i = 0; i < localItemList.size(); i++) {
                        Item itemObj = localItemList.get(i);
                        Log.d("MMM", "\nItem Name: " + itemObj.getName() + "\nOwner Login: " + itemObj.getOwner().getOwnerLogin());
                    }

                } else {
                    Toast.makeText(getApplicationContext(), "Error while dispalying", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<ItemList> call, Throwable t) {
                Toast.makeText(getApplicationContext(), t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });


    }


}
