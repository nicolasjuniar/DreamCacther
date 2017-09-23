package com.cheteam.dreamcatcher.Login.Model;

import com.google.gson.annotations.SerializedName;

/**
 * Created by Nicolas Juniar on 22/09/2017.
 */

public class LoginRequest {
    @SerializedName("email")
    String email;
    @SerializedName("password")
    String password;

    public LoginRequest(String email, String password) {
        this.email = email;
        this.password = password;
    }
}
