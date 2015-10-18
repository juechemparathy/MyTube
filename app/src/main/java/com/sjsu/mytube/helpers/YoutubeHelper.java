package com.sjsu.mytube.helpers;

import android.util.Log;

import com.google.api.client.googleapis.json.GoogleJsonResponseException;
import com.google.api.client.http.HttpRequest;
import com.google.api.client.http.HttpRequestInitializer;
import com.google.api.client.http.HttpTransport;
import com.google.api.client.http.javanet.NetHttpTransport;
import com.google.api.client.json.gson.GsonFactory;
import com.google.api.services.youtube.YouTube;
import com.google.api.services.youtube.model.SearchListResponse;
import com.google.api.services.youtube.model.SearchResult;
import com.google.api.services.youtube.model.SearchResultSnippet;
import com.google.api.services.youtube.model.Video;
import com.google.api.services.youtube.model.VideoListResponse;
import com.sjsu.mytube.models.VideoInfo;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class YoutubeHelper {

    private static YoutubeHelper shared = null;

    private static final HttpTransport HTTP_TRANSPORT = new NetHttpTransport();
    private static final GsonFactory JSON_FACTORY = new GsonFactory();

    public static final String API_KEY = "AIzaSyCtiFaMBbe0Pmp5AuvsF7znbGFTZ-ljn7k";
    private static final long MAX_VIDEOS_RETURNED = 20;

    private YouTube youtube;
    private YoutubeHelper() {
        youtube = new YouTube.Builder(HTTP_TRANSPORT, JSON_FACTORY, new HttpRequestInitializer() {
            public void initialize(HttpRequest request) throws IOException {
            }
        }).setApplicationName( "MyTube" ).build();
    }

    public static YoutubeHelper shared() {
        if ( shared == null ) {
            synchronized ( YoutubeHelper.class ) {
                if ( shared == null ) {
                    try {
                        shared = new YoutubeHelper();
                    } catch ( Exception exception ) {
                        Log.e( "YoutubeHelper", "Unable to initalize.", exception);
                    }
                }
            }
        }

        return shared;
    }

    public List<VideoInfo> Search( final String query )
    {
        List<VideoInfo> result = new ArrayList<VideoInfo>();

        try {
            YouTube.Search.List searchHelper = youtube.search().list("id,snippet");
            searchHelper.setKey(API_KEY);
            searchHelper.setQ(query);
            searchHelper.setType("video");
            searchHelper.setFields("items(id/kind,id/videoId,snippet/title,snippet/publishedAt,snippet/thumbnails/default/url)");
            searchHelper.setMaxResults(MAX_VIDEOS_RETURNED);

            SearchListResponse searchResponse = searchHelper.execute();

            List<SearchResult> searchResultList = searchResponse.getItems();

            if (searchResultList != null) {
                for (int i = 0; i < searchResultList.size(); i++) {
                    SearchResult searchResult = searchResultList.get(i);
                    SearchResultSnippet searchResultSnippet = searchResult.getSnippet();

                    String videoId = searchResult.getId().getVideoId();

                    YouTube.Videos.List videoInfoHelper = youtube.videos().list("snippet, statistics").setId(videoId);
                    videoInfoHelper.setKey(API_KEY);
                    VideoListResponse videoInfoHelperResponse = videoInfoHelper.execute();

                    List<Video> videoInfoResultList = videoInfoHelperResponse.getItems();

                    int viewCount = 12;
                    if (videoInfoResultList != null) {
                        Video video = videoInfoResultList.get(0);
                        viewCount = video.getStatistics().getViewCount().intValue();
                    }

                    VideoInfo videoInfo = new VideoInfo(
                            videoId,
                            searchResultSnippet.getTitle().toString(),
                            searchResultSnippet.getPublishedAt(),
                            searchResultSnippet.getThumbnails().getDefault().getUrl(),
                            viewCount
                    );

                    result.add(videoInfo);

                }
            }
        } catch ( GoogleJsonResponseException exception ) {
            Log.e( "Youtubehelper", "Google Json Response Exception.", exception );
        } catch ( IOException exception ) {
            Log.e( "Youtubehelper", "IO Exception.", exception );
        } catch ( Exception exception ) {
            Log.e( "Youtubehelper", "Unkown Exception.", exception );
        }
//        }catch(Exception e){
//            e.printStackTrace();
//        }
        return result;
    }
//
//    public static void main(String args[]){
//        List<VideoInfo> videoInfos = YoutubeHelper.shared().Search("car");
//        System.out.println(videoInfos);
//    }
}