package com.cheteam.dreamcatcher;

import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.TextView;

import com.cheteam.dreamcatcher.Helper.PreferenceHelper;
import com.cheteam.dreamcatcher.Login.View.LoginActivity;
import com.cheteam.dreamcatcher.Timeline.View.TimelineActivity;

import butterknife.BindView;
import butterknife.ButterKnife;


/**
 * Created by Nicolas Juniar on 31/08/2017.
 */

public class SplashScreenActivity extends AppCompatActivity{

    @BindView(R.id.title1) TextView title1;
    @BindView(R.id.title2) TextView title2;

    private PreferenceHelper preferences;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splashscreen_layout);
        preferences = PreferenceHelper.getInstance(getApplicationContext());
        ButterKnife.bind(this);
        setFont();

        Thread logoTimer = new Thread() {
            public void run() {
                try {
                    sleep(2000);
                } catch (InterruptedException e) {
                    Log.d("Exception", "Exception" + e);
                } finally {
                    loadPreferences();
                }
                finish();
            }
        };
        logoTimer.start();
    }

    public void setFont()
    {
        Typeface Lobster_Regular=Typeface.createFromAsset(getAssets(), "fonts/Lobster-Regular.ttf");
        title1.setTypeface(Lobster_Regular);
        Typeface justAnotherHand=Typeface.createFromAsset(getAssets(), "fonts/JustAnotherHand.ttf");
        title2.setTypeface(justAnotherHand);
    }

    public void loadPreferences()
    {
        if(preferences.getBoolean("session",false))
        {
            startActivity(new Intent(SplashScreenActivity.this, TimelineActivity.class));
        }
        else
        {
            startActivity(new Intent(SplashScreenActivity.this, LoginActivity.class));
        }
    }
}
