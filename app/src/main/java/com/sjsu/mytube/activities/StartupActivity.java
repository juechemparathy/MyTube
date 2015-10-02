package com.sjsu.mytube.activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;

import com.sjsu.mytube.R;

public class StartupActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_startup);
        //check for upgrade stage
        checkForUpgrade();
        //check for UserPref init page
        checkForUserPrefSettings();

        // determine if we need to handle main page customized for the user.
        // For example showing guidelines if its a first time user etc.

        Intent i = new Intent(this, LoginActivity.class);
        i.putExtra("startUpMode", "normal");
        Log.d("DEBUG", "Starting up in normal mode");
        startActivity(i);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_startup, menu);
        return true;
    }

    private void checkForUpgrade() {

    }

    private void checkForUserPrefSettings() {

    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
