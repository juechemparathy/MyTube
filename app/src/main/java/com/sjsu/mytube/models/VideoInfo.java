package com.sjsu.mytube.models;

import com.google.api.client.util.DateTime;
import com.google.api.services.youtube.model.Video;

public class VideoInfo {
    private String id;
    private String title;
    private DateTime publisherDate;
    private String thumbnailUrl;
    private int numberOfViews;

    public String getThumbnailUrl(){
        return thumbnailUrl;
    }

    public int getNumberOfViews(){
        return numberOfViews;
    }

    public DateTime getPublishDate(){
        return publisherDate;
    }

    public String getTitle(){ return title; }

    public VideoInfo( String _id, String _title, DateTime _publisherDate, String _thumbnailUrl, int _numberOfViews )
    {
        id = _id;
        title = _title;
        publisherDate = _publisherDate;
        thumbnailUrl = _thumbnailUrl;
        numberOfViews = _numberOfViews;
    }

    // any custom getters  on video should be added below.
    // Ref - https://github.com/youtube/yt-direct-lite-android/blob/master/app/src/main/java/com/google/ytdl/util/VideoData.java
}
