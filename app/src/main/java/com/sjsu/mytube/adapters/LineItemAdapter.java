package com.sjsu.mytube.adapters;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.sjsu.mytube.R;
import com.sjsu.mytube.activities.PlayerActivity;
import com.sjsu.mytube.models.VideoLineItem;
import com.squareup.picasso.Picasso;

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
        holder.pubdate.setText(current.getPubdate());
        holder.owner.setText(current.getOwner());
        Picasso.with(context).load(current.getImageUrl()).into(holder.icon);
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
            Intent i = new Intent(context, PlayerActivity.class);
            i.putExtra("videoId",data.get(position).getVideoId());
            context.startActivity(i);
        }
    }

}
