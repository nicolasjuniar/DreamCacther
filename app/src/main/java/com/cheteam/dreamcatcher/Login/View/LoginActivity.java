package com.cheteam.dreamcatcher.Login.View;

import android.content.Intent;
import android.graphics.Typeface;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.cheteam.dreamcatcher.Login.Controller.LoginAPI;
import com.cheteam.dreamcatcher.Login.Model.LoginResponse;
import com.cheteam.dreamcatcher.R;
import com.cheteam.dreamcatcher.Register.View.RegisterActivity;
import com.cheteam.dreamcatcher.ServiceGenerator;
import com.cheteam.dreamcatcher.Timeline.View.TimelineActivity;
import com.google.gson.Gson;
import com.squareup.okhttp.ResponseBody;

import retrofit.Call;
import retrofit.Callback;
import retrofit.Response;

public class LoginActivity extends AppCompatActivity {
    TextView title1,title2,btnRegister,join_later;
    EditText txtEmail,txtPassword;
    Button btnLogin;
    TextView email,password;

    LoginAPI service;
    Call<LoginResponse> CallLogin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_layout);
        email=(TextView) findViewById(R.id.email);
        password=(TextView) findViewById(R.id.password);
        txtEmail=(EditText) findViewById(R.id.txtEmail);
        txtPassword=(EditText) findViewById(R.id.txtPassword);
        title1=(TextView) findViewById(R.id.title1);
        title2=(TextView) findViewById(R.id.title2);
        btnRegister=(TextView) findViewById(R.id.btnRegister);
        join_later=(TextView) findViewById(R.id.join_later);
        btnLogin=(Button) findViewById(R.id.btnLogin);

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Login(txtEmail.getText().toString(),txtPassword.getText().toString());
            }
        });

        btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(LoginActivity.this,RegisterActivity.class));
//                finish();
            }
        });

        join_later.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(LoginActivity.this,TimelineActivity.class);
                Bundle bundle=new Bundle();
                bundle.putBoolean("login",false);
                intent.putExtras(bundle);
                startActivity(intent);
//                finish();
            }
        });

        setFont();
    }

    public void setFont()
    {
        Typeface justAnotherHand=Typeface.createFromAsset(getAssets(), "fonts/JustAnotherHand.ttf");
        Typeface Lobster_Regular=Typeface.createFromAsset(getAssets(), "fonts/Lobster-Regular.ttf");
        Typeface Roboto_Regular=Typeface.createFromAsset(getAssets(), "fonts/Roboto-Regular.ttf");
        email.setTypeface(Roboto_Regular);
        password.setTypeface(Roboto_Regular);
        txtEmail.setTypeface(Roboto_Regular);
        txtPassword.setTypeface(Roboto_Regular);
        title1.setTypeface(Lobster_Regular);
        title2.setTypeface(justAnotherHand);
        btnLogin.setTypeface(Lobster_Regular);
        btnRegister.setTypeface(Lobster_Regular);
        join_later.setTypeface(Lobster_Regular);
    }

    public void Login(String email,String password)
    {
        service= ServiceGenerator.createService(LoginAPI.class);
        CallLogin=service.Login();
        CallLogin.enqueue(new Callback<LoginResponse>() {
            @Override
            public void onResponse(Response<LoginResponse> response) {
                Toast.makeText(LoginActivity.this, response.body().token, Toast.LENGTH_SHORT).show();
                Intent intent=new Intent(LoginActivity.this,TimelineActivity.class);
                Bundle bundle=new Bundle();
                bundle.putBoolean("login",true);
                intent.putExtras(bundle);
                startActivity(intent);
            }

            @Override
            public void onFailure(Throwable t) {

            }
        });
    }
}
