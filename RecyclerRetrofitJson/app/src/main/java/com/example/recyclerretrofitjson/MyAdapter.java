package com.example.recyclerretrofitjson;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;

import androidx.annotation.DrawableRes;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.RecyclerView.ViewHolder;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.ViewHolder> {

    final ItemList itemList;
    Context context;

    final ArrayList<ItemList> lists = new ArrayList<>();

    public MyAdapter(ItemList itemList, Context context) {

        this.itemList = itemList;

        if (itemList.items != null && itemList.items.size() > 0) {
            lists.addAll(itemList.items);
        }
        this.context = context;
    }

    @NonNull
    @Override
    public MyAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_layout, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyAdapter.ViewHolder holder, int position) {
        final ItemList item = lists.get(position);

        holder.myItemId.setText(String.format("Item ID: %s", item.getId()));
        holder.myItemName.setText(String.format("Name: %s", item.getName()));
        holder.myItemPrivate.setText(String.format("Private: %s", item.getPrivateStatus()));
        holder.myOwnerId.setText(String.format("Owner ID: %s", item.getOwner().getOwnerId()));
        holder.myOwnerLogin.setText(String.format("Owner Login: %s", item.getOwner().getOwnerLogin()));
        holder.myOwnerAvatarUrl.setText(String.format("Owner Avatar URL: %s", item.getOwner().getOwnerAvatarUrl()));


    }

    @Override
    public int getItemCount() {
        return lists.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView myOwnerAvatarUrl;
        TextView myOwnerLogin;
        TextView myOwnerId;
        TextView myItemPrivate;
        TextView myItemName;
        TextView myItemId;

        public ViewHolder(View itemView) {
            super(itemView);
            myOwnerAvatarUrl = itemView.findViewById(R.id.ownerAvatarUrl);
            myOwnerLogin = itemView.findViewById(R.id.ownerLogin);
            myOwnerId = itemView.findViewById(R.id.ownerId);
            myItemPrivate = itemView.findViewById(R.id.itemPrivate);
            myItemName = itemView.findViewById(R.id.itemName);
            myItemId = itemView.findViewById(R.id.itemId);
        }
    }
}


