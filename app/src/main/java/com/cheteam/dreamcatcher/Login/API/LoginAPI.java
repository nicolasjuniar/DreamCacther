package com.cheteam.dreamcatcher.Login.API;

import com.cheteam.dreamcatcher.Login.Model.LoginResponse;
import retrofit2.Call;
import retrofit2.http.POST;

/**
 * Created by Nicolas Juniar on 03/09/2017.
 */

public interface LoginAPI {
    @POST("v1/login")
    Call<LoginResponse> Login();
}
