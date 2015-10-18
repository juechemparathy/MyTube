package com.sjsu.mytube.models;

import com.google.api.client.util.DateTime;

import java.math.BigInteger;

public class VideoInfo {
    private String id;
    private String title;
    private DateTime publisherDate;
    private String thumbnailUrl;
    private BigInteger viewCount;

    public String getThumbnailUrl(){
        return thumbnailUrl;
    }

    public BigInteger getViewCount(){
        return viewCount;
    }

    public DateTime getPublishDate(){
        return publisherDate;
    }

    public String getTitle(){ return title; }

    public VideoInfo( String _id, String _title, DateTime _publisherDate, String _thumbnailUrl, BigInteger _viewCount )
    {
        id = _id;
        title = _title;
        publisherDate = _publisherDate;
        thumbnailUrl = _thumbnailUrl;
        viewCount = _viewCount;
    }

    // any custom getters  on video should be added below.
    // Ref - https://github.com/youtube/yt-direct-lite-android/blob/master/app/src/main/java/com/google/ytdl/util/VideoData.java
}
