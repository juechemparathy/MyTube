package com.sjsu.mytube.fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.sjsu.mytube.R;
import com.sjsu.mytube.adapters.LineItemAdapter;
import com.sjsu.mytube.helpers.YoutubeHelper;
import com.sjsu.mytube.models.VideoInfo;
import com.sjsu.mytube.models.VideoLineItem;

import java.util.ArrayList;
import java.util.List;

public class FavoriteFragment extends Fragment {

    public class DeleteSelectedButtonOnClickListener implements View.OnClickListener {

        private List<String> selectedItemIdList;

        public DeleteSelectedButtonOnClickListener( List<String> _selectedItemIdList ) {
            selectedItemIdList = _selectedItemIdList;
        }

        public void onClick( View v) {
            new Thread() {
                @Override
                public void run() {
                    YoutubeHelper.shared().DeletePlaylistItems(selectedPlaylistItemIds);
                }
            }.start();
        }

    }

    private static final String DATA = "data";

    // TODO: Rename and change types of parameters
    private int data;
    private TextView tvPagePosition;
    private RecyclerView recyclerView;
    private LineItemAdapter lineItemAdapter;
    List<VideoLineItem> videoLineItems = new ArrayList<VideoLineItem>();
    List<String> selectedPlaylistItemIds = new ArrayList<String>();

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

        Button deleteSelected = (Button) layout.findViewById(R.id.deleteSelected);
        deleteSelected.setOnClickListener(new DeleteSelectedButtonOnClickListener(selectedPlaylistItemIds));

        synchronized( this ) {
            Thread thread = new Thread(new Runnable() {
                @Override
                public void run() {
                    List<VideoInfo> videoInfoList = YoutubeHelper.shared().GetPlaylistVideosFavorite();

                    videoLineItems.clear();

                    if (videoInfoList != null) {
                        for (VideoInfo videoInfo : videoInfoList) {
                            VideoLineItem videoLineItem = new VideoLineItem();
                            videoLineItem.setVideoId(videoInfo.getVideoId());
                            videoLineItem.setPlaylistItemId(videoInfo.getPlaylistItemId());
                            videoLineItem.setImageUrl(videoInfo.getThumbnailUrl());
                            videoLineItem.setTitle(videoInfo.getTitle());
                            // videoLineItem.setOwner(videoInfo.getOwner());
                            videoLineItem.setPubdate(videoInfo.getPublishDate());
                            videoLineItem.setViewCount(videoInfo.getViewCount());
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
            });
            thread.start();
        }

        recyclerView = (RecyclerView) layout.findViewById(R.id.videolist_rcview);
        lineItemAdapter = new LineItemAdapter(getActivity(), videoLineItems, false, selectedPlaylistItemIds);
        recyclerView.setAdapter(lineItemAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        return layout;
    }
}
