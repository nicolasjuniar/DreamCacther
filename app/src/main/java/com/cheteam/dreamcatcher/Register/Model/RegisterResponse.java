package com.cheteam.dreamcatcher.Register.Model;

import com.google.gson.annotations.SerializedName;

/**
 * Created by Nicolas Juniar on 03/09/2017.
 */

public class RegisterResponse {
    @SerializedName("success")
    public String success;
    @SerializedName("message")
    public String message;
    @SerializedName("status_code")
    public String status_code;
    @SerializedName("data")
    public DataUser data;

    public class DataUser
    {
        public int id_user;
        public String name;
        public String email;
    }
}
