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

import de.hdodenhof.circleimageview.CircleImageView;

/**
 * Created by Nicolas Juniar on 10/09/2017.
 */

public class EditProfileActivity extends AppCompatActivity {
    TextView bio,location,name;
    EditText txtBio,txtLocation,txtName;
    CircleImageView add_photo_user,add_photo_cover;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_profile_layout);
        bio=(TextView) findViewById(R.id.bio);
        location=(TextView)findViewById(R.id.location);
        name=(TextView) findViewById(R.id.name);
        txtBio=(EditText) findViewById(R.id.txtBio);
        txtLocation=(EditText) findViewById(R.id.txtLocation);
        txtName=(EditText) findViewById(R.id.txtName);
        add_photo_user=(CircleImageView) findViewById(R.id.add_photo_user);
        add_photo_cover=(CircleImageView) findViewById(R.id.add_photo_cover);

        add_photo_user.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(EditProfileActivity.this, "add photo user", Toast.LENGTH_SHORT).show();
            }
        });

        add_photo_cover.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(EditProfileActivity.this, "add photo cover", Toast.LENGTH_SHORT).show();
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
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_edit_profile, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        if(id==R.id.action_done)
        {
            finish();
        }
        return super.onOptionsItemSelected(item);
    }
}
