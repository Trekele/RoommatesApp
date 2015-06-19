package com.awfulname.roommates;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

/**
 * Created by Trekele on 6/19/2015.
 */
public class Register extends Activity {
    //Fields/buttons that are on the screen
    private EditText etFirstName;
    private EditText etLastName;
    private EditText etEmail;
    private EditText etUserName;
    private EditText etPassword;
    private EditText etConfirmPassword;
    private Button btnRegister;

    //user's email & password
    private String userEmail, userPassword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        etFirstName = (EditText) findViewById(R.id.etFirstName);
        etLastName = (EditText) findViewById(R.id.etLastName);
        etEmail = (EditText) findViewById(R.id.etEmailAddress);
        etUserName = (EditText) findViewById(R.id.etUserName);
        etPassword = (EditText) findViewById(R.id.etPassword);
        etConfirmPassword = (EditText) findViewById(R.id.etConfirmPassword);
        btnRegister = (Button) findViewById(R.id.btnRegister);

        //Onclick listener's for Button
        btnRegister.setOnClickListener(new Button.OnClickListener()
        {
            @Override
            public void onClick(View v) {
                registerClick();
            }
        });

    }

    private void registerClick()
    {

    }
}
