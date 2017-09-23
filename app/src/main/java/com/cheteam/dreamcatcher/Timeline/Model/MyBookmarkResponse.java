package com.cheteam.dreamcatcher.Timeline.Model;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

/**
 * Created by Nicolas Juniar on 23/09/2017.
 */

public class MyBookmarkResponse {
    @SerializedName("bookmarks")
    public ArrayList<Bookmark> bookmarks;
    public class Bookmark
    {
        @SerializedName("id_posts")
        public int id_post;
        @SerializedName("post_title")
        public String post_title;
        @SerializedName("id_background")
        public int id_background;
        @SerializedName("categories")
        public String categories;
        @SerializedName("name")
        public String name;
        @SerializedName("id_avatar")
        public int id_avatar;
        @SerializedName("published_at")
        public String published_at;
    }
}
