package com.cheteam.dreamcatcher.Login.Controller;

import com.cheteam.dreamcatcher.Login.Model.LoginResponse;
import com.squareup.okhttp.ResponseBody;

import retrofit.Call;
import retrofit.http.Field;
import retrofit.http.FormUrlEncoded;
import retrofit.http.POST;

/**
 * Created by Nicolas Juniar on 03/09/2017.
 */

public interface LoginAPI {
    @POST("v1/login")
    Call<LoginResponse> Login();
}
