package com.example.roomsample;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Base64;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.bitmap.CircleCrop;
import com.bumptech.glide.request.RequestOptions;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import androidx.recyclerview.widget.RecyclerView;

public class UserListAdapter extends RecyclerView.Adapter<UserListAdapter.UserViewHolder> {
    private Context context;

    class UserViewHolder extends RecyclerView.ViewHolder {
        private final TextView userView1;
        private final TextView userView2;
        private final ImageView imageView;


        private UserViewHolder(View itemView) {
            super(itemView);
            imageView = itemView.findViewById(R.id.photoId);
            userView1 = itemView.findViewById(R.id.title);
            userView2 = itemView.findViewById(R.id.subtitle);
        }
    }

    static private LayoutInflater mInflater;
    static private List<User> mUsers = new ArrayList<>();

    UserListAdapter(Context context) {
        this.context = context;
        mInflater = LayoutInflater.from(context);
    }

    @Override
    public UserViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = mInflater.inflate(R.layout.row_feed, null);
        return new UserViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(UserViewHolder holder, final int position) {
        User current = mUsers.get(position);

        if (!TextUtils.isEmpty(current.getImgUrl())) {
            RequestOptions cropOptions = new RequestOptions().transform(new CircleCrop());

            Glide.with(holder.itemView.getContext()).load(current.getImgUrl())
                    .fallback(R.drawable.ic_add_a_photo_black_24dp).apply(cropOptions).into(holder.imageView);
        }

        Log.d("UrlInDB", current.getUid() + " \npath" + current.getImgUrl());
        holder.userView1.setText(current.getName());
        holder.userView2.setText(current.getEmail());

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, ActivityTwo.class);
                Bundle bundle = new Bundle();

                bundle.putInt("id", mUsers.get(position).getUid());
                intent.putExtras(bundle);
                context.startActivity(intent);
            }
        });
    }

    public void setUsers(List<User> users) {
        mUsers.clear();
        if (users != null && users.size() > 0) {
            mUsers.addAll(users);
        }
        notifyDataSetChanged();
    }

    @Override
    public int getItemCount() {
        return mUsers.size();
    }
}

