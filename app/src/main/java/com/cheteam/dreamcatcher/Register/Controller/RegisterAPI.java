package com.cheteam.dreamcatcher.Register.Controller;

import com.cheteam.dreamcatcher.Register.Model.RegisterResponse;
import com.squareup.okhttp.ResponseBody;

import retrofit.Call;
import retrofit.http.Field;
import retrofit.http.FormUrlEncoded;
import retrofit.http.POST;

/**
 * Created by Nicolas Juniar on 03/09/2017.
 */

public interface RegisterAPI {
    @POST("v1/register")
    Call<RegisterResponse> Register();
}
