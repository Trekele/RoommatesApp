package com.awfulname.roommates;

import android.app.Activity;
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

    //user's email & password
    private String userEmail, userPassword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        etUserName = (EditText) findViewById(R.id.etUserName);
        etPassword = (EditText) findViewById(R.id.etPassword);
        btnLogin = (Button) findViewById(R.id.btnLogin);
        tvRegister = (TextView) findViewById(R.id.tvRegister);

        //Onclick listener's for Buttons
        btnLogin.setOnClickListener(new Button.OnClickListener()
        {
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
        userEmail = etUserName.getText().toString();
        userPassword = etPassword.getText().toString();
        if (userEmail.isEmpty() || userPassword.isEmpty())
        {
            toast = Toast.makeText(this, "Please Enter All Required Fields", Toast.LENGTH_LONG);
            toast.show();
        }
        else
        {
            toast = Toast.makeText(this, "This Button does nothing", Toast.LENGTH_LONG);
            toast.show();
        }
    }

    private void registerClick()
    {
        Intent i = new Intent(getApplicationContext(), Register.class);
        startActivity(i);
    }

}
