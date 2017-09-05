package com.cheteam.dreamcatcher;

import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;

import com.cheteam.dreamcatcher.Login.Controller.LoginAPI;
import com.cheteam.dreamcatcher.Login.Model.LoginResponse;
import com.cheteam.dreamcatcher.Login.View.LoginActivity;
import com.google.gson.Gson;
import com.squareup.okhttp.ResponseBody;

import retrofit.Call;
import retrofit.Callback;
import retrofit.Response;

/**
 * Created by Nicolas Juniar on 31/08/2017.
 */

public class SplashScreenActivity extends AppCompatActivity {

    TextView title1,title2;

    LoginAPI service;
    Call<LoginResponse> CallBody;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splashscreen_layout);
        title1=(TextView) findViewById(R.id.title1);
        title2=(TextView) findViewById(R.id.title2);
        Typeface Lobster_Regular=Typeface.createFromAsset(getAssets(), "fonts/Lobster-Regular.ttf");
        title1.setTypeface(Lobster_Regular);
        Typeface justAnotherHand=Typeface.createFromAsset(getAssets(), "fonts/JustAnotherHand.ttf");
        title2.setTypeface(justAnotherHand);

        Thread logoTimer = new Thread() {
            public void run() {
                try {
                    sleep(2000);
                } catch (InterruptedException e) {
                    Log.d("Exception", "Exception" + e);
                } finally {
                    startActivity(new Intent(SplashScreenActivity.this,LoginActivity.class));
                }
                finish();
            }
        };
        logoTimer.start();
    }


}
