package com.cheteam.dreamcatcher.Timeline.Controller;

import com.cheteam.dreamcatcher.Timeline.Model.ModelTimeline;
import com.cheteam.dreamcatcher.Timeline.Model.ResponseTimeline;

import java.util.ArrayList;

import retrofit.Call;
import retrofit.http.GET;

/**
 * Created by Nicolas Juniar on 03/09/2017.
 */

public interface TimelineAPI {
    @GET("v1/posts")
    Call<ArrayList<ModelTimeline>> GetTimeline();
}
