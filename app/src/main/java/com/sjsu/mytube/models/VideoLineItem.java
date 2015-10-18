package com.sjsu.mytube.models;

import com.google.api.client.util.DateTime;

public class VideoLineItem {
    private String title;
    private String imageUrl;
    private String owner;
    private DateTime pubdate;
    private int star;

    public DateTime getPubdate() {
        return pubdate;
    }

    public void setPubdate(DateTime pubdate) {
        this.pubdate = pubdate;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public String getOwner() {
        return owner;
    }

    public void setOwner(String owner) {
        this.owner = owner;
    }

    public int getStar() {
        return star;
    }

    public void setStar(int star) {
        this.star = star;
    }
}
