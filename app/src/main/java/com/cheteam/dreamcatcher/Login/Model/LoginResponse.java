package com.cheteam.dreamcatcher.Login.Model;

import com.google.gson.annotations.SerializedName;

/**
 * Created by Nicolas Juniar on 03/09/2017.
 */

public class LoginResponse {
    @SerializedName("message")
    public String message;
    @SerializedName("success")
    public boolean success;
    @SerializedName("status_code")
    public int status_code;
    @SerializedName("token")
    public String token;
    @SerializedName("email")
    public String email;
}
