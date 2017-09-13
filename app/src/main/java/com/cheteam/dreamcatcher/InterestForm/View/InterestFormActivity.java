package com.cheteam.dreamcatcher.InterestForm.View;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

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

public class InterestFormActivity extends AppCompatActivity {

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

        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(check)
                {
                    preferences.putBoolean("session",true);
                    preferences.putBoolean("interest",true);
                    startActivity(new Intent(InterestFormActivity.this,TimelineActivity.class));
                }
                else if(!check)
                {
                    startActivity(new Intent(InterestFormActivity.this,TimelineActivity.class));
                }
                LoginActivity.LA.finish();
                finish();
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
        listInterest=new ArrayList<>();
        list=new ArrayList<>();
        list.add(new ModelInterest("Finances"));
        list.add(new ModelInterest("Skills"));
        list.add(new ModelInterest("Facilities"));
        list.add(new ModelInterest("Opportunities"));
        list.add(new ModelInterest("Courses"));

        adapter=new RecycleViewAdapterListInterest(list,InterestFormActivity.this,listInterest,next,ok);
        ListInterest.setAdapter(adapter);
        ListInterest.setLayoutManager(new LinearLayoutManager(InterestFormActivity.this));
    }
}
