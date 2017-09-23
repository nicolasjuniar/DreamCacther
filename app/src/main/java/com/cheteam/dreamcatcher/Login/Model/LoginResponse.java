package com.cheteam.dreamcatcher.Login.Model;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

/**
 * Created by Nicolas Juniar on 03/09/2017.
 */

public class LoginResponse {
    @SerializedName("id_user")
    public int id_user;
    @SerializedName("email")
    public int email;
    @SerializedName("name")
    public String name;
    @SerializedName("token")
    public String token;
    @SerializedName("address")
    public String address;
    @SerializedName("bio")
    public String bio;
    @SerializedName("id_avatar")
    public int id_avatar;
    @SerializedName("id_cover_photo")
    public int id_cover_photo;
    @SerializedName("total_posts")
    public int total_posts;
    @SerializedName("published_at")
    public String published_at;
    @SerializedName("updated_at")
    public String updated_at;
    @SerializedName("message")
    public String message;
    @SerializedName("categories")
    public ArrayList<String> categories;
    @SerializedName("success")
    public boolean success;
    @SerializedName("status_code")
    public int status_code;
}
