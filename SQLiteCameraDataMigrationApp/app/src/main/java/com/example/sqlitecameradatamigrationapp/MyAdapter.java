package com.example.sqlitecameradatamigrationapp;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.bitmap.CircleCrop;
import com.bumptech.glide.request.RequestOptions;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.ViewHolder> {
    static List<UserModel> listItem = new ArrayList<>();
    static Context context;

    public MyAdapter(Context context) {
        this.context = context;
    }

    public void setItems(List<UserModel> items) {
        listItem.clear();

        if (items != null && items.size() > 0) {
            listItem.addAll(items);
        }
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_row, null);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, final int position) {


        if (!TextUtils.isEmpty(listItem.get(position).getImage())) {
            Log.d("Adapter1",""+listItem.get(position).getImage());
            RequestOptions cropOptions = new RequestOptions().transform(new CircleCrop());

            Glide.with(holder.itemView.getContext()).load(listItem.get(position).getImage())
                    .fallback(R.drawable.ic_camera_alt_black_24dp).apply(cropOptions).into(holder.photo);
        }
        Log.d("Adapter2",""+listItem.get(position).getImage());
        holder.title.setText(listItem.get(position).getName());
        holder.subtitle.setText(listItem.get(position).getEmail());

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, ModifySingleUser.class);
                Bundle bundle = new Bundle();

                bundle.putLong("id", listItem.get(position).getId());

                intent.putExtras(bundle);
                context.startActivity(intent);
            }
        });

    }

    @Override
    public int getItemCount() {
        return listItem.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {

        TextView title, subtitle;
        ImageView photo;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            photo = itemView.findViewById(R.id.photoId);
            title = itemView.findViewById(R.id.title);
            subtitle = itemView.findViewById(R.id.subtitle);
        }
    }
}

