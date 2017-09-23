package com.cheteam.dreamcatcher.Register.Model;

import com.google.gson.annotations.SerializedName;

/**
 * Created by Nicolas Juniar on 03/09/2017.
 */

public class RegisterResponse {
    @SerializedName("success")
    public boolean success;
    @SerializedName("message")
    public String message;
    @SerializedName("status_code")
    public int status_code;
    @SerializedName("data")
    public DataUser data;

    public class DataUser
    {
        @SerializedName("id_user")
        public int id_user;
        @SerializedName("name")
        public String name;
        @SerializedName("email")
        public String email;
        @SerializedName("token")
        public String token;
    }
}
