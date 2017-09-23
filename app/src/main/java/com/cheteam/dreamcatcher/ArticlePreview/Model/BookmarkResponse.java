package com.cheteam.dreamcatcher.ArticlePreview.Model;

import com.google.gson.annotations.SerializedName;

/**
 * Created by Nicolas Juniar on 23/09/2017.
 */

public class BookmarkResponse {
    @SerializedName("success")
    public boolean success;
    @SerializedName("message")
    public String message;
    @SerializedName("status_code")
    public int status_code;
}
