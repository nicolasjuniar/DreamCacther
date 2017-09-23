package com.cheteam.dreamcatcher.Timeline.Controller;

import com.cheteam.dreamcatcher.ServiceGenerator;
import com.cheteam.dreamcatcher.Timeline.API.TimelineAPI;
import com.cheteam.dreamcatcher.Timeline.Model.TimelineResponse;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by Nicolas Juniar on 13/09/2017.
 */

public class TimelineController {
    TimelineAPI service;
    Call<TimelineResponse> CallResponse;
    onTimelineResponse listener;
    onTimelineCategoryResponse listener2;

    public TimelineController(onTimelineResponse listener) {
        this.listener = listener;
    }


    public TimelineController(onTimelineCategoryResponse listener2) { this.listener2=listener2; }

    public void getTimeline()
    {
        service= ServiceGenerator.createService(TimelineAPI.class);
        CallResponse=service.GetTimeline();
        CallResponse.enqueue(new Callback<TimelineResponse>() {
            @Override
            public void onResponse(Call<TimelineResponse> call, Response<TimelineResponse> response) {
                listener.getTimelineResponse(false,response.body(),null);
            }

            @Override
            public void onFailure(Call<TimelineResponse> call, Throwable t) {
                listener.getTimelineResponse(true,null,t);
            }
        });
    }

    public void getTimelineByCategory(String category)
    {
        service=ServiceGenerator.createService(TimelineAPI.class);
        CallResponse=service.GetTimelineByCategory(category);
        CallResponse.enqueue(new Callback<TimelineResponse>() {
            @Override
            public void onResponse(Call<TimelineResponse> call, Response<TimelineResponse> response) {
                listener2.getTimelineByCategoryResponse(false,response.body(),null);
            }

            @Override
            public void onFailure(Call<TimelineResponse> call, Throwable t) {
                listener2.getTimelineByCategoryResponse(true,null,t);
            }
        });
    }

    public interface onTimelineResponse {
        public void getTimelineResponse(boolean error, TimelineResponse response,Throwable t);
    }

    public interface onTimelineCategoryResponse {
        public void getTimelineByCategoryResponse(boolean error, TimelineResponse response,Throwable t);
    }
}
