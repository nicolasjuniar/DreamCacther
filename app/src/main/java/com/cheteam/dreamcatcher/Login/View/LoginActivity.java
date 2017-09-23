package com.cheteam.dreamcatcher.Login.View;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Typeface;
import android.support.constraint.ConstraintLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.cheteam.dreamcatcher.Helper.PreferenceHelper;
import com.cheteam.dreamcatcher.InterestForm.View.InterestFormActivity;
import com.cheteam.dreamcatcher.Login.API.LoginAPI;
import com.cheteam.dreamcatcher.Login.Controller.LoginController;
import com.cheteam.dreamcatcher.Login.Model.LoginRequest;
import com.cheteam.dreamcatcher.Login.Model.LoginResponse;
import com.cheteam.dreamcatcher.NetworkUtils;
import com.cheteam.dreamcatcher.R;
import com.cheteam.dreamcatcher.Register.View.RegisterActivity;
import com.cheteam.dreamcatcher.ServiceGenerator;
import com.cheteam.dreamcatcher.Timeline.View.TimelineActivity;
import com.google.gson.Gson;

import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class LoginActivity extends AppCompatActivity implements LoginController.OnLoginResponse {

    @BindView(R.id.title1) TextView title1;
    @BindView(R.id.title2) TextView title2;
    @BindView(R.id.no_account) TextView no_account;
    @BindView(R.id.join_later) TextView join_later;
    @BindView(R.id.join_now) TextView join_now;
    @BindView(R.id.txtEmail) EditText txtEmail;
    @BindView(R.id.txtPassword) EditText txtPassword;
    @BindView(R.id.btnLogin) Button btnLogin;

    LoginController LC;

    ProgressDialog progressDialog;
    PreferenceHelper preferences;

    NetworkUtils network;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_layout);
        ButterKnife.bind(this);
        LC=new LoginController(this);

        network=new NetworkUtils(LoginActivity.this);

        (btnLogin.getParent()).requestLayout();
        btnLogin.bringToFront();

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ClearError();
                if(CekInput())
                {
                    if(network.isConnected())
                    {
                        if(progressDialog==null)
                        {
                            progressDialog=new ProgressDialog(LoginActivity.this);
                            progressDialog.setMessage("Trying Login....");
                            progressDialog.setIndeterminate(false);
                            progressDialog.setCancelable(false);
                        }
                        progressDialog.show();
                        String email=txtEmail.getText().toString();
                        String password=txtPassword.getText().toString();
                        LC.Login(new LoginRequest(email,password));
                    }
                    else
                    {
                        Toast.makeText(LoginActivity.this, "phone is not connected to internet", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });

        join_now.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(LoginActivity.this,RegisterActivity.class));
            }
        });

        join_later.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(LoginActivity.this,InterestFormActivity.class);
                Bundle bundle=new Bundle();
                bundle.putBoolean("login",false);
                intent.putExtras(bundle);
                startActivity(intent);
            }
        });

        preferences=PreferenceHelper.getInstance(getApplicationContext());

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
        txtEmail.setTypeface(Roboto_Regular);
        txtPassword.setTypeface(Roboto_Regular);
        title1.setTypeface(Lobster_Regular);
        title2.setTypeface(justAnotherHand);
        btnLogin.setTypeface(Lobster_Regular);
        no_account.setTypeface(Lobster_Regular);
        join_later.setTypeface(Lobster_Regular);
        join_now.setTypeface(Lobster_Regular);
    }

    @Override
    public void getLoginResponse(boolean error, Response<LoginResponse> response , Throwable t) {
        if ((progressDialog != null) && progressDialog.isShowing()) {
            progressDialog.dismiss();
        }
        if(!error)
        {
            if(response.code()==400)
            {
                Toast.makeText(this, "Email is not registeed", Toast.LENGTH_SHORT).show();
            }
            else
            {
                LoginResponse loginResponse=response.body();

                if(response.body().success)
                {
                    Toast.makeText(this, "Login Successful", Toast.LENGTH_SHORT).show();
                    if(loginResponse.categories==null)
                    {
                        Intent intent=new Intent(LoginActivity.this,InterestFormActivity.class);
                        Bundle bundle=new Bundle();
                        bundle.putBoolean("login",true);
                        preferences.putString("token",loginResponse.token);
                        savePreferences(response.body());
                        intent.putExtras(bundle);
                        startActivity(intent);
                    }
                    else
                    {
                        savePreferences(response.body());
                        preferences.putBoolean("session",true);
                        startActivity(new Intent(LoginActivity.this,TimelineActivity.class));
                    }
                    finish();
                }
            }

        }

        if(error)
        {
            Toast.makeText(LoginActivity.this, t.getMessage(), Toast.LENGTH_SHORT).show();
        }
    }

    public void savePreferences(LoginResponse response)
    {
        preferences.putString("profile", new Gson().toJson(response));
    }

    public void ClearError()
    {
        txtEmail.setError(null);
        txtPassword.setError(null);
    }

    public boolean CekInput() {
        boolean cek = true;

        String regex = "^([_a-zA-Z0-9-]+(\\.[_a-zA-Z0-9-]+)*@[a-zA-Z0-9-]+(\\.[a-zA-Z0-9-]+)*(\\.[a-zA-Z]{1,6}))?$";

        if (txtEmail.getText().toString().isEmpty()) {
            txtEmail.setError("Email can't be empty");
            cek = false;
        } else if(!txtEmail.getText().toString().matches(regex))
        {
            txtEmail.setError("Email is invalid");
            cek= false;
        } else
        {
            txtEmail.setError(null);
        }

        if (txtPassword.getText().toString().isEmpty()) {
            txtPassword.setError("Incorrect password");
            cek = false;
        } else
        {
            txtPassword.setError(null);
        }

        return cek;
    }
}
