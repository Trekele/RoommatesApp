package com.awfulname.roommates;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;


public class MainActivity extends Activity {

    private UserLocalStore userLocalStore;
    private TextView tvUserName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tvUserName = (TextView) findViewById(R.id.tvUserName);
        userLocalStore = new UserLocalStore(this);
    }

    @Override
    protected void onStart() {
        super.onStart();

        if (authenticate() == true)
        {
            displayUserDetails();
        }
        else
        {
            Intent i = new Intent(getApplicationContext(), Login.class);
            startActivity(i);
        }
    }

    //checks to see if a user is logged in
    private boolean authenticate()
    {
        return userLocalStore.getUserLoginStatus();
    }

    private void displayUserDetails()
    {
        User user = userLocalStore.getLoggedInUser();

        tvUserName.setText("Welcome " + user.getName());
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

        //noinspection SimplifiableIfStatement
        switch(id) {
            case R.id.action_settings:
                return true;
            case R.id.action_help:
                return true;
            case R.id.action_signout:
                userLocalStore.clearUserData();
                userLocalStore.setUserLoggedIn(false);
                Intent i = new Intent(getApplicationContext(), Login.class);
                startActivity(i);
                return true;
        }
        /*if (id == R.id.action_settings) {
            return true;
        }*/

        return super.onOptionsItemSelected(item);
    }
}
