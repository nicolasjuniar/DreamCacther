package com.cheteam.dreamcatcher.Login.Model;

/**
 * Created by Nicolas Juniar on 03/09/2017.
 */

public class UserModel {
    int id_user;
    String email;
    String password;

    public UserModel(int id_user, String email, String password) {
        this.id_user = id_user;
        this.email = email;
        this.password = password;
    }

    public int getId_user() {
        return id_user;
    }

    public void setId_user(int id_user) {
        this.id_user = id_user;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
