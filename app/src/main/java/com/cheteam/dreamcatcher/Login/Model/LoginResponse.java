package com.cheteam.dreamcatcher.Login.Model;

import com.google.gson.annotations.SerializedName;

/**
 * Created by Nicolas Juniar on 03/09/2017.
 */

public class LoginResponse {
    @SerializedName("token")
    public String token;
    @SerializedName("email")
    public String email;
    @SerializedName("success")
    public String success;
    @SerializedName("message")
    public String message;
    @SerializedName("status_code")
    public String status_code;
}
