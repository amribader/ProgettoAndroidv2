package com.example.simplenav.CommucationController;

import com.example.simplenav.DB.PictureDB.Sid;

public class Like {

    private String sid;
    private int tid;
    private Boolean liked;

    public Like(String sid, int tid, Boolean liked) {
        this.sid = sid;
        this.tid = tid;
        this.liked = liked;
    }

    public String getSid() {
        return sid;
    }

    public void setSid(String sid) {
        this.sid = sid;
    }

    public int getTid() {
        return tid;
    }

    public void setTid(int tid) {
        this.tid = tid;
    }

    public Boolean getLiked() {
        return liked;
    }

    public void setLiked(Boolean liked) {
        this.liked = liked;
    }
}
