package com.cheteam.dreamcatcher.Timeline.API;

import com.cheteam.dreamcatcher.Timeline.Model.TimelineResponse;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Header;
import retrofit2.http.Path;


/**
 * Created by Nicolas Juniar on 03/09/2017.
 */

public interface TimelineAPI {
    @GET("v1/posts/")
    Call<TimelineResponse> GetTimeline();

    @GET("v1/categories/{category}")
    Call<TimelineResponse> GetTimelineByCategory(@Path("category") String id_category);
}
