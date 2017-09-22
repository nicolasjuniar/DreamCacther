package com.cheteam.dreamcatcher.AddPost.View.Model;

import com.google.gson.annotations.SerializedName;

/**
 * Created by Rahmat Al Hakam on 23/09/2017.
 */

public class AddPostResponse {
    @SerializedName("id_post")
    public int id_post;
    @SerializedName("id_user")
    public int id_user;
    @SerializedName("post_title")
    public String post_title;
    @SerializedName("id_background")
    public int id_background;
    @SerializedName("categories")
    public String categories;
    @SerializedName("content")
    public String content;
    @SerializedName("published_at")
    public String published_at;
}
