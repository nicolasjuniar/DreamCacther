package com.cheteam.dreamcatcher.Login.Controller;

import android.util.Log;

import com.cheteam.dreamcatcher.Login.API.LoginAPI;
import com.cheteam.dreamcatcher.Login.Model.InterestRequest;
import com.cheteam.dreamcatcher.Login.Model.InterestResponse;
import com.cheteam.dreamcatcher.Login.Model.LoginRequest;
import com.cheteam.dreamcatcher.Login.Model.LoginResponse;
import com.cheteam.dreamcatcher.Login.View.LoginActivity;
import com.cheteam.dreamcatcher.Login.View.onInterestUpdate;
import com.cheteam.dreamcatcher.ServiceGenerator;

import java.io.IOException;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by Nicolas Juniar on 12/09/2017.
 */

public class LoginController {

    LoginAPI service;
    Call<LoginResponse> CallResponse;
    Call<InterestResponse> CallInterest;
    OnLoginResponse listener;
    onInterestUpdate listener2;

    public LoginController(OnLoginResponse listener) {
        this.listener = listener;
    }

    public LoginController(onInterestUpdate listener2) {
        this.listener2 = listener2;
    }

    public LoginController() {
    }

    public void Login(LoginRequest body)
    {
        service= ServiceGenerator.createService(LoginAPI.class);
        CallResponse=service.Login(body);
        CallResponse.enqueue(new Callback<LoginResponse>() {
            @Override
            public void onResponse(Call<LoginResponse> call, Response<LoginResponse> response) {
                listener.getLoginResponse(false,response,null);
            }

            @Override
            public void onFailure(Call<LoginResponse> call, Throwable t) {
                listener.getLoginResponse(true,null,t);
            }
        });
    }

    public void UpdateInterest(InterestRequest body, String token)
    {
        service=ServiceGenerator.createService(LoginAPI.class);
        CallInterest=service.EditInterest(body,token);
        CallInterest.enqueue(new Callback<InterestResponse>() {
            @Override
            public void onResponse(Call<InterestResponse> call, Response<InterestResponse> response) {
                listener2.InterestUpdateResponse(false,response.body(),null);
            }

            @Override
            public void onFailure(Call<InterestResponse> call, Throwable t) {
                listener2.InterestUpdateResponse(true,null,t);
            }
        });

    }


    public interface OnLoginResponse {
        void getLoginResponse(boolean error,Response<LoginResponse> response,Throwable t);
    }
}
