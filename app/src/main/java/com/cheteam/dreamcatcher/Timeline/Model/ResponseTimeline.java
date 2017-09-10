package com.cheteam.dreamcatcher.Timeline.Model;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

/**
 * Created by Nicolas Juniar on 03/09/2017.
 */

public class ResponseTimeline {
    @SerializedName("posts")
    public ArrayList<ModelTimeline> posts;
    @SerializedName("success")
    public String success;
    @SerializedName("message")
    public String message;
    @SerializedName("status_code")
    public String status_code;
}
