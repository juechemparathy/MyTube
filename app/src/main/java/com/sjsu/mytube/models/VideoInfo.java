package com.sjsu.mytube.models;

import com.google.api.client.util.DateTime;
import com.google.api.services.youtube.model.Video;

/**
 * Created by jue on 10/10/15.
 */
public class VideoInfo {

    private String videoId;
    private String title;
    private DateTime publisherDate;
    private String thumbnailUrl;
    private int viewCount;

    public VideoInfo(String _id, String _title, DateTime _publisherDate, String _thumbnailUrl, int _viewCount) {
        videoId = _id;
        title = _title;
        publisherDate = _publisherDate;
        thumbnailUrl = _thumbnailUrl;
        viewCount = _viewCount;
    }


    private Video video;

    public String getVideoId() { return videoId; }

    public Video getVideo() {
        return video;
    }

    public void setVideo(Video video) {
        this.video = video;
    }

    public String getWatchUrl() {
        return null;
    }

    public String getImageUrl() {
        return thumbnailUrl;
    }

    public int getNumberOfViews() {
        return viewCount;
    }

    public String getPublishDate() {
        return publisherDate.toString();
    }

    public String getTitle() {
        return title;
    }

    public String getOwner() {
        return viewCount+"";
    }

    // any custom getters  on video should be added below.
    // Ref - https://github.com/youtube/yt-direct-lite-android/blob/master/app/src/main/java/com/google/ytdl/util/VideoData.java
}
