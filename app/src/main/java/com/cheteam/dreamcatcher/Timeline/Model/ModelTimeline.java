package com.cheteam.dreamcatcher.Timeline.Model;

import com.cheteam.dreamcatcher.Helper.PreferenceHelper;
import com.google.gson.annotations.SerializedName;

/**
 * Created by Nicolas Juniar on 31/08/2017.
 */

public class ModelTimeline {

    @SerializedName("id_post")
    public int id_post;
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
    @SerializedName("published_at")
    public String published_at;
    @SerializedName("updated_at")
    public String updated_at;
    @SerializedName("name")
    public String name;
    @SerializedName("id_avatar")
    public int id_avatar;



}
