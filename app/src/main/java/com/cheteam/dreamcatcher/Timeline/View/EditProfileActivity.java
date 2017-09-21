package com.cheteam.dreamcatcher.Timeline.View;

import android.app.Activity;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.cheteam.dreamcatcher.Helper.PreferenceHelper;
import com.cheteam.dreamcatcher.Login.View.LoginActivity;
import com.cheteam.dreamcatcher.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import de.hdodenhof.circleimageview.CircleImageView;

/**
 * Created by Nicolas Juniar on 10/09/2017.
 */

public class EditProfileActivity extends AppCompatActivity {
    @BindView(R.id.bio) TextView bio;
    @BindView(R.id.location) TextView location;
    @BindView(R.id.name) TextView name;
    @BindView(R.id.txtBio) EditText txtBio;
    @BindView(R.id.txtLocation) EditText txtLocation;
    @BindView(R.id.txtName) EditText txtName;
    @BindView(R.id.add_photo_user) CircleImageView add_photo_user;
    @BindView(R.id.add_photo_cover) CircleImageView add_photo_cover;
    private PreferenceHelper preferences;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_profile_layout);
        ButterKnife.bind(this);
        preferences= PreferenceHelper.getInstance(getApplicationContext());
        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);

        add_photo_user.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(EditProfileActivity.this,PickAvatarActivity.class));
            }
        });

        add_photo_cover.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(EditProfileActivity.this,PickCoverActivity.class));
            }
        });
        setFont();
    }

    public void setFont()
    {
        Typeface Roboto_Regular=Typeface.createFromAsset(getAssets(), "fonts/Roboto-Regular.ttf");
        bio.setTypeface(Roboto_Regular);
        location.setTypeface(Roboto_Regular);
        name.setTypeface(Roboto_Regular);
        txtBio.setTypeface(Roboto_Regular);
        txtLocation.setTypeface(Roboto_Regular);
        txtName.setTypeface(Roboto_Regular);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_edit_profile, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        switch (id){
            case android.R.id.home: onBackPressed();
                break;
            case R.id.action_logout:
                new AlertDialog.Builder(this)
                        .setTitle("Logout")
                        .setMessage("Are you sure wanna logout?")
                        .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int which) {
                                preferences.putBoolean("session",false);
                                startActivity(new Intent(EditProfileActivity.this,LoginActivity.class));
                                finish();
                            }
                        })
                        .setNegativeButton(android.R.string.no, new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {

                            }
                        })
                        .setIcon(android.R.drawable.ic_dialog_alert)
                        .show();
                break;

        }

        return super.onOptionsItemSelected(item);
    }

}
