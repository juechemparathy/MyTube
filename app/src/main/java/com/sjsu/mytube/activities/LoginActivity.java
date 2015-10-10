package com.sjsu.mytube.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.sjsu.mytube.R;


public class LoginActivity extends AppCompatActivity {

    private EditText et_username;
    private EditText et_password;
    private EditText et_email;
    private EditText et_phonenumber;
    private Button bt_login;
    boolean isUserLoggedIn;
    String errorMessage;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
//        ParseUser.logOut();
    }


    public void onLogin(View view) {

        errorMessage = null;
        isUserLoggedIn = false;

        et_username = (EditText) findViewById(R.id.et_username);
        et_password = (EditText) findViewById(R.id.et_password);

        et_email = (EditText) findViewById(R.id.et_email);
        et_phonenumber = (EditText) findViewById(R.id.et_phone);

        if (et_email.getText().toString().length() == 0 || et_phonenumber.getText().length() == 0) {
            //LOGIN
//            ParseUser.logInInBackground(et_username.getText().toString(), et_password.getText().toString(), new LogInCallback() {
//                public void done(ParseUser user, ParseException e) {
//                    if (user != null) {
//                        // Hooray! The user is logged in.
//                        callMainActivity();
//                    } else {
//                        errorMessage = "Invalid username/password";
//                        // Signup failed. Look at the ParseException to see what happened.
//                    }
//                }
//            });
        } else {

            //SIGN-UP
            // Create the ParseUser
//            ParseUser user = new ParseUser();
//            // Set core properties
//            user.setUsername(et_username.getText().toString());
//            user.setPassword(et_password.getText().toString());
//            user.setEmail(et_email.getText().toString());
//            // Set custom properties
//            user.put("phone", Integer.parseInt(et_phonenumber.getText().toString()));
//            // Invoke signUpInBackground
//            user.signUpInBackground(new SignUpCallback() {
//                public void done(ParseException e) {
//                    if (e == null) {
//
//                        isUserLoggedIn = true;
//
//                        // Hooray! Let them use the app now.
//                    } else {
//                        errorMessage = e.getMessage();
//                        // Sign up didn't succeed. Look at the ParseException
//                        // to figure out what went wrong
//                    }
//                }
//            });
        }

        if (isUserLoggedIn) {
            callMainActivity();
        }

        if (errorMessage != null) {
            Toast.makeText(this, errorMessage, Toast.LENGTH_SHORT).show();
        }
    }

    private void callMainActivity() {
        Intent i = new Intent(this, com.sjsu.mytube.activities.MainActivity.class);
        i.putExtra("startUpMode", "normal");
        Log.d("DEBUG", "Starting up in normal mode");
        startActivity(i);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_login, menu);
        return true;
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


//    //This is a work around for using back button after logout.
//    //Need to work on aexception thrown at this stage - harmless but need to fix.
    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            moveTaskToBack(true);
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }
}
