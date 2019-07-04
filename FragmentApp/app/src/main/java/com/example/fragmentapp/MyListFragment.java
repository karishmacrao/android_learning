package com.example.fragmentapp;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.util.Objects;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.ListFragment;

public class MyListFragment extends ListFragment {
    public MyListFragment() {
    }

    String[] data = new String[]{
            "Item_1",
            "Item_2",
            "Item_3",
            "Item_4",
            "Item_5",
            "Item_6",
            "Item_7",
            "Item_8",
            "Item_9",
            "Item_10",
            "Item_11",
            "Item_12"
    };

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(
                Objects.requireNonNull(getActivity()),
                android.R.layout.simple_list_item_1,
                data
        );
        this.setListAdapter(adapter);


    }

    @Override
    public void onListItemClick(ListView l, View v, int position, long id) {
        super.onListItemClick(l, v, position, id);
        Toast.makeText(getActivity().getApplicationContext(), "You have clicked on "+data[position], Toast.LENGTH_SHORT).show();
    }
}