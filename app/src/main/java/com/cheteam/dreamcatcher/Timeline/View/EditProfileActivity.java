package com.cheteam.dreamcatcher.Timeline.View;

import android.accessibilityservice.AccessibilityService;
import android.app.Activity;
import android.app.usage.UsageEvents;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Resources;
import android.graphics.Typeface;
import android.inputmethodservice.Keyboard;
import android.inputmethodservice.KeyboardView;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.annotation.StringDef;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.Editable;
import android.text.Layout;
import android.text.TextWatcher;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.view.WindowManager;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.cheteam.dreamcatcher.Helper.PreferenceHelper;
import com.cheteam.dreamcatcher.Login.View.LoginActivity;
import com.cheteam.dreamcatcher.R;
import com.cheteam.dreamcatcher.Timeline.Adapter.RecyclerViewAdapterListAvatar;

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
    @BindView(R.id.fullnamecounter) TextView full_name_counter;
    @BindView(R.id.name_max) TextView name_max;
    @BindView(R.id.location_counter) TextView location_counter;
    @BindView(R.id.max_location) TextView max_location;
    @BindView(R.id.bio_counter) TextView bio_counter;
    @BindView(R.id.max_bio)TextView max_bio;
    @BindView(R.id.name_indicator)LinearLayout name_indicator;
    @BindView(R.id.location_indicator) LinearLayout location_indicator;
    @BindView(R.id.bio_indicator) LinearLayout bio_indicator;
    @BindView(R.id.AvatarUser) CircleImageView edit_avatar_user;
    @BindView(R.id.BgProfile) ImageView edit_bg_profile;
    private PreferenceHelper preferences;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_profile_layout);
        ButterKnife.bind(this);
        preferences= PreferenceHelper.getInstance(getApplicationContext());
        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);

        setPickBackgroundButton();
        setPickAvatarButton();
        setFont();
        setCounterIndicator();

        txtName.addTextChangedListener(nametextWatcher);
        txtLocation.addTextChangedListener(locationtextWatcher);
        txtBio.addTextChangedListener(biotextWatcher);

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
    private final TextWatcher nametextWatcher = new TextWatcher() {
        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {

        }

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {
            count = 18;
            if(s.length()> count){
                full_name_counter.setTextColor(getResources().getColor(R.color.Red));
                name_max.setTextColor(getResources().getColor(R.color.Red));
                txtName.setTextColor(getResources().getColor(R.color.Red));
            }else {
                full_name_counter.setTextColor(getResources().getColor(R.color.Teal));
                name_max.setTextColor(getResources().getColor(R.color.Teal));
                txtName.setTextColor(getResources().getColor(R.color.Teal));

            }
            name_indicator.setVisibility(View.VISIBLE);
            location_indicator.setVisibility(View.INVISIBLE);
            bio_indicator.setVisibility(View.INVISIBLE);
        }

        @Override
        public void afterTextChanged(Editable s) {
            full_name_counter.setText(String.valueOf(s.length()));
        }
    };
    private final TextWatcher locationtextWatcher = new TextWatcher() {
        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {

        }

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {
            int max_kar = 18;
            if(s.length()> max_kar){
                location_counter.setTextColor(getResources().getColor(R.color.Red));
                max_location.setTextColor(getResources().getColor(R.color.Red));
                txtLocation.setTextColor(getResources().getColor(R.color.Red));
            }else {
                location_counter.setTextColor(getResources().getColor(R.color.Teal));
                max_location.setTextColor(getResources().getColor(R.color.Teal));
                txtLocation.setTextColor(getResources().getColor(R.color.Teal));

            }
            name_indicator.setVisibility(View.INVISIBLE);
            location_indicator.setVisibility(View.VISIBLE);
            bio_indicator.setVisibility(View.INVISIBLE);
        }

        @Override
        public void afterTextChanged(Editable s) {
            location_counter.setText(String.valueOf(s.length()));

        }
    };

    private final TextWatcher biotextWatcher = new TextWatcher() {
        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {

        }

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {
            int max_kar_bio = 100;
            if(s.length()>max_kar_bio){
                bio_counter.setTextColor(getResources().getColor(R.color.Red));
                max_bio.setTextColor(getResources().getColor(R.color.Red));
                txtBio.setTextColor(getResources().getColor(R.color.Red));
            }else {
                bio_counter.setTextColor(getResources().getColor(R.color.Teal));
                max_bio.setTextColor(getResources().getColor(R.color.Teal));
                txtBio.setTextColor(getResources().getColor(R.color.Teal));
            }
            name_indicator.setVisibility(View.INVISIBLE);
            location_indicator.setVisibility(View.INVISIBLE);
            bio_indicator.setVisibility(View.VISIBLE);
        }

        @Override
        public void afterTextChanged(Editable s) {
            bio_counter.setText(String.valueOf(s.length()));
        }
    };
    public void setCounterIndicator(){
        name_indicator.setVisibility(View.INVISIBLE);
        location_indicator.setVisibility(View.INVISIBLE);
        bio_indicator.setVisibility(View.INVISIBLE);
    }
    public void setPickAvatarButton(){
        add_photo_user.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(EditProfileActivity.this,PickAvatarActivity.class);
                startActivityForResult(intent,1);
//                startActivity(new Intent(EditProfileActivity.this,PickAvatarActivity.class));
            }
        });
        edit_avatar_user.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(EditProfileActivity.this,PickAvatarActivity.class);
                startActivityForResult(intent,1);

//                startActivity(new Intent(EditProfileActivity.this,PickAvatarActivity.class));
            }
        });
    }
    public void setPickBackgroundButton(){
        add_photo_cover.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(EditProfileActivity.this,PickCoverActivity.class));
            }
        });
        edit_bg_profile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(EditProfileActivity.this,PickCoverActivity.class));
            }
        });
    }
    public void onActivityResult(int requestCode, int resultCode, Intent intent){
        super.onActivityResult(requestCode,resultCode,intent);
        if (requestCode == 1){
            if(resultCode == RESULT_OK){
            }
        }
    }
}
