package com.sjsu.mytube.adapters;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.style.ImageSpan;

import com.sjsu.mytube.R;
import com.sjsu.mytube.fragments.FavoriteFragment;
import com.sjsu.mytube.fragments.PlaylistFragment;
import com.sjsu.mytube.fragments.SearchFragment;

public class MainPagerAdapter extends FragmentPagerAdapter {

    Context context;

//    String[] tabTitles = { "Playlist","Search"};
//    int icons[] = {R.drawable.mytube_playlist,R.drawable.mytube_search};

    String[] tabTitles = { "Playlist","Search", "Favorites",};
    int icons[] = {R.drawable.mytube_playlist,R.drawable.mytube_search, R.drawable.mytube_favorite};


    public MainPagerAdapter(FragmentManager fm,Context c) {
        super(fm);
        this.context=c;
    }

    @Override
    public Fragment getItem(int position) {
        Fragment fragment = null;
        switch (position){
            case 0:
                fragment = new PlaylistFragment();
                break;
            case 1:
                fragment = new SearchFragment();

            break;
            case 2:
                fragment = new FavoriteFragment();
                break;
            default:
                fragment =  new SearchFragment();
                break;
        }
        return fragment;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        Drawable drawable =this.context.getResources().getDrawable(icons[position]);
        drawable.setBounds(0,0,50,50);
        ImageSpan imageSpan = new ImageSpan(drawable);
        //notice the space in the spannable string below else it wont work.
        SpannableString spannableString = new SpannableString(" ");
        spannableString.setSpan(imageSpan,0,spannableString.length(), Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        return spannableString;
    }

    @Override
    public int getCount() {
        return 3;
    }
}
