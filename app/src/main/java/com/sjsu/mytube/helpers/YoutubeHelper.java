package com.sjsu.mytube.helpers;

import android.util.Log;

import com.google.api.client.googleapis.json.GoogleJsonResponseException;
import com.google.api.client.http.HttpRequest;
import com.google.api.client.http.HttpRequestInitializer;
import com.google.api.client.http.HttpTransport;
import com.google.api.client.http.javanet.NetHttpTransport;
import com.google.api.client.json.gson.GsonFactory;
import com.google.api.services.youtube.YouTube;
import com.google.api.services.youtube.model.PlaylistItem;
import com.google.api.services.youtube.model.PlaylistItemListResponse;
import com.google.api.services.youtube.model.PlaylistItemSnippet;
import com.google.api.services.youtube.model.ResourceId;
import com.google.api.services.youtube.model.SearchListResponse;
import com.google.api.services.youtube.model.SearchResult;
import com.google.api.services.youtube.model.SearchResultSnippet;
import com.google.api.services.youtube.model.Video;
import com.google.api.services.youtube.model.VideoListResponse;
import com.sjsu.mytube.models.VideoInfo;

import java.io.IOException;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

public class YoutubeHelper {

    private static YoutubeHelper shared = null;

    private static final HttpTransport HTTP_TRANSPORT = new NetHttpTransport();
    private static final GsonFactory JSON_FACTORY = new GsonFactory();
    private static final String API_KEY = "AIzaSyCZ5AGF_E1RfPhsX-3tX6HZKj-Zgt7JKuY"; //"AIzaSyCtLOXBs3kGN8JPJLRIYvYr4Ri46yAL3xU";//"AIzaSyAUURrcezOMraClM2YzAvODYyU51HFbAi8" ;
    private static final long MAX_VIDEOS_RETURNED = 25;
    private static final long MAX_PLAYLISTS_RETURNED = 1;
    private static final String FAVORITE_PLAYLIST_NAME = "SJSU-CMP-277";

    private static String favoritePlaylistId;

    private YouTube youtube;

    private YoutubeHelper() {

        YouTube.Builder builder = new YouTube.Builder(
                HTTP_TRANSPORT,
                JSON_FACTORY,
                new HttpRequestInitializer() {
                    public void initialize(HttpRequest request) throws IOException {
                    }
                }
        );
        builder.setApplicationName("MyTube");
        youtube = builder.build();

        favoritePlaylistId = null;
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

    public List<VideoInfo> Search( final String query ) {
        List<VideoInfo> result = new ArrayList<VideoInfo>();

        if ( youtube == null ) {
            return result;
        }

        try {
            YouTube.Search.List searchCommand = youtube.search().list( "id,snippet" );
            searchCommand.setKey(API_KEY);
            searchCommand.setQ(query);
            searchCommand.setType("video");
            searchCommand.setFields("items(id/kind,id/videoId,snippet/title,snippet/publishedAt,snippet/thumbnails/default/url)");
            searchCommand.setMaxResults(MAX_VIDEOS_RETURNED);

            SearchListResponse searchResponse = searchCommand.execute();

            List<SearchResult> searchResultList = searchResponse.getItems();

            if ( searchResultList != null ) {
                for ( int i = 0; i < searchResultList.size(); i++ ) {
                    SearchResult searchResult = searchResultList.get(i);
                    SearchResultSnippet searchResultSnippet = searchResult.getSnippet();

                    String videoId = searchResult.getId().getVideoId();

                    YouTube.Videos.List videoInfoCommand = youtube.videos().list("snippet, statistics").setId(videoId);
                    videoInfoCommand.setKey(API_KEY);
                    VideoListResponse videoInfoHelperResponse = videoInfoCommand.execute();

                    List<Video> videoInfoResultList = videoInfoHelperResponse.getItems();

                    BigInteger viewCount = BigInteger.ZERO;
                    if ( videoInfoResultList != null ) {
                        Video video = videoInfoResultList.get(0);
                        viewCount = video.getStatistics().getViewCount();
                    }

                    VideoInfo videoInfo = new VideoInfo(
                            videoId,
                            searchResultSnippet.getTitle().toString(),
                            searchResultSnippet.getPublishedAt(),
                            searchResultSnippet.getThumbnails().getDefault().getUrl(),
                            viewCount
                    );

                    result.add( videoInfo );

                }
            }
        } catch ( GoogleJsonResponseException exception ) {
            Log.e( "Youtubehelper", "Search", exception );
        } catch ( IOException exception ) {
            Log.e( "Youtubehelper", "Search", exception );
        } catch ( Exception exception ) {
            Log.e( "Youtubehelper", "Search", exception );
        }

        return result;
    }

    public List<VideoInfo> GetPlaylistVideos( String playlistId ) {
        List<VideoInfo> result = new ArrayList<VideoInfo>();

        if ( youtube == null || playlistId == null ) {
            return result;
        }

        try {
            YouTube.PlaylistItems.List playlistCommand = youtube.playlistItems().list( "id,snippet" );
            playlistCommand.setKey(API_KEY);
            playlistCommand.setFields("items(id,snippet/resourceId/videoId,snippet/title,snippet/publishedAt,snippet/thumbnails/default/url)");
            playlistCommand.setMaxResults(MAX_VIDEOS_RETURNED);
            playlistCommand.setPlaylistId(playlistId);

            PlaylistItemListResponse searchResponse = playlistCommand.execute();

            List<PlaylistItem> searchResultList = searchResponse.getItems();

            if ( searchResultList != null ) {
                for ( int i = 0; i < searchResultList.size(); i++ ) {
                    PlaylistItem searchResult = searchResultList.get(i);
                    PlaylistItemSnippet searchResultSnippet = searchResult.getSnippet();

                    String videoId = searchResult.getSnippet().getResourceId().getVideoId();

                    YouTube.Videos.List videoInfoCommand = youtube.videos().list("snippet, statistics").setId(videoId);
                    videoInfoCommand.setKey(API_KEY);
                    VideoListResponse videoInfoHelperResponse = videoInfoCommand.execute();

                    List<Video> videoInfoResultList = videoInfoHelperResponse.getItems();

                    BigInteger viewCount = BigInteger.ZERO;
                    if ( videoInfoResultList != null ) {
                        Video video = videoInfoResultList.get(0);
                        viewCount = video.getStatistics().getViewCount();
                    }

                    VideoInfo videoInfo = new VideoInfo(
                            videoId,
                            searchResultSnippet.getTitle().toString(),
                            searchResultSnippet.getPublishedAt(),
                            searchResultSnippet.getThumbnails().getDefault().getUrl(),
                            viewCount
                    );

                    result.add( videoInfo );

                }
            }
        } catch ( GoogleJsonResponseException exception ) {
            Log.e( "Youtubehelper", "Search", exception );
        } catch ( IOException exception ) {
            Log.e( "Youtubehelper", "Search", exception );
        } catch ( Exception exception ) {
            Log.e( "Youtubehelper", "Search", exception );
        }

        return result;
    }

    public boolean PlaylistInsert( final String videoId, final String playlistId ) {

        if ( youtube == null || videoId == null || playlistId == null ) {
            return false;
        }

        ResourceId resourceId = new ResourceId();
        resourceId.setKind("youtube#video");
        resourceId.setVideoId(videoId);

        PlaylistItemSnippet playlistItemSnippet = new PlaylistItemSnippet();
        playlistItemSnippet.setPlaylistId(playlistId);
        playlistItemSnippet.setResourceId(resourceId);

        PlaylistItem playlistItem = new PlaylistItem();
        playlistItem.setSnippet(playlistItemSnippet);

        try {
            YouTube.PlaylistItems.Insert playlistItemsInsertCommand = youtube.playlistItems().insert("snippet,contentDetails", playlistItem);
            playlistItemsInsertCommand.setKey(API_KEY);
            PlaylistItem returnedPlaylistItem = playlistItemsInsertCommand.execute();

            if ( returnedPlaylistItem != null ) {
                return true;
            }
        } catch ( IOException exception ) {
            Log.e( "Youtubehelper", "PlaylistInsert", exception );
        }
        return false;
    }

    private String GetPlaylistId( String playlistName ) {
        String playlistId = null;

        if ( youtube == null || playlistName == null ) {
            return playlistId;
        }

        try {
            YouTube.Search.List searchCommand = youtube.search().list( "id,snippet" );
            searchCommand.setKey(API_KEY);
            searchCommand.setQ(playlistName);
            searchCommand.setType("playlist");
            searchCommand.setFields("items(id/playlistId)");
            searchCommand.setMaxResults(MAX_PLAYLISTS_RETURNED);

            SearchListResponse searchResponse = searchCommand.execute();

            List<SearchResult> searchResultList = searchResponse.getItems();

            if ( searchResultList != null ) {
                for ( int i = 0; i < searchResultList.size(); i++ ) {
                    SearchResult searchResult = searchResultList.get(i);
                    playlistId = searchResult.getId().getPlaylistId();
                }
            }
        } catch ( GoogleJsonResponseException exception ) {
            Log.e( "Youtubehelper", "GetPlaylistId", exception );
        } catch ( IOException exception ) {
            Log.e( "Youtubehelper", "GetPlaylistId", exception );
        } catch ( Exception exception ) {
            Log.e( "Youtubehelper", "GetPlaylistId", exception );
        }

        return playlistId;
    }

    private String GetPlaylistIdFavorite() {
        return GetPlaylistId( FAVORITE_PLAYLIST_NAME );
    }

    public  boolean PlaylistInsertFavorite(final String videoId) {
        if ( favoritePlaylistId == null ) {
            favoritePlaylistId = GetPlaylistIdFavorite();
        }
        return PlaylistInsert( videoId, favoritePlaylistId);
    }

    public List<VideoInfo> GetPlaylistVideosFavorite() {
        if ( favoritePlaylistId == null ) {
            favoritePlaylistId = GetPlaylistIdFavorite();
        }
        return GetPlaylistVideos(favoritePlaylistId);
    }
}
