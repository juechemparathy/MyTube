package com.sjsu.mytube.adapters;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.youtube.player.YouTubeStandalonePlayer;
import com.sjsu.mytube.R;
import com.sjsu.mytube.activities.PlayerActivity;
import com.sjsu.mytube.models.VideoLineItem;

import java.util.List;

public class LineItemAdapter extends RecyclerView.Adapter<LineItemAdapter.LineItemViewHolder> {


    private LayoutInflater layoutInflater;
    private List<VideoLineItem> data;
    private Context context;

    public LineItemAdapter(Context context, List<VideoLineItem> data) {
        this.context = context;
        this.layoutInflater = LayoutInflater.from(context);
        this.data = data;
    }

    @Override
    public LineItemViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View lineItemView = layoutInflater.inflate(R.layout.video_lineitem, parent, false);
        LineItemViewHolder viewHolder = new LineItemViewHolder(lineItemView);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(LineItemViewHolder holder, int position) {
        VideoLineItem current = data.get(position);
        holder.title.setText(current.getTitle());
        holder.icon.setImageResource(R.drawable.video_play_image);
        holder.pubdate.setText(current.getPubdate());
        holder.owner.setText(current.getOwner());
    }

    @Override
    public int getItemCount() {
        return data.size();
    }


    //ViewHolder for this adapter
    class LineItemViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        TextView title;
        ImageView icon;
        TextView owner;
        TextView pubdate;

        public LineItemViewHolder(View itemView) {
            super(itemView);
            title = (TextView) itemView.findViewById(R.id.tv_title);
            owner = (TextView) itemView.findViewById(R.id.tv_owner);
            pubdate = (TextView) itemView.findViewById(R.id.tv_pubdate);
            icon = (ImageView) itemView.findViewById(R.id.iv_image);

            icon.setOnClickListener(this);
            title.setOnClickListener(this);
            owner.setOnClickListener(this);
            pubdate.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            int position = getPosition();
            //Handle this more elegantly
            if (position == 1) {
                Intent i = new Intent(context, PlayerActivity.class);
                context.startActivity(i);
            } else {
                Intent intent = null;
                intent= YouTubeStandalonePlayer.createVideoIntent((Activity) context,PlayerActivity.API_KEY, PlayerActivity.VIDEO_ID,0,true,false);
                context.startActivity(intent);
                Toast.makeText(context, "Clicked on position " + position, Toast.LENGTH_SHORT).show();
            }
        }
    }

}
