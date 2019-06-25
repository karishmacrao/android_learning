package com.example.retrofitexample;

import com.google.gson.annotations.SerializedName;

public class Owner {
    @SerializedName("id")
    public String ownerId;
    @SerializedName("login")
    public String ownerLogin;
    @SerializedName("avatar_url")
    public String ownerAvatarUrl;
    @SerializedName("type")
    public String ownerType;

    public Owner(String ownerId, String ownerLogin, String ownerAvatarUrl, String ownerType) {
        this.ownerId = ownerId;
        this.ownerLogin = ownerLogin;
        this.ownerAvatarUrl = ownerAvatarUrl;
        this.ownerType = ownerType;
    }

    public String getOwnerId() {
        return ownerId;
    }

    public String getOwnerLogin() {
        return ownerLogin;
    }

    public String getOwnerAvatarUrl() {
        return ownerAvatarUrl;
    }

    public String getOwnerType() {
        return ownerType;
    }

}
