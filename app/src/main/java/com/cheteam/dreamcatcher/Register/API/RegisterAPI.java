package com.cheteam.dreamcatcher.Register.API;

import com.cheteam.dreamcatcher.Register.Model.RegisterRequest;
import com.cheteam.dreamcatcher.Register.Model.RegisterResponse;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

/**
 * Created by Nicolas Juniar on 03/09/2017.
 */

public interface RegisterAPI {
    @POST("v1/register/")
    Call<RegisterResponse> Register(@Body RegisterRequest body);
}
