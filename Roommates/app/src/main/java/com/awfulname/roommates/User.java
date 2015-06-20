package com.awfulname.roommates;

/**
 * Created by Trekele on 6/19/2015.
 */
public class User
{

    private String name;
    private String password;
    private String email;
    private String username;
    private int id;

    public User(String username, String password)
    {
        this.password = password;
        this.username = username;
        this.name = "";
        this.email = "";
        this.id = -1;
    }

    public User(String name, String username, String password, String email)
    {
        this.name = name;
        this.password = password;
        this.email = email;
        this.username = username;
    }

    public User(String username, String name, String email, int id)
    {
        this.password = "";
        this.username = username;
        this.name = name;
        this.email = email;
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
}
