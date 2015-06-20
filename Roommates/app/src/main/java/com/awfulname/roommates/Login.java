package com.awfulname.roommates;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

/**
 * Created by Trekele on 6/19/2015.
 */
public class Login extends Activity {

    //Fields/buttons that are on the screen
    private EditText etUserName, etPassword;
    private Button btnLogin;
    private TextView tvRegister;


    private UserLocalStore userLocalStore;

    //user's email & password
    private String userName, userPassword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        etUserName = (EditText) findViewById(R.id.etUserName);
        etPassword = (EditText) findViewById(R.id.etPassword);
        btnLogin = (Button) findViewById(R.id.btnLogin);
        tvRegister = (TextView) findViewById(R.id.tvRegister);

        userLocalStore = new UserLocalStore(this);

        //Onclick listener's for Buttons
        btnLogin.setOnClickListener(new Button.OnClickListener() {
            @Override
            public void onClick(View v) {
                loginClick();
            }
        });

        tvRegister.setOnClickListener(new TextView.OnClickListener() {
            @Override
            public void onClick(View v) {
                registerClick();
            }
        });
    }


    private void loginClick()
    {
        Toast toast;
        //get username and password
        userName = etUserName.getText().toString();
        userPassword = etPassword.getText().toString();
        //make sure that the user entered in all data
        if (userName.isEmpty() || userPassword.isEmpty())
        {
            toast = Toast.makeText(this, "Please Enter All Required Fields", Toast.LENGTH_LONG);
            toast.show();
            return;
        }
        //if they did, authenticate the user.
        else
        {
            User user = new User(userName, userPassword);
            authenticate(user);
        }
    }

    private void registerClick()
    {
        Intent i = new Intent(getApplicationContext(), Register.class);
        startActivity(i);
    }

    private void authenticate(User user)
    {
        ServerRequests serverRequests = new ServerRequests(this);
        serverRequests.fetchUserDataInBackground(user, new GetUserCallback() {
            @Override
            public void done(User returnedUser)
            {
                if (returnedUser == null) {
                    showErrorMessage();
                }
                else
                {
                    logUserIn(returnedUser);
                }
            }
        });
    }

    private void logUserIn(User returnedUser)
    {
        userLocalStore.storeUserData(returnedUser);
        userLocalStore.setUserLoggedIn(true);

        Intent i = new Intent(getApplicationContext(), MainActivity.class);
        startActivity(i);
    }

    private void showErrorMessage()
    {
        AlertDialog.Builder dialogBuilder = new AlertDialog.Builder(Login.this);
        dialogBuilder.setMessage("No such user was found");
        dialogBuilder.setPositiveButton("Ok", null);
        dialogBuilder.show();
    }
}
