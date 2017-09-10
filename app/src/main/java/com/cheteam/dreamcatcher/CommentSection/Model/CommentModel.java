package com.cheteam.dreamcatcher.CommentSection.Model;

import com.google.gson.annotations.SerializedName;

/**
 * Created by Rahmat Al Hakam on 10/09/2017.
 */

public class CommentModel {
    @SerializedName("id_comment")
    public int id_comment;
    public int id_user;
    public String comment_title;
    public String published_at;
}
