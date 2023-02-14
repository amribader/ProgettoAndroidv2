package com.example.simplenav.CommucationController;

public class follow {
    private Boolean followed;

    public follow(Boolean followed) {
        this.followed = followed;
    }

    public follow() {
    }

    public Boolean getFollowed() {
        return followed;
    }

    public void setFollowed(Boolean followed) {
        this.followed = followed;
    }

    @Override
    public String toString() {
        return "follow{" +
                "followed=" + followed +
                '}';
    }
}
