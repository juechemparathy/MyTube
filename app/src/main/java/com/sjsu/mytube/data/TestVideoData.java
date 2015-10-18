package com.sjsu.mytube.data;

import com.google.api.client.util.DateTime;
import com.sjsu.mytube.models.VideoInfo;

import java.math.BigInteger;
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
        videoInfoList.add( new VideoInfo( "id0", "title0", new DateTime( 500 ), "https://i.ytimg.com/vi/fbkzNPe5s64/default.jpg", BigInteger.ONE ) );
        videoInfoList.add( new VideoInfo( "id1", "title1", new DateTime( 500 ), "https://i.ytimg.com/vi/fbkzNPe5s64/default.jpg", BigInteger.ONE ) );
        videoInfoList.add( new VideoInfo( "id2", "title2", new DateTime( 500 ), "https://i.ytimg.com/vi/fbkzNPe5s64/default.jpg", BigInteger.ONE ) );
        videoInfoList.add( new VideoInfo( "id3", "title3", new DateTime( 500 ), "https://i.ytimg.com/vi/fbkzNPe5s64/default.jpg", BigInteger.ONE ) );
        videoInfoList.add( new VideoInfo( "id4", "title4", new DateTime( 500 ), "https://i.ytimg.com/vi/fbkzNPe5s64/default.jpg", BigInteger.ONE ) );
        videoInfoList.add( new VideoInfo( "id5", "title5", new DateTime( 500 ), "https://i.ytimg.com/vi/fbkzNPe5s64/default.jpg", BigInteger.ONE ) );
        videoInfoList.add( new VideoInfo( "id6", "title6", new DateTime( 500 ), "https://i.ytimg.com/vi/fbkzNPe5s64/default.jpg", BigInteger.ONE ) );
        videoInfoList.add( new VideoInfo( "id7", "title7", new DateTime( 500 ), "https://i.ytimg.com/vi/fbkzNPe5s64/default.jpg", BigInteger.ONE ) );
        videoInfoList.add( new VideoInfo( "id8", "title8", new DateTime( 500 ), "https://i.ytimg.com/vi/fbkzNPe5s64/default.jpg", BigInteger.ONE ) );
        videoInfoList.add( new VideoInfo( "id9", "title9", new DateTime( 500 ), "https://i.ytimg.com/vi/fbkzNPe5s64/default.jpg", BigInteger.ONE ) );
        return videoInfoList;
    }
}
