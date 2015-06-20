package com.awfulname.roommates;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;
import java.util.ArrayList;

/**
 * Created by Trekele on 6/19/2015.
 */
public class ServerRequests
{
    private ProgressDialog progressDialog;
    public static final int CONNECTION_TIMEOUT = 1000 * 15;
    public static final String SERVER_ADDRESS = "http://roommates.comoj.com/";

    public ServerRequests(Context context)
    {
        progressDialog = new ProgressDialog(context);
        progressDialog.setCancelable(false);
        progressDialog.setTitle("Processing");
        progressDialog.setTitle("Please wait...");
    }

    public void storeUserDataInBackground(User user, GetUserCallback callBack)
    {
        progressDialog.show();
        StoreUserDataAsyncTask storeUserDataAsyncTask = new StoreUserDataAsyncTask(user, callBack);
        storeUserDataAsyncTask.execute();
    }

    public void fetchUserDataInBackground(User user, GetUserCallback callBack)
    {
        progressDialog.show();
        FetchUserDataAsyncTask fetchUserData = new FetchUserDataAsyncTask(user, callBack);
        fetchUserData.execute();

    }

    public class StoreUserDataAsyncTask extends AsyncTask<Void, Void, Void>
    {
        private User user;
        private GetUserCallback userCallback;

        public StoreUserDataAsyncTask(User user, GetUserCallback callBack)
        {
            this.user = user;
            this.userCallback = callBack;
        }

        @Override
        protected Void doInBackground(Void... params)
        {
            JSONObject dataToSend = new JSONObject();
            try
            {
                dataToSend.put("name", user.getName());
                dataToSend.put("email", user.getEmail());
                dataToSend.put("username", user.getUsername());
                dataToSend.put("password", user.getPassword());

            }
            catch (JSONException e)
            {
                System.out.println(e.getMessage());
            }

            try
            {
                URL url = new URL(SERVER_ADDRESS + "Roommates/register.php");

                //establish connection
                HttpURLConnection client = (HttpURLConnection) url.openConnection();
                client.setDoOutput(true);
                client.setDoInput(true);
                client.setRequestProperty("Content-Type", "application/json; charset=UTF-8");
                client.setRequestMethod("POST");
                client.connect();
                //write to stream
                OutputStreamWriter wr = new OutputStreamWriter(client.getOutputStream());
                wr.write(dataToSend.toString());
                wr.flush();
                wr.close();

                //display what returns the post request
                InputStream input = client.getInputStream();
                BufferedReader reader = new BufferedReader(new InputStreamReader(input));
                StringBuilder r = new StringBuilder();
                String line;
                while ((line = reader.readLine()) != null)
                {
                    r.append(line);
                }
                client.disconnect();
            }
            catch (Exception ex)
            {
                System.out.println(ex.getMessage());
            }
            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid)
        {
            progressDialog.dismiss();
            userCallback.done(null);
            super.onPostExecute(aVoid);
        }
    }

    public class FetchUserDataAsyncTask extends AsyncTask<Void, Void, User>
    {
        private User user;
        private GetUserCallback userCallback;

        public FetchUserDataAsyncTask(User user, GetUserCallback callBack)
        {
            this.user = user;
            this.userCallback = callBack;
        }

        @Override
        protected User doInBackground(Void... params)
        {
            JSONObject dataToSend = new JSONObject();
            User returnedUser = null;
            try
            {
                dataToSend.put("username", user.getUsername());
                dataToSend.put("password", user.getPassword());
            }
            catch (JSONException e)
            {
                System.out.println(e.getMessage());
            }

            try
            {
                URL url = new URL(SERVER_ADDRESS + "Roommates/login.php");

                //establish connection
                HttpURLConnection client = (HttpURLConnection) url.openConnection();
                client.setDoOutput(true);
                client.setDoInput(true);
                client.setRequestProperty("Content-Type", "application/json; charset=UTF-8");
                client.setRequestMethod("POST");
                client.connect();
                //write to stream
                OutputStreamWriter wr = new OutputStreamWriter(client.getOutputStream());
                wr.write(dataToSend.toString());
                wr.flush();
                wr.close();

                //display what returns the post request
                InputStream input = client.getInputStream();
                BufferedReader reader = new BufferedReader(new InputStreamReader(input));
                StringBuilder r = new StringBuilder();
                String line;
                while ((line = reader.readLine()) != null)
                {
                    r.append(line);
                }

                JSONObject jsonUser = new JSONObject(r.toString());
                if (jsonUser.length() == 0)
                {
                    returnedUser = null;
                }
                else
                {
                    String name = jsonUser.getString("name");
                    int id = jsonUser.getInt("id");
                    String username = jsonUser.getString("username");
                    String email = jsonUser.getString("email");
                    returnedUser = new User(name, username, email, id);
                }

                client.disconnect();
            }
            catch (Exception ex)
            {
                System.out.println(ex.getMessage());
            }
            return returnedUser;
        }

        @Override
        protected void onPostExecute(User returnedUser)
        {
            progressDialog.dismiss();
            userCallback.done(returnedUser);
            super.onPostExecute(returnedUser);
        }
    }
}
