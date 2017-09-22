package com.cheteam.dreamcatcher.Timeline.Model;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by MPR on 9/23/2017.
 */

public class BookmarkResponse {
    @SerializedName("bookmarks")
    public ArrayList<ModelTimeline>bookmark;
    @SerializedName("id_post")
    public int id_Post;
    @SerializedName("post_tittle")
    public String post_Title;
    @SerializedName("id_background")
    public int id_Background;
    @SerializedName("name")
    public String name;
    @SerializedName("id_avatar")
    public int id_Avatar;
    @SerializedName("published_at")
    public String published_At;

}
