package com.example.networkoptestapp;

public class Owner {
    String ownerId, ownerLogin, ownerAvatarUrl, ownerType;

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
