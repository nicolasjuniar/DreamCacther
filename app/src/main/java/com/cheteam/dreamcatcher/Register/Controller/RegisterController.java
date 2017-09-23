package com.cheteam.dreamcatcher.Register.Controller;

import com.cheteam.dreamcatcher.Register.API.RegisterAPI;
import com.cheteam.dreamcatcher.Register.Model.RegisterRequest;
import com.cheteam.dreamcatcher.Register.Model.RegisterResponse;
import com.cheteam.dreamcatcher.ServiceGenerator;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by Nicolas Juniar on 13/09/2017.
 */

public class RegisterController {

    RegisterAPI service;
    Call<RegisterResponse> CallResponse;
    onRegisterResponse listener;

    public RegisterController(onRegisterResponse listener) {
        this.listener = listener;
    }

    public void Register(RegisterRequest body)
    {
        service= ServiceGenerator.createService(RegisterAPI.class);
        CallResponse=service.Register(body);
        CallResponse.enqueue(new Callback<RegisterResponse>() {
            @Override
            public void onResponse(Call<RegisterResponse> call, Response<RegisterResponse> response) {
                listener.getRegisterResponse(false,response.body(),null);
            }

            @Override
            public void onFailure(Call<RegisterResponse> call, Throwable t) {
                listener.getRegisterResponse(true,null,t);
            }
        });
    }

    public interface onRegisterResponse {
        public void getRegisterResponse(boolean error, RegisterResponse response, Throwable t);
    }
}
