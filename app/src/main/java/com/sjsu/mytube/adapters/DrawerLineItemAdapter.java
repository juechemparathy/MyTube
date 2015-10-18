package com.sjsu.mytube.adapters;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.sjsu.mytube.R;
import com.sjsu.mytube.activities.LoginActivity;
import com.sjsu.mytube.activities.PlayerActivity;
import com.sjsu.mytube.models.DrawerLineItem;

import java.util.List;

public class DrawerLineItemAdapter extends RecyclerView.Adapter<DrawerLineItemAdapter.DrawerLineItemViewHolder> {


    private LayoutInflater layoutInflater;
    private List<DrawerLineItem> data;
    private Context context;

    public DrawerLineItemAdapter(Context context, List<DrawerLineItem> data) {
        this.context = context;
        this.layoutInflater = LayoutInflater.from(context);
        this.data = data;
    }

    @Override
    public DrawerLineItemViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View lineItemView = layoutInflater.inflate(R.layout.drawer_lineitem, parent, false);
        DrawerLineItemViewHolder viewHolder = new DrawerLineItemViewHolder(lineItemView);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(DrawerLineItemViewHolder holder, int position) {
        DrawerLineItem current = data.get(position);
        holder.title.setText(current.getTitle());
        holder.icon.setImageResource(current.getIconId());
    }

    @Override
    public int getItemCount() {
        return data.size();
    }


    //ViewHolder for this adapter
    class DrawerLineItemViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        TextView title;
        ImageView icon;

        public DrawerLineItemViewHolder(View itemView) {
            super(itemView);
            title = (TextView) itemView.findViewById(R.id.tv_drawer);
            icon = (ImageView) itemView.findViewById(R.id.iv_drawer);
            icon.setOnClickListener(this);
            title.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            int position = getAdapterPosition();
            //Handle this more elegantly
            if(position == 3){
                Intent i = new Intent(context, LoginActivity.class);
                i.putExtra("action", "logout");
                // Was trying to remove the call stack on logout.
                i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TOP);
                context.startActivity(i);
            }
            else {

            }
        }
    }

}
