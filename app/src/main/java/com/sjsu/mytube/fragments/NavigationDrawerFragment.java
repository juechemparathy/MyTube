package com.sjsu.mytube.fragments;


import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.sjsu.mytube.R;
import com.sjsu.mytube.adapters.DrawerLineItemAdapter;
import com.sjsu.mytube.data.DrawerData;

/**
 * A simple {@link Fragment} subclass.
 */
public class NavigationDrawerFragment extends Fragment {

    private static final String PREF_FILENAME = "preferencesFile";
    private static final String USER_LEARNED_KEY = "userLearnedDrawer";

    private RecyclerView recyclerView;
    private DrawerLineItemAdapter drawerLineItemAdapter;

    private ActionBarDrawerToggle drawerToggle;
    private DrawerLayout drawerLayout;

    private boolean userLearnedDrawer;
    private boolean fromSavedInstanceState;
    View containerView;

    public NavigationDrawerFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        userLearnedDrawer = Boolean.valueOf(readFromPreferences(getActivity(), USER_LEARNED_KEY, "false"));
        if (savedInstanceState != null) {
            fromSavedInstanceState = true;
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View layout =  inflater.inflate(R.layout.fragment_navigation_drawer, container, false);
        recyclerView = (RecyclerView)layout.findViewById(R.id.drawer_rcview);
        drawerLineItemAdapter = new DrawerLineItemAdapter(getActivity(), DrawerData.getDrawerItems());
        recyclerView.setAdapter(drawerLineItemAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        return layout;

    }


    public void setUp(int fragmentId, DrawerLayout drawerLayout, final Toolbar toolbar) {
        containerView = getActivity().findViewById(fragmentId);
        this.drawerLayout = drawerLayout;
        drawerToggle = new ActionBarDrawerToggle(getActivity(), this.drawerLayout, toolbar, R.string.drawer_open, R.string.drawer_close) {
            @Override
            public void onDrawerOpened(View drawerView) {
                super.onDrawerOpened(drawerView);
                if (!userLearnedDrawer) {
                    userLearnedDrawer = true;
                    saveToPreferecnes(getActivity(), USER_LEARNED_KEY, "true");
                }
                //redraw the menu - Need to understand more. #9 3.11
                getActivity().invalidateOptionsMenu();
            }

            @Override
            public void onDrawerClosed(View drawerView) {
                super.onDrawerClosed(drawerView);
                //redraw the menu
                getActivity().invalidateOptionsMenu();
            }

            public void onDrawerSlide(View drawerView, float slideOffset) {
                if (slideOffset < 0.6) {
                    toolbar.setAlpha(1 - slideOffset);
                }
            }

        };

        if (!userLearnedDrawer && !fromSavedInstanceState) {
            drawerLayout.openDrawer(containerView);
        }
        drawerLayout.setDrawerListener(drawerToggle);

        //syncing drawer
        drawerLayout.post(new Runnable() {
            @Override
            public void run() {
                drawerToggle.syncState();
            }
        });

    }


    public static void saveToPreferecnes(Context context, String prefName, String prefValue) {
        SharedPreferences sharedPreferences = context.getSharedPreferences(PREF_FILENAME, context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString(prefName, prefValue);
        //try editor.commit(); Synchronous
        editor.apply();//Async
    }

    public static String readFromPreferences(Context context, String prefName, String defaultValue) {
        SharedPreferences sharedPreferences = context.getSharedPreferences(PREF_FILENAME, context.MODE_PRIVATE);
        return sharedPreferences.getString(prefName, defaultValue);
    }

}
