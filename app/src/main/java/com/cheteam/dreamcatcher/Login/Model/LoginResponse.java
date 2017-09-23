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
    public String email;
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

    public void setId_user(int id_user) {
        this.id_user = id_user;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public void setBio(String bio) {
        this.bio = bio;
    }

    public void setId_avatar(int id_avatar) {
        this.id_avatar = id_avatar;
    }

    public void setId_cover_photo(int id_cover_photo) {
        this.id_cover_photo = id_cover_photo;
    }

    public void setTotal_posts(int total_posts) {
        this.total_posts = total_posts;
    }

    public void setPublished_at(String published_at) {
        this.published_at = published_at;
    }

    public void setUpdated_at(String updated_at) {
        this.updated_at = updated_at;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public void setCategories(ArrayList<String> categories) {
        this.categories = categories;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public void setStatus_code(int status_code) {
        this.status_code = status_code;
    }
}
