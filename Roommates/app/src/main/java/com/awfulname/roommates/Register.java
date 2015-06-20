package com.awfulname.roommates;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

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

        //getting all of the text fields and buttons
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
        try
        {
            //store text in variables from the text fields
            String firstName = etFirstName.getText().toString();
            String lastName = etLastName.getText().toString();
            String email = etEmail.getText().toString();
            String userName = etUserName.getText().toString();
            String password = etPassword.getText().toString();
            String confirmPassword = etConfirmPassword.getText().toString();

            //check that all fields contained something
            if (firstName.isEmpty() || lastName.isEmpty() || email.isEmpty() || userName.isEmpty() || password.isEmpty() || confirmPassword.isEmpty())
            {
                Toast toast = Toast.makeText(this, "Please enter all required fields", Toast.LENGTH_LONG);
                toast.show();
                return;
            }
            //make sure the passwords were equal to each other
            if (!password.equals(confirmPassword))
            {
                Toast toast = Toast.makeText(this, "Passwords do not match", Toast.LENGTH_LONG);
                toast.show();
                return;
            }
            //create a new user
            else
            {
                String fullName = firstName + " " + lastName;
                User user = new User(fullName, userName, password, email);
            }
        }
        catch(Exception e)
        {
            System.out.println(e.getMessage());
        }

    }
}
