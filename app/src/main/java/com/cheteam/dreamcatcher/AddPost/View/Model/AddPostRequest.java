package com.cheteam.dreamcatcher.AddPost.View.Model;

import com.google.gson.annotations.SerializedName;

/**
 * Created by Rahmat Al Hakam on 23/09/2017.
 */

public class AddPostRequest {
    @SerializedName("id_user")
    public int id_user;
    @SerializedName("id_background")
    public int id_background;
    @SerializedName("post_title")
    public String post_title;
    @SerializedName("categories")
    public String categories;
    @SerializedName("content")
    public String content;

    public AddPostRequest(int id_user, int id_background, String post_title, String categories, String content) {
        this.id_user = id_user;
        this.id_background = id_background;
        this.post_title = post_title;
        this.categories = categories;
        this.content = content;
    }
}
