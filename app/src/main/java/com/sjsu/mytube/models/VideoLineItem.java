package com.sjsu.mytube.models;

public class VideoLineItem {
    private String title;
    private String imageUrl;
    private String owner;
    private String pubdate;
    private int star;
    private String videoId;
    private int viewCount;

    public int getViewCount() {
        return viewCount;
    }

    public void setViewCount(int _viewCount) {
        viewCount = _viewCount;
    }

    public String getPubdate() {
        return pubdate;
    }

    public void setPubdate(String pubdate) {
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

    public String getVideoId() {
        return videoId;
    }

    ;

    public void setVideoId(String _videoId) {
        videoId = _videoId;
    }
}
