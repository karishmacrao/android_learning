package com.example.retrofitexample;

import com.google.gson.annotations.SerializedName;

class Item {
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


    public Owner owner;

    public void setOwner(Owner owner) {
        this.owner = owner;
    }

    public Owner getOwner() {
        return owner;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getFullName() {
        return fullName;
    }

    public void setPrivateStatus(Boolean privateStatus) {
        this.privateStatus = privateStatus;
    }

    public boolean getPrivateStatus() {
        return privateStatus;
    }

    public void setNodeId(String nodeId) {
        this.nodeId = nodeId;
    }

    public String getNodeId() {
        return nodeId;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getId() {
        return id;
    }
}

