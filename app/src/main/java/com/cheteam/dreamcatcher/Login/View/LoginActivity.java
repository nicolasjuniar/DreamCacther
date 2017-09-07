package com.cheteam.dreamcatcher.Login.View;

import android.app.ProgressDialog;
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
    TextView title1,title2,no_account,join_later,join_now;
    EditText txtEmail,txtPassword;
    Button btnLogin;
    TextView email,password;

    LoginAPI service;
    Call<LoginResponse> CallLogin;

    ProgressDialog progressDialog;

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
        no_account=(TextView) findViewById(R.id.no_account);
        join_now=(TextView) findViewById(R.id.join_now);
        join_later=(TextView) findViewById(R.id.join_later);
        btnLogin=(Button) findViewById(R.id.btnLogin);

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(progressDialog==null)
                {
                    progressDialog=new ProgressDialog(LoginActivity.this);
                    progressDialog.setMessage("Trying Login....");
                    progressDialog.setIndeterminate(false);
                    progressDialog.setCancelable(false);
                }
                Login(txtEmail.getText().toString());
            }
        });

        join_now.setOnClickListener(new View.OnClickListener() {
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

    @Override
    public void onPause() {
        super.onPause();

        if ((progressDialog != null) && progressDialog.isShowing())
            progressDialog.dismiss();
        progressDialog = null;
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
        no_account.setTypeface(Lobster_Regular);
        join_later.setTypeface(Lobster_Regular);
        join_now.setTypeface(Lobster_Regular);
    }

    public void Login(final String email)
    {
        service= ServiceGenerator.createService(LoginAPI.class);
        CallLogin=service.Login();
        CallLogin.enqueue(new Callback<LoginResponse>() {
            @Override
            public void onResponse(Response<LoginResponse> response) {
                LoginResponse loginResponse=response.body();
                if(loginResponse.email.equalsIgnoreCase(email))
                {
                    if ((progressDialog != null) && progressDialog.isShowing()) {
                        progressDialog.dismiss();
                    }
                    Toast.makeText(LoginActivity.this, loginResponse.message, Toast.LENGTH_SHORT).show();
                    Intent intent=new Intent(LoginActivity.this,TimelineActivity.class);
                    Bundle bundle=new Bundle();
                    bundle.putBoolean("login",true);
                    intent.putExtras(bundle);
                    startActivity(intent);
                }
                else
                {
                    if ((progressDialog != null) && progressDialog.isShowing()) {
                        progressDialog.dismiss();
                    }
                    Toast.makeText(LoginActivity.this, "Password or Email Incorrect", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Throwable t) {
                if ((progressDialog != null) && progressDialog.isShowing()) {
                    progressDialog.dismiss();
                }
                Toast.makeText(LoginActivity.this, t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }
}
