package com.example.simplenav.CommucationController;

import com.google.gson.annotations.SerializedName;

import org.jetbrains.annotations.NotNull;

public class setProfileI {
    @SerializedName("name")
    private @NotNull String name;
    @SerializedName("picture")
    private @NotNull String picture;
    @SerializedName("sid")
    private @NotNull String sid;
    @SerializedName("pversion")
    private int pversion;
    @SerializedName("uid")
    private int uid;

    public setProfileI(@NotNull String name, @NotNull String picture, @NotNull String sid, int pversion) {
        this.name = name;
        this.picture = picture;
        this.sid = sid;
        this.pversion = pversion;
    }

    public setProfileI() {
    }

    public int getUid() {
        return uid;
    }

    public void setUid(int uid) {
        this.uid = uid;
    }

    public int getPversion() {
        return pversion;
    }

    public void setPversion(int pversion) {
        this.pversion = pversion;
    }

    @NotNull
    public String getName() {
        return name;
    }

    public void setName(@NotNull String name) {
        this.name = name;
    }

    @NotNull
    public String getPicture() {
        return picture;
    }

    public void setPicture(@NotNull String picture) {
        this.picture = picture;
    }

    @NotNull
    public String getSid() {
        return sid;
    }

    public void setSid(@NotNull String sid) {
        this.sid = sid;
    }
}
