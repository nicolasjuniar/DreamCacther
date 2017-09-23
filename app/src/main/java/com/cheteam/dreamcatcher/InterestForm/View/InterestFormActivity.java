package com.cheteam.dreamcatcher.InterestForm.View;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Typeface;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.cheteam.dreamcatcher.Helper.PreferenceHelper;
import com.cheteam.dreamcatcher.InterestForm.Adapter.RecycleViewAdapterListInterest;
import com.cheteam.dreamcatcher.InterestForm.Model.ModelInterest;
import com.cheteam.dreamcatcher.Login.View.LoginActivity;
import com.cheteam.dreamcatcher.R;
import com.cheteam.dreamcatcher.Timeline.View.TimelineActivity;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Nicolas Juniar on 08/09/2017.
 */

public class InterestFormActivity extends AppCompatActivity implements RecycleViewAdapterListInterest.onChangeInterest{

    @BindView(R.id.next) RelativeLayout next;
    @BindView(R.id.return1) RelativeLayout return1;
    @BindView(R.id.txtNext) TextView txtNext;
    @BindView(R.id.title1) TextView title1;
    @BindView(R.id.title2) TextView title2;
    @BindView(R.id.txtReturn) TextView txtReturn;
    @BindView(R.id.ok) ImageView ok;
    @BindView(R.id.ListInterest) RecyclerView ListInterest;

    RecycleViewAdapterListInterest adapter;
    ArrayList<ModelInterest> list;
    ArrayList<String> listInterest;
    Boolean check;
    private PreferenceHelper preferences;
    private Boolean exit = false;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_interest_form_layout);

        ButterKnife.bind(this);
        setFont();
        setListInterest();
        preferences=PreferenceHelper.getInstance(getApplicationContext());

        Bundle bundle = getIntent().getExtras();
        check=bundle.getBoolean("login",false);
        listInterest=new ArrayList<>();
        if(check)
        {
            return1.setVisibility(View.GONE);
        }

        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(InterestFormActivity.this,TimelineActivity.class);
                Bundle bundle = new Bundle();
                bundle.putStringArrayList("listinterest",listInterest);
                intent.putExtras(bundle);
                if(check)
                {
                    preferences.putBoolean("session",true);
                    preferences.putBoolean("interest",true);
                    startActivity(intent);
                }
                else if(!check)
                {
                    startActivity(intent);
                }
                finishAffinity();
            }
        });

        return1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(InterestFormActivity.this,LoginActivity.class));
                finish();
            }
        });
    }

    @Override
    public void onBackPressed() {
        if(check)
        {
            if (exit) {
                finish(); // finish activity
            } else {
                Toast.makeText(this, "Press Back again to Exit.",
                        Toast.LENGTH_SHORT).show();
                exit = true;
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        exit = false;
                    }
                }, 3 * 1000);

            }
        }
        else
        {
            super.onBackPressed();
        }

    }

    public void showNextButton(boolean show)
    {
        if(show)
        {
            ok.setVisibility(View.VISIBLE);
            next.setVisibility(View.VISIBLE);
        }
        else
        {
            ok.setVisibility(View.GONE);
            next.setVisibility(View.GONE);
        }
    }

    public void setFont()
    {
        Typeface Lobster_Regular=Typeface.createFromAsset(getAssets(), "fonts/Lobster-Regular.ttf");
        Typeface RockoFLF=Typeface.createFromAsset(getAssets(), "fonts/RockoFLF.ttf");
        Typeface Roboto_Regular=Typeface.createFromAsset(getAssets(), "fonts/Roboto-Regular.ttf");

        txtReturn.setTypeface(RockoFLF);
        txtNext.setTypeface(RockoFLF);
        title1.setTypeface(Lobster_Regular);
        title2.setTypeface(Roboto_Regular);
    }

    public void setListInterest()
    {

        list=new ArrayList<>();
        list.add(new ModelInterest("Finances"));
        list.add(new ModelInterest("Skills"));
        list.add(new ModelInterest("Facilities"));
        list.add(new ModelInterest("Opportunities"));
        list.add(new ModelInterest("Courses"));

        adapter=new RecycleViewAdapterListInterest(list,InterestFormActivity.this,this);
        ListInterest.setAdapter(adapter);
        ListInterest.setLayoutManager(new LinearLayoutManager(InterestFormActivity.this));
    }

    @Override
    public void addInterest(String interest) {
        listInterest.add(interest);
        if(listInterest.size()>=3)
            showNextButton(true);
    }

    @Override
    public void removeInterest(String interest) {
        listInterest.remove(interest);
        if(listInterest.size()<3)
            showNextButton(false);
    }
}
