package com.cheteam.dreamcatcher.Login.View;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Typeface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.cheteam.dreamcatcher.InterestForm.View.InterestFormActivity;
import com.cheteam.dreamcatcher.Login.API.LoginAPI;
import com.cheteam.dreamcatcher.Login.Controller.LoginController;
import com.cheteam.dreamcatcher.Login.Model.LoginResponse;
import com.cheteam.dreamcatcher.R;
import com.cheteam.dreamcatcher.Register.View.RegisterActivity;
import com.cheteam.dreamcatcher.ServiceGenerator;
import com.cheteam.dreamcatcher.Timeline.View.TimelineActivity;

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
    public static Activity LA;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_layout);
        ButterKnife.bind(this);
        LA=this;
        LC=new LoginController(this);

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
                LC.Login();
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
    public void getLoginResponse(boolean error, LoginResponse loginResponse, Throwable t) {
        if(!error)
        {
            LoginResponse response=loginResponse;
            if ((progressDialog != null) && progressDialog.isShowing()) {
                progressDialog.dismiss();
            }
            Toast.makeText(LoginActivity.this, loginResponse.message, Toast.LENGTH_SHORT).show();
            Boolean interest=LoginActivity.this.getSharedPreferences("MyShared", Activity.MODE_PRIVATE).getBoolean("interest",false);
            if(!interest)
            {
                Intent intent=new Intent(LoginActivity.this,InterestFormActivity.class);
                Bundle bundle=new Bundle();
                bundle.putBoolean("login",true);
                intent.putExtras(bundle);
                startActivity(intent);
            }
            else if(interest)
            {
                SharedPreferences sp=LoginActivity.this.getSharedPreferences("MyShared", Activity.MODE_PRIVATE);
                SharedPreferences.Editor editor=sp.edit();
                editor.putBoolean("session",true);
                editor.apply();
                startActivity(new Intent(LoginActivity.this,TimelineActivity.class));
                finish();
            }
        }

        if(error)
        {
            if ((progressDialog != null) && progressDialog.isShowing()) {
                progressDialog.dismiss();
            }
            Toast.makeText(LoginActivity.this, t.getMessage(), Toast.LENGTH_SHORT).show();
        }
    }
}
