package com.cheteam.dreamcatcher.Timeline.API;

import com.cheteam.dreamcatcher.Timeline.Model.TimelineResponse;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.POST;


/**
 * Created by Nicolas Juniar on 03/09/2017.
 */

public interface TimelineAPI {
    @GET("v1/posts")
    Call<TimelineResponse> GetTimeline();


}
