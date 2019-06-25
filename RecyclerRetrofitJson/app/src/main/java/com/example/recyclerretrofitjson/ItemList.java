package com.example.recyclerretrofitjson;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class ItemList {

    @SerializedName("total_count")
    int total_count;

    @SerializedName("incomplete_results")
    boolean incompleteResults;

    @SerializedName("items")
    ArrayList<ItemList> items;

    @SerializedName("id")
    public String id;
    @SerializedName("name")
    public String name;
    @SerializedName("full_name")
    public String fullName;
    @SerializedName("node_id")
    public String nodeId;
    @SerializedName("private")
    public boolean privateStatus;

    public ItemList() {
    }

    public Owner owner;

    public void setOwner(Owner owner) {
        this.owner = owner;
    }

    public Owner getOwner() {
        return owner;
    }

    public ArrayList<ItemList> getItemList() {
        return items;
    }

    public int getTotal_count() {
        return total_count;
    }

    public boolean getIncompleteResults() {
        return incompleteResults;
    }

    public String getFullName() {
        return fullName;
    }

    public boolean getPrivateStatus() {
        return privateStatus;
    }

    public String getNodeId() {
        return nodeId;
    }

    public String getName() {
        return name;
    }

    public String getId() {
        return id;
    }


}
