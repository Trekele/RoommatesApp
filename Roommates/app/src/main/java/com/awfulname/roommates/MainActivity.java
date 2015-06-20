package com.awfulname.roommates;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;


public class MainActivity extends Activity {

    private Button btnLogout;
    private UserLocalStore userLocalStore;
    private TextView tvUserName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnLogout = (Button) findViewById(R.id.btnLogout);
        tvUserName = (TextView) findViewById(R.id.tvUserName);
        userLocalStore = new UserLocalStore(this);

        btnLogout.setOnClickListener(new Button.OnClickListener() {
            @Override
            public void onClick(View v) {
                clickLogout();
            }

        });
    }

    @Override
    protected void onStart() {
        super.onStart();

        if (authenticate() == true)
        {
            displayUserDetails();
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

    //method that will be used for logging out
    private void clickLogout() {
        userLocalStore.clearUserData();
        userLocalStore.setUserLoggedIn(false);
        displayUserDetails();
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
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
