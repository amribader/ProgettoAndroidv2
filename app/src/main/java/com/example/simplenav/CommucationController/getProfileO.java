package com.example.simplenav.CommucationController;

import com.google.gson.annotations.SerializedName;

import org.jetbrains.annotations.NotNull;

public class getProfileO {
    //@SerializedName("uid")
    private int uid;
    //@SerializedName("sid")
    private @NotNull String sid;
    //@SerializedName("name")
    private @NotNull String name;
    //@SerializedName("picture")
    private @NotNull String picture;
    //@SerializedName("pversion")
    private int pversion;



    public getProfileO(int uid,@NotNull String sid,@NotNull String name, @NotNull String picture, int pversion) {
        this.name = name;
        this.picture = picture;
        this.pversion = pversion;
        this.uid = uid;
        this.sid = sid;
    }

    public getProfileO() {
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

    public int getPversion() {
        return pversion;
    }

    public void setPversion(int pversion) {
        this.pversion = pversion;
    }

    public int getUid() {
        return uid;
    }

    public void setUid(int uid) {
        this.uid = uid;
    }

    @NotNull
    public String getSid() {
        return sid;
    }

    public void setSid(@NotNull String sid) {
        this.sid = sid;
    }

    @Override
    public String toString() {
        return "getProfileO{" +
                "uid=" + uid +
                ", name='" + name + '\'' +
                ", picture='" + picture + '\'' +
                ", pversion=" + pversion +
                ", sid='" + sid + '\'' +
                '}';
    }
}
