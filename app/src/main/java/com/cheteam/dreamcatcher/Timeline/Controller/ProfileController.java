package com.cheteam.dreamcatcher.Timeline.Controller;

import com.cheteam.dreamcatcher.ServiceGenerator;
import com.cheteam.dreamcatcher.Timeline.API.ProfileAPI;
import com.cheteam.dreamcatcher.Timeline.Model.ProfileResponse;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by Nicolas Juniar on 13/09/2017.
 */

public class ProfileController {
    ProfileAPI service;
    Call<ProfileResponse> CallProfileResponse;
    onProfileResponse listener;

    public ProfileController(onProfileResponse listener) {
        this.listener = listener;
    }

    public void GetProfile()
    {
        service= ServiceGenerator.createService(ProfileAPI.class);
        CallProfileResponse=service.GetProfile();
        CallProfileResponse.enqueue(new Callback<ProfileResponse>() {
            @Override
            public void onResponse(Call<ProfileResponse> call, Response<ProfileResponse> response) {
                listener.getProfileResponse(false,response.body(),null);
            }

            @Override
            public void onFailure(Call<ProfileResponse> call, Throwable t) {
                listener.getProfileResponse(true,null,t);
            }
        });
    }

    public interface onProfileResponse {
        public void getProfileResponse(boolean error,ProfileResponse response,Throwable t);
    }
}
