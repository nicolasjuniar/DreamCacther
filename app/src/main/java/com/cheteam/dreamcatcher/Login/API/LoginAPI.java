package com.cheteam.dreamcatcher.Login.API;

import com.cheteam.dreamcatcher.Login.Model.InterestRequest;
import com.cheteam.dreamcatcher.Login.Model.InterestResponse;
import com.cheteam.dreamcatcher.Login.Model.LoginRequest;
import com.cheteam.dreamcatcher.Login.Model.LoginResponse;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Header;
import retrofit2.http.POST;
import retrofit2.http.PUT;

/**
 * Created by Nicolas Juniar on 03/09/2017.
 */

public interface LoginAPI {
    @POST("v1/login/")
    Call<LoginResponse> Login(@Body LoginRequest body);

    @PUT("v1/owncategory")
    Call<InterestResponse> EditInterest(@Body InterestRequest body,
                                        @Header("Authorization") String token);
}
