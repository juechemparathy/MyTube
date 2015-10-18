package com.sjsu.mytube.data;

import com.google.api.client.util.DateTime;
import com.sjsu.mytube.models.VideoInfo;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by jue on 10/10/15.
 */
public class TestVideoData {

    private static TestVideoData instance;
    public static TestVideoData getNewInstance(){
        if(instance == null){
            instance = new TestVideoData();
        }
        return instance;
    }

    public List<VideoInfo> getVideoList() {
        List<VideoInfo> videoInfoList = new ArrayList<VideoInfo>();
        for (int i = 0; i < 10; i++) {
           videoInfoList.add( new VideoInfo( "id0", "title0", new DateTime( 500 ), "https://i.ytimg.com/vi/fbkzNPe5s64/default.jpg", 1 ) );
        }
        return videoInfoList;
    }
}
