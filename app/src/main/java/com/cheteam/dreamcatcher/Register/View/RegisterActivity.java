package com.cheteam.dreamcatcher.Register.View;

import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.cheteam.dreamcatcher.Login.View.LoginActivity;
import com.cheteam.dreamcatcher.R;
import com.cheteam.dreamcatcher.Register.Controller.RegisterController;
import com.cheteam.dreamcatcher.Register.Model.RegisterResponse;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Nicolas Juniar on 03/09/2017.
 */

public class RegisterActivity extends AppCompatActivity implements RegisterController.onRegisterResponse{

    @BindView(R.id.title1) TextView title1;
    @BindView(R.id.title2) TextView title2;
    @BindView(R.id.title3) TextView title3;
    @BindView(R.id.return1) TextView return1;
    @BindView(R.id.btnReturn) ImageView btnReturn;
    @BindView(R.id.txtEmail) EditText txtEmail;
    @BindView(R.id.txtPassword) EditText txtPassword;
    @BindView(R.id.txtRepassword) EditText txtRepassword;
    @BindView(R.id.txtName) EditText txtName;
    @BindView(R.id.btnRegister) Button btnRegister;

    RegisterController RC;
    ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_layout);
        ButterKnife.bind(this);
        RC=new RegisterController(this);

        btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ClearError();
                if(CekInput())
                {
                    if(progressDialog==null)
                    {
                        progressDialog=new ProgressDialog(RegisterActivity.this);
                        progressDialog.setMessage("Trying Login....");
                        progressDialog.setIndeterminate(false);
                        progressDialog.setCancelable(false);
                    }
                    RC.Register();
                }
            }
        });

        return1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(RegisterActivity.this,LoginActivity.class));
                finish();
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
        txtEmail.setTypeface(Roboto_Regular);
        txtPassword.setTypeface(Roboto_Regular);
        txtRepassword.setTypeface(Roboto_Regular);
        title1.setTypeface(Lobster_Regular);
        title2.setTypeface(justAnotherHand);
        title3.setTypeface(RockoFLF);
        btnRegister.setTypeface(Lobster_Regular);
        return1.setTypeface(RockoFLF);
        txtName.setTypeface(Roboto_Regular);
    }

    public void ClearError()
    {
        txtEmail.setError(null);
        txtPassword.setError(null);
        txtName.setError(null);
        txtRepassword.setError(null);
    }

    public boolean CekInput() {
        boolean cek = true;

        if (txtEmail.getText().toString().isEmpty()) {
            txtEmail.setError("Email is invalid");
            cek = false;
        } else
        {
            txtEmail.setError(null);
        }

        if (txtPassword.getText().toString().isEmpty()) {
            txtPassword.setError("Incorrect password");
            cek = false;
        } else if(txtPassword.getText().toString().length()<6 || txtPassword.getText().toString().length()>18) {
            txtPassword.setError("Password must be 6-18 character");
            cek=false;
        }else {
            txtPassword.setError(null);
        }

        if(!txtRepassword.getText().toString().equalsIgnoreCase(txtPassword.getText().toString())){
            txtRepassword.setError("Password doesn't match");
            cek=false;
        }else {
            txtRepassword.setError(null);
        }

        return cek;
    }

    @Override
    public void getRegisterResponse(boolean error, RegisterResponse response, Throwable t) {
        if ((progressDialog != null) && progressDialog.isShowing()) {
            progressDialog.dismiss();
        }

        if(!error)
        {
            Toast.makeText(RegisterActivity.this, response.message, Toast.LENGTH_SHORT).show();
            startActivity(new Intent(RegisterActivity.this,LoginActivity.class));
        }
        if(error)
        {
            Toast.makeText(this, t.getMessage(), Toast.LENGTH_SHORT).show();
        }
    }
}
