package com.sjsu.mytube.data;

import com.sjsu.mytube.R;
import com.sjsu.mytube.models.DrawerLineItem;

import java.util.ArrayList;
import java.util.List;


public class DrawerData {
    private static List<DrawerLineItem> data;

    public static List<DrawerLineItem> getDrawerItems() {
        if (data == null || data.size() == 0) {
            data = new ArrayList();

            DrawerLineItem lineItem = new DrawerLineItem();
            lineItem.setIconId(R.drawable.mytube_search);
            lineItem.setTitle("Search");
            data.add(lineItem);

            lineItem = new DrawerLineItem();
            lineItem.setIconId(R.drawable.mytube_favorite);
            lineItem.setTitle("Favorites");
            data.add(lineItem);

            lineItem = new DrawerLineItem();
            lineItem.setIconId(R.drawable.mytube_playlist);
            lineItem.setTitle("Playlist");
            data.add(lineItem);

            lineItem = new DrawerLineItem();
            lineItem.setIconId(R.drawable.mytube_eye);
            lineItem.setTitle("Logout");
            data.add(lineItem);
        }
        return data;
    }
}
