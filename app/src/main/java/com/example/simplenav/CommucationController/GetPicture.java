package com.example.simplenav.CommucationController;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

import com.google.gson.annotations.SerializedName;

import org.jetbrains.annotations.NotNull;


public class GetPicture {
    @SerializedName("name")
    private @NotNull String name;
    @SerializedName("picture")
    private @NotNull String picture;
    @SerializedName("pversion")
    private int pversion;

    @SerializedName("uid")
    private int uid;


    public GetPicture(@NotNull String name, @NotNull String picture, int pversion, int uid) {
        this.name = name;
        this.picture = picture;
        this.pversion = pversion;
        this.uid = uid;
    }

    public void setName(@NotNull String name) {
        this.name = name;
    }

    public void setPicture(@NotNull String picture) {
        this.picture = picture;
    }

    public void setPversion(int pversion) {
        this.pversion = pversion;
    }

    public void setUid(int uid) {
        this.uid = uid;
    }

    @NotNull
    public String getName() {
        return name;
    }

    @NotNull
    public String getPicture() {
        return picture;
    }

    public int getPversion() {
        return pversion;
    }

    public int getUid() {
        return uid;
    }

    @Override
    public String toString() {
        return "GetPicture{" +
                "name='" + name + '\'' +
                ", picture='" + picture + '\'' +
                ", pversion=" + pversion +
                ", uid=" + uid +
                '}';
    }
}
