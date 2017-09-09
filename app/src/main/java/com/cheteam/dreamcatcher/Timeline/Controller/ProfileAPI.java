package com.cheteam.dreamcatcher.Timeline.Controller;

import com.cheteam.dreamcatcher.Timeline.Model.ModelUser;

import retrofit.Call;
import retrofit.http.GET;

/**
 * Created by Nicolas Juniar on 10/09/2017.
 */

public interface ProfileAPI {
    @GET("v1/profile")
    Call<ModelUser> GetProfile();
}
