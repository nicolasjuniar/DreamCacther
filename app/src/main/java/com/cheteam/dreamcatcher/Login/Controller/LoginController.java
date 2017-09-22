package com.cheteam.dreamcatcher.Login.Controller;

import com.cheteam.dreamcatcher.Login.API.LoginAPI;
import com.cheteam.dreamcatcher.Login.Model.LoginRequest;
import com.cheteam.dreamcatcher.Login.Model.LoginResponse;
import com.cheteam.dreamcatcher.Login.View.LoginActivity;
import com.cheteam.dreamcatcher.ServiceGenerator;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by Nicolas Juniar on 12/09/2017.
 */

public class LoginController {

    LoginAPI service;
    Call<LoginResponse> CallResponse;
    OnLoginResponse listener;

    public LoginController(OnLoginResponse listener) {
        this.listener = listener;
    }

    public void Login(LoginRequest body)
    {
        service= ServiceGenerator.createService(LoginAPI.class);
        CallResponse=service.Login(body);
        CallResponse.enqueue(new Callback<LoginResponse>() {
            @Override
            public void onResponse(Call<LoginResponse> call, Response<LoginResponse> response) {
                listener.getLoginResponse(false,response.body(),null);
            }

            @Override
            public void onFailure(Call<LoginResponse> call, Throwable t) {
                listener.getLoginResponse(true,null,t);
            }
        });
    }


    public interface OnLoginResponse {
        void getLoginResponse(boolean error,LoginResponse loginResponse,Throwable t);
    }
}
