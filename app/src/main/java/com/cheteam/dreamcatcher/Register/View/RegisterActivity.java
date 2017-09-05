package com.cheteam.dreamcatcher.Register.View;

import android.content.Intent;
import android.graphics.PorterDuff;
import android.graphics.Typeface;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.design.widget.TextInputLayout;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.util.TypedValue;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.cheteam.dreamcatcher.Login.View.LoginActivity;
import com.cheteam.dreamcatcher.R;
import com.cheteam.dreamcatcher.Register.Controller.RegisterAPI;
import com.cheteam.dreamcatcher.Register.Model.RegisterResponse;
import com.cheteam.dreamcatcher.ServiceGenerator;
import com.google.gson.Gson;
import com.squareup.okhttp.ResponseBody;

import retrofit.Call;
import retrofit.Callback;
import retrofit.Response;

/**
 * Created by Nicolas Juniar on 03/09/2017.
 */

public class RegisterActivity extends AppCompatActivity {
    TextView title1,title2,title3,return1;
    ImageView btnReturn;
    EditText txtEmail,txtPassword,txtRepassword;
    Button btnRegister;
    TextView email,password,repassword;

    RegisterAPI service;
    Call<RegisterResponse> CallRegister;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_layout);
        email=(TextView) findViewById(R.id.email);
        password=(TextView) findViewById(R.id.password);
        txtEmail=(EditText) findViewById(R.id.txtEmail);
        return1=(TextView) findViewById(R.id.return1);
        txtPassword=(EditText) findViewById(R.id.txtPassword);
        repassword=(TextView) findViewById(R.id.repassword);
        txtRepassword=(EditText) findViewById(R.id.txtRepassword);
        title1=(TextView) findViewById(R.id.title1);
        title2=(TextView) findViewById(R.id.title2);
        title3=(TextView) findViewById(R.id.title3);
        btnRegister=(Button) findViewById(R.id.btnRegister);
        btnReturn=(ImageView) findViewById(R.id.btnReturn);

        Drawable icon = ContextCompat.getDrawable(this, R.drawable.icon_return).mutate();
        TypedValue typedValue = new TypedValue();
        this.getTheme().resolveAttribute(R.color.duckEggBlue, typedValue, true);
        icon.setColorFilter(typedValue.data, PorterDuff.Mode.SRC_ATOP);

        btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(!txtPassword.getText().toString().equals(txtRepassword.getText().toString()))
                {
                    Toast.makeText(RegisterActivity.this, "Password and confirm password not same", Toast.LENGTH_SHORT).show();
                }
                else
                {
                    Register(txtEmail.getText().toString(),txtPassword.getText().toString());
                }
            }
        });

        btnReturn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(RegisterActivity.this,LoginActivity.class));
                finish();
            }
        });

        setFont();
    }

    public void setFont()
    {
        Typeface justAnotherHand=Typeface.createFromAsset(getAssets(), "fonts/JustAnotherHand.ttf");
        Typeface Lobster_Regular=Typeface.createFromAsset(getAssets(), "fonts/Lobster-Regular.ttf");
        Typeface RockoFLF=Typeface.createFromAsset(getAssets(), "fonts/RockoFLF.ttf");
        Typeface Roboto_Regular=Typeface.createFromAsset(getAssets(), "fonts/Roboto-Regular.ttf");
        email.setTypeface(Roboto_Regular);
        password.setTypeface(Roboto_Regular);
        txtEmail.setTypeface(Roboto_Regular);
        txtPassword.setTypeface(Roboto_Regular);
        repassword.setTypeface(Roboto_Regular);
        txtRepassword.setTypeface(Roboto_Regular);
        title1.setTypeface(Lobster_Regular);
        title2.setTypeface(justAnotherHand);
        title3.setTypeface(RockoFLF);
        btnRegister.setTypeface(Lobster_Regular);
        return1.setTypeface(RockoFLF);
    }

    public void Register(String email,String password)
    {
        service= ServiceGenerator.createService(RegisterAPI.class);
        CallRegister=service.Register();
        CallRegister.enqueue(new Callback<RegisterResponse>() {
            @Override
            public void onResponse(Response<RegisterResponse> response) {
                Toast.makeText(RegisterActivity.this, response.body().token, Toast.LENGTH_SHORT).show();
                startActivity(new Intent(RegisterActivity.this,LoginActivity.class));
            }

            @Override
            public void onFailure(Throwable t) {

            }
        });
    }
}
