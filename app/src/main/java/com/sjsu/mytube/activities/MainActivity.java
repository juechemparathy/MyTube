package com.sjsu.mytube.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;

import com.sjsu.mytube.R;
import com.sjsu.mytube.adapters.MainPagerAdapter;
import com.sjsu.mytube.fragments.NavigationDrawerFragment;
import com.sjsu.mytube.helpers.SlidingTabLayout;

//icons  - https://www.iconfinder.com/icons/520566/glass_magnifier_magnifying_search_zoom_icon#size=128
public class MainActivity extends AppCompatActivity {

    private Toolbar toolbar;
    ViewPager viewPager;
    SlidingTabLayout mTabs;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        toolbar = (Toolbar) findViewById(R.id.app_bar);
        setSupportActionBar(toolbar);

        NavigationDrawerFragment navigatorFragment = (NavigationDrawerFragment) getSupportFragmentManager().findFragmentById(R.id.navigation_fragment);
        navigatorFragment.setUp(R.id.navigation_fragment, (DrawerLayout) findViewById(R.id.drawer_layout), toolbar);

        viewPager= (ViewPager)findViewById(R.id.pager);
        viewPager.setAdapter(new MainPagerAdapter(getSupportFragmentManager(),this));
        mTabs = (SlidingTabLayout) findViewById(R.id.tabs);
        mTabs.setDistributeEvenly(true);
        mTabs.setCustomTabView(R.layout.main_tab_view, R.id.tabText);
        mTabs.setBackgroundColor(getResources().getColor(R.color.primary_light));
        mTabs.setCustomTabColorizer(new SlidingTabLayout.TabColorizer() {
            @Override
            public int getIndicatorColor(int position) {
                return getResources().getColor(R.color.accent);
            }
        });
        mTabs.setViewPager(viewPager);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        if (id == R.id.menu_something) {
            startActivity(new Intent(this, LoginActivity.class));
        }

        return super.onOptionsItemSelected(item);
    }

}
