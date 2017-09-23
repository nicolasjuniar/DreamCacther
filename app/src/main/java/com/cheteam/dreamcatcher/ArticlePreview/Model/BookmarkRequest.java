package com.cheteam.dreamcatcher.ArticlePreview.Model;

import com.google.gson.annotations.SerializedName;

/**
 * Created by Nicolas Juniar on 23/09/2017.
 */

public class BookmarkRequest {
    @SerializedName("id_user")
    public int id_user;
    @SerializedName("id_post")
    public int id_post;
}
