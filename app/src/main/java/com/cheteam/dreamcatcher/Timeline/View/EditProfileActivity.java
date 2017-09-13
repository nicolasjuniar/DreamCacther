package com.cheteam.dreamcatcher.Timeline.View;

import android.app.Activity;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

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

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_profile_layout);
        ButterKnife.bind(this);

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

        if(id==R.id.action_done)
        {
            finish();
        }
        return super.onOptionsItemSelected(item);
    }
}
