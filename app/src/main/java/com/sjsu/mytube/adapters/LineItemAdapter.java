package com.sjsu.mytube.adapters;

import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.sjsu.mytube.R;
import com.sjsu.mytube.activities.SomeActivity;
import com.sjsu.mytube.helpers.YoutubeHelper;
import com.sjsu.mytube.models.VideoLineItem;

import java.net.URL;
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
    public void onBindViewHolder( final LineItemViewHolder holder, int position ) {
        final VideoLineItem current = data.get(position);

        try {
            holder.title.setText(current.getTitle());
            holder.pubdate.setText(current.getPubdate().toString());
            holder.owner.setText(current.getViewCount().toString());   // FIXME
        }
        catch (Exception e) {
            Log.e("LineItemAdapter", "Unkown Exception.", e);
        }

        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    URL thumbUrl = new URL( current.getImageUrl() );
                    final Drawable thumbDrawable = Drawable.createFromStream(thumbUrl.openStream(), "src");
                    synchronized ( holder )
                    {
                        holder.rootView.post(new Runnable() {
                            public void run() {
                                holder.icon.setImageDrawable(thumbDrawable);
                            }
                        });
                    }
                }
                catch (Exception e) {
                    Log.e("LineItemAdapter", "Unkown Exception.", e);
                }
            }
        });

        thread.start();
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    // on click handler for the star button on LineItemViewHolder
    class LineItemStarButtonClickListener implements View.OnClickListener {

        private LineItemViewHolder holder;

        public LineItemStarButtonClickListener( LineItemViewHolder _holder ) {
            holder = _holder;
        }

        @Override
        public void onClick(View view) {
            Thread thread = new Thread ( new Runnable() {
                @Override
                public void run() {
                    final int position = holder.getPosition();
                    final VideoLineItem current = data.get(position);
                    final String videoId = current.getVideoId();
                    boolean result = YoutubeHelper.shared().PlaylistInsertStarred( videoId );
                }
            } );
            thread.start();
        }
    }

    //ViewHolder for this adapter
    class LineItemViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        TextView title;
        ImageView icon;
        TextView owner;
        TextView pubdate;
        Button starButton;
        View rootView;

        public LineItemViewHolder(View itemView) {
            super(itemView);
            rootView = itemView;
            title = (TextView) itemView.findViewById(R.id.tv_title);
            owner = (TextView) itemView.findViewById(R.id.tv_owner);
            pubdate = (TextView) itemView.findViewById(R.id.tv_pubdate);
            icon = (ImageView) itemView.findViewById(R.id.iv_image);
            starButton = (Button) itemView.findViewById(R.id.starButton);

            icon.setOnClickListener(this);
            title.setOnClickListener(this);
            owner.setOnClickListener(this);
            pubdate.setOnClickListener(this);
            starButton.setOnClickListener( new LineItemStarButtonClickListener( this ) );
        }

        @Override
        public void onClick(View view) {
            int position = getPosition();
            //Handle this more elegantly
            if (position == 1) {
                context.startActivity(new Intent(context, SomeActivity.class));
            } else {
                Toast.makeText(context, "Clicked on position " + position, Toast.LENGTH_SHORT).show();
            }
        }
    }

}
