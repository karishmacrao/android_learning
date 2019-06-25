package com.example.retrofitexample;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class ItemList {

    @SerializedName("total_count")
    int total_count;

    @SerializedName("incomplete_results")
    boolean incompleteResults;

    @SerializedName("items")
    ArrayList<Item> items;

    public ItemList() {
    }

    public ItemList getItemList() {
        return this;
    }

    public ArrayList<Item> getItems() {
        return items;
    }

    public int getTotal_count() {
        return total_count;
    }

    public boolean getIncompleteResults() {
        return incompleteResults;
    }

    public Item item;

    public void setItem(Item item) {
        this.item = item;
    }

    public Item getItem() {
        return item;
    }

}
