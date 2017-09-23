package com.cheteam.dreamcatcher.ArticlePreview.Model;

import com.google.gson.annotations.SerializedName;

/**
 * Created by Nicolas Juniar on 23/09/2017.
 */

public class BookmarkRequest {
    @SerializedName("id_user")
    private int id_user;
    @SerializedName("id_post")
    private int id_post;

    public BookmarkRequest(int id_user, int id_post) {
        this.id_user = id_user;
        this.id_post = id_post;
    }
}
