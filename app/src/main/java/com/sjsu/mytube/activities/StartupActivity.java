package com.sjsu.mytube.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.plus.Plus;
import com.sjsu.mytube.R;

public class StartupActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_startup);

        // if logged in
        if(LoginActivity.getCredential() != null) {
            Intent i = new Intent(this, MainActivity.class);
            i.putExtra("startUpMode", "normal");
            Log.d("DEBUG", "Starting up in active mode");
            startActivity(i);
        } else{
            Intent i = new Intent(this, LoginActivity.class);
            Log.d("DEBUG", "Starting up in login mode");
            startActivity(i);
        }
    }
}
