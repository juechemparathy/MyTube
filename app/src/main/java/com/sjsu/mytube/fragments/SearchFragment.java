package com.sjsu.mytube.fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.SearchView;
import android.widget.TextView;

import com.sjsu.mytube.R;
import com.sjsu.mytube.adapters.LineItemAdapter;
import com.sjsu.mytube.helpers.YoutubeHelper;
import com.sjsu.mytube.models.VideoInfo;
import com.sjsu.mytube.models.VideoLineItem;

import java.util.ArrayList;
import java.util.List;

public class SearchFragment extends Fragment implements SearchView.OnQueryTextListener {
    private static final String DATA = "data";

    // TODO: Rename and change types of parameters
    private int data;
    private TextView tvPagePosition;
    private RecyclerView recyclerView;
    private LineItemAdapter lineItemAdapter;
    List<VideoLineItem> videoLineItems = new ArrayList<VideoLineItem>();

    private String queryText;
    private SearchView searchView;

    public static SearchFragment newInstance(int data) {
        SearchFragment fragment = new SearchFragment();
        Bundle args = new Bundle();
        args.putInt(DATA, data);
        fragment.setArguments(args);
        return fragment;
    }

    public SearchFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            data = getArguments().getInt(DATA);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View layout = inflater.inflate(R.layout.fragment_search, container, false);
        Bundle bundle = getArguments();
        if (bundle != null) {
            // rebuild the data
//          tvPagePosition.setText("Page " +bundle.getInt("position"));
        }

        searchView = ( SearchView ) layout.findViewById(R.id.searchView);
        searchView.setOnQueryTextListener( this );

        recyclerView = (RecyclerView) layout.findViewById(R.id.videolist_rcview);
        lineItemAdapter = new LineItemAdapter(getActivity(), videoLineItems);
        recyclerView.setAdapter(lineItemAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        return layout;
    }


    public boolean onQueryTextSubmit( String _queryText ) {
        synchronized( this ) {
            queryText = _queryText;

            Thread thread = new Thread ( new Runnable() {
                @Override
                public void run()
                {
                    double start =  System.currentTimeMillis();
                    List<VideoInfo> videoInfoList = YoutubeHelper.shared().Search( queryText );
                    System.out.println("Total fetch time: "+ (System.currentTimeMillis()-start)/1000);

                    videoLineItems.clear();

                    if(videoInfoList != null) {
                        for (VideoInfo videoInfo : videoInfoList) {
                            VideoLineItem videoLineItem = new VideoLineItem();
                            videoLineItem.setVideoId( videoInfo.getVideoId() );
                            videoLineItem.setImageUrl(videoInfo.getImageUrl());
                            videoLineItem.setTitle(videoInfo.getTitle());
                            videoLineItem.setPubdate(videoInfo.getPublishDate());
                            videoLineItem.setViewCount(videoInfo.getNumberOfViews());
                            videoLineItems.add(videoLineItem);
                        }
                    }

                    getActivity().runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            lineItemAdapter.notifyDataSetChanged();
                        }
                    });
                }
            } );
            thread.start();
            searchView.clearFocus();
        }

        return true;
    }

    public boolean onQueryTextChange( String quertText )
    {
        return true;
    }
}