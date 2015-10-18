package com.sjsu.mytube.helpers;

import android.util.Log;

import com.google.android.gms.auth.api.Auth;
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
import com.sjsu.mytube.models.VideoInfo;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class YoutubeHelper {

    private static YoutubeHelper shared = null;

    private static final HttpTransport HTTP_TRANSPORT = new NetHttpTransport();
    private static final GsonFactory JSON_FACTORY = new GsonFactory();
    private static final long MAX_VIDEOS_RETURNED = 20;

    private YouTube youtube;

    private YoutubeHelper()
    {
        youtube = new YouTube.Builder(HTTP_TRANSPORT, JSON_FACTORY, new HttpRequestInitializer() {
            public void initialize(HttpRequest request) throws IOException {
            }
        }).setApplicationName( "MyTube" ).build();
    }

    public static YoutubeHelper shared()
    {
        if ( shared == null )
        {
            synchronized ( YoutubeHelper.class )
            {
                if ( shared == null )
                {
                    try
                    {
                        shared = new YoutubeHelper();
                    }
                    catch ( Exception exception )
                    {
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

        try
        {
            YouTube.Search.List searchHelper = youtube.search().list( "id,snippet" );
            searchHelper.setKey( "AIzaSyCZ5AGF_E1RfPhsX-3tX6HZKj-Zgt7JKuY" );
            //searchHelper.setOauthToken(  );
            searchHelper.setQ( query );
            searchHelper.setType("video");
            searchHelper.setFields("items(id/kind,id/videoId,snippet/title,snippet/publishedAt,snippet/thumbnails/default/url)");
            searchHelper.setMaxResults( MAX_VIDEOS_RETURNED );

            SearchListResponse searchResponse = searchHelper.execute();

            List<SearchResult> searchResultList = searchResponse.getItems();

            if ( searchResultList != null )
            {
                for ( int i = 0; i < searchResultList.size(); i++ )
                {
                    SearchResult searchResult = searchResultList.get( i );
                    SearchResultSnippet searchResultSnippet = searchResult.getSnippet();

                    VideoInfo videoInfo = new VideoInfo( searchResult.getId().toString(),
                            searchResultSnippet.getTitle().toString(),
                            searchResultSnippet.getPublishedAt(),
                            searchResultSnippet.getThumbnails().getDefault().getUrl(),
                            0           // TODO - get number of views
                    );

                    result.add( videoInfo );

                }
            }
        }
        catch ( GoogleJsonResponseException exception )
        {
            Log.e( "Youtubehelper", "Google Json Response Exception.", exception );
        }
        catch ( IOException exception )
        {
            Log.e( "Youtubehelper", "IO Exception.", exception );
        }
        catch ( Exception exception )
        {
            Log.e( "Youtubehelper", "Unkown Exception.", exception );
        }

        return result;
    }
}
