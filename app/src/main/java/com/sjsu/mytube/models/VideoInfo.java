package com.sjsu.mytube.models;

import com.google.api.services.youtube.model.Video;

/**
 * Created by jue on 10/10/15.
 */
public class VideoInfo {
    private Video video;

    public Video getVideo() {
        return video;
    }

    public void setVideo(Video video) {
        this.video = video;
    }

    public String getWatchUrl(){
        return null;
    }

    public String getImageUrl(){
        return null;
    }

    public int getNumberOfViews(){
        return 10;
    }

    public String getPublishDate(){
        return "02-10-2015";
    }

    public String getTitle(){
        return "Video Title";
    }

    public String getOwner(){
        return "sjsuOwner";
    }

    // any custom getters  on video should be added below.
    // Ref - https://github.com/youtube/yt-direct-lite-android/blob/master/app/src/main/java/com/google/ytdl/util/VideoData.java
}
