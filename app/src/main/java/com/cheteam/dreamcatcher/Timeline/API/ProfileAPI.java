package com.cheteam.dreamcatcher.Timeline.API;

import com.cheteam.dreamcatcher.Timeline.Model.ProfileResponse;

import retrofit2.Call;
import retrofit2.http.GET;


/**
 * Created by Nicolas Juniar on 10/09/2017.
 */

public interface ProfileAPI {
    @GET("v1/profile")
    Call<ProfileResponse> getProfile();
}
