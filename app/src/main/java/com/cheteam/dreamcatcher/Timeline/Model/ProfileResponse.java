package com.cheteam.dreamcatcher.Timeline.Model;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

/**
 * Created by Nicolas Juniar on 10/09/2017.
 */

public class ProfileResponse {
    @SerializedName("id_user")
    public int id_user;
    @SerializedName("name")
    public String name;
    @SerializedName("address")
    public String address;
    @SerializedName("bio")
    public String bio;
    @SerializedName("total_posts")
    public int total_posts;
    @SerializedName("id_avatar")
    public int id_avatar;
    @SerializedName("id_cover_photo")
    public int id_cover_photo;
    @SerializedName("id_categories")
    public ArrayList<Integer> id_categories;
    @SerializedName("success")
    public boolean success;
    @SerializedName("message")
    public String message;
    @SerializedName("status_code")
    public int status_code;
    @SerializedName("email")
    public String email;
    @SerializedName("token")
    public String token;
}
