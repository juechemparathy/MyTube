package com.sjsu.mytube.fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.sjsu.mytube.R;
import com.sjsu.mytube.adapters.LineItemAdapter;
import com.sjsu.mytube.data.TestVideoData;
import com.sjsu.mytube.models.VideoInfo;
import com.sjsu.mytube.models.VideoLineItem;

import java.util.ArrayList;
import java.util.List;


public class FavoriteFragment extends Fragment {
    private static final String DATA = "data";

    // TODO: Rename and change types of parameters
    private int data;
    private TextView tvPagePosition;
    private RecyclerView recyclerView;
    private LineItemAdapter lineItemAdapter;
    private List<VideoInfo> videoInfoList = TestVideoData.getNewInstance().getVideoList();
    List<VideoLineItem> videoLineItems = new ArrayList<VideoLineItem>();


    public static FavoriteFragment newInstance(int data) {
        FavoriteFragment fragment = new FavoriteFragment();
        Bundle args = new Bundle();
        args.putInt(DATA, data);
        fragment.setArguments(args);
        return fragment;
    }

    public FavoriteFragment() {
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
        View layout = inflater.inflate(R.layout.fragment_favorite, container, false);
        Bundle bundle = getArguments();
        if (bundle != null) {
            // rebuild the data
//          tvPagePosition.setText("Page " +bundle.getInt("position"));
        }

        if(videoInfoList != null) {
            for (VideoInfo videoInfo : videoInfoList) {
                VideoLineItem videoLineItem = new VideoLineItem();
                videoLineItem.setVideoId( videoInfo.getVideoId() );
                videoLineItem.setImageUrl(videoInfo.getThumbnailUrl());
                videoLineItem.setTitle(videoInfo.getTitle());
                // videoLineItem.setOwner(videoInfo.getOwner()); // TODO
                videoLineItem.setPubdate(videoInfo.getPublishDate());
                videoLineItem.setViewCount( videoInfo.getViewCount() );
                videoLineItems.add(videoLineItem);
            }
        }

        recyclerView = (RecyclerView) layout.findViewById(R.id.videolist_rcview);
        lineItemAdapter = new LineItemAdapter(getActivity(), videoLineItems);
        recyclerView.setAdapter(lineItemAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        return layout;
    }
}
