package com.sjsu.mytube.activities;

import android.accounts.AccountManager;
import android.app.Activity;
import android.content.Intent;
import android.content.IntentSender;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.auth.UserRecoverableAuthException;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.Scopes;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.GoogleApiClient.ConnectionCallbacks;
import com.google.android.gms.common.api.GoogleApiClient.OnConnectionFailedListener;
import com.google.android.gms.common.api.Scope;
import com.google.android.gms.plus.Plus;
import com.google.api.client.googleapis.auth.oauth2.GoogleCredential;
import com.google.api.client.googleapis.extensions.android.gms.auth.GoogleAccountCredential;
import com.sjsu.mytube.R;

import java.util.List;

public class LoginActivity extends Activity {

    private static final int REQUEST_ACCOUNT_PICKER = 1;
    private static final int REQUEST_AUTHORIZATION = 2;

    private static boolean hasCredential = false;
    private static GoogleAccountCredential credential = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        List<String> scope = com.google.common.collect.Lists.newArrayList("https://www.googleapis.com/auth/youtube");

        credential = GoogleAccountCredential.usingOAuth2(this, scope);
        startActivityForResult(credential.newChooseAccountIntent(), REQUEST_ACCOUNT_PICKER);
    }
    protected void onStart() {
        super.onStart();
    }

    protected void onStop() {
        super.onStop();
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        switch ( requestCode ) {
            case REQUEST_ACCOUNT_PICKER:
                if (resultCode == RESULT_OK && data != null && data.getExtras() != null) {
                    String accountName = data.getStringExtra(AccountManager.KEY_ACCOUNT_NAME);
                    if (accountName != null) {
                        credential.setSelectedAccountName(accountName);

                        try {
                            credential.getToken();
                        } catch ( UserRecoverableAuthException exception ) {
                            // expected exception on 1st run with new account name
                            startActivityForResult(exception.getIntent(), REQUEST_AUTHORIZATION);
                        } catch ( Exception exception ) {
                            Log.e( "LoginActivity", "onActivityResult", exception );
                        }

                        hasCredential = true;
                        startActivity( new Intent(this,StartupActivity.class) );
                    }
                } else {
                    startActivityForResult(credential.newChooseAccountIntent(), REQUEST_ACCOUNT_PICKER);
                }
                break;
            case REQUEST_AUTHORIZATION:

                if ( resultCode == Activity.RESULT_OK ) {
                    hasCredential = true;
                    startActivity(new Intent(this, StartupActivity.class));
                } else {
                    startActivityForResult( credential.newChooseAccountIntent(), REQUEST_ACCOUNT_PICKER );
                }

                break;
            default:
                Log.e( "LoginActivity", "onActivityResult", null );
        }
    }

    public static GoogleAccountCredential getCredential() {

        if ( !hasCredential )
            return null;

        return credential;
    }
}