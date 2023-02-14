package com.example.simplenav;

import androidx.annotation.NonNull;

public class CreateTwok {

    private @NonNull String sid;
    private @NonNull String text;
    private @NonNull String bgcol;
    private @NonNull String fontcol;
    private @NonNull int fontsize;
    private @NonNull int fonttype;
    private @NonNull int halign;
    private @NonNull int valign;
    private @NonNull int lat;
    private @NonNull int lon;

    public CreateTwok(@NonNull String sid, @NonNull String text, @NonNull String bgcol, @NonNull String fontcol, int fontsize, int fonttype, int halign, int valign, int lat, int lon) {
        this.sid = sid;
        this.text = text;
        this.bgcol = bgcol;
        this.fontcol = fontcol;
        this.fontsize = fontsize;
        this.fonttype = fonttype;
        this.halign = halign;
        this.valign = valign;
        this.lat = lat;
        this.lon = lon;
    }

    public CreateTwok(@NonNull String text, @NonNull String bgcol, @NonNull String fontcol, @NonNull int fontsize, @NonNull int fonttype, @NonNull int halign, @NonNull int valign) {
        this.text = text;
        this.bgcol = bgcol;
        this.fontcol = fontcol;
        this.fontsize = fontsize;
        this.fonttype = fonttype;
        this.halign = halign;
        this.valign = valign;
    }

    public CreateTwok() {

    }

    @NonNull
    public String getSid() {
        return sid;
    }

    public void setSid(@NonNull String sid) {
        this.sid = sid;
    }

    @NonNull
    public String getText() {
        return text;
    }

    public void setText(@NonNull String text) {
        this.text = text;
    }

    @NonNull
    public String getBgcol() {
        return bgcol;
    }

    public void setBgcol(@NonNull String bgcol) {
        this.bgcol = bgcol;
    }

    @NonNull
    public String getFontcol() {
        return fontcol;
    }

    public void setFontcol(@NonNull String fontcol) {
        this.fontcol = fontcol;
    }

    public int getFontsize() {
        return fontsize;
    }

    public void setFontsize(int fontsize) {
        this.fontsize = fontsize;
    }

    public int getFonttype() {
        return fonttype;
    }

    public void setFonttype(int fonttype) {
        this.fonttype = fonttype;
    }

    public int getHalign() {
        return halign;
    }

    public void setHalign(int halign) {
        this.halign = halign;
    }

    public int getValign() {
        return valign;
    }

    public void setValign(int valign) {
        this.valign = valign;
    }

    public int getLat() {
        return lat;
    }

    public void setLat(int lat) {
        this.lat = lat;
    }

    public int getLon() {
        return lon;
    }

    public void setLon(int lon) {
        this.lon = lon;
    }

    @Override
    public String toString() {
        return "CreateTwok{" +
                "sid='" + sid + '\'' +
                ", text='" + text + '\'' +
                ", bgcol='" + bgcol + '\'' +
                ", fontcol='" + fontcol + '\'' +
                ", fontsize='" + fontsize + '\'' +
                ", fonttype='" + fonttype + '\'' +
                ", halign='" + halign + '\'' +
                ", valign='" + valign + '\'' +
                ", lat='" + lat + '\'' +
                ", lon='" + lon + '\'' +
                '}';
    }

    public enum Font {
        FONT1,
        FONT2,
        FONT3
    }
    public enum Dimension {
        small,
        medium,
        large
    }
    public enum Horizontal {
        left,
        center,
        right
    }
    public enum Vertical {
        top,
        center,
        bottom
    }



}
