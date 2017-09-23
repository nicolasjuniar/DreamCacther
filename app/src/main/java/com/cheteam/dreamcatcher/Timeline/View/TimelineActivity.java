package com.cheteam.dreamcatcher.Timeline.View;

import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.os.Handler;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.cheteam.dreamcatcher.AddPost.View.AddPostActivity;
import com.cheteam.dreamcatcher.Helper.PreferenceHelper;
import com.cheteam.dreamcatcher.Login.View.LoginActivity;
import com.cheteam.dreamcatcher.Timeline.Fragment.FragmentFeedsCategory;
import com.cheteam.dreamcatcher.Timeline.Fragment.FragmentProfile;
import com.cheteam.dreamcatcher.Timeline.Fragment.FragmentTimeline;
import com.cheteam.dreamcatcher.R;
import com.cheteam.dreamcatcher.ViewPagerAdapter;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;


/**
 * Created by Nicolas Juniar on 31/08/2017.
 */

public class TimelineActivity extends AppCompatActivity{

    MenuItem btnLogout,btnEditProfile;

    @BindView(R.id.viewpager) ViewPager viewPager;
    @BindView(R.id.tabs) TabLayout tabLayout;
    @BindView(R.id.fab) FloatingActionButton fab;

    private Boolean exit = false;

    boolean check=false;
    private PreferenceHelper preferences;
    ArrayList<String> listinterest;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_timeline_layout);

        ButterKnife.bind(this);
        preferences=PreferenceHelper.getInstance(getApplicationContext());

        Intent intent = getIntent();
        listinterest=intent.getStringArrayListExtra("listinterest");
        if(listinterest==null||listinterest.isEmpty())
        {
            listinterest=new ArrayList<>();
            listinterest.add("Finances");
            listinterest.add("Skills");
            listinterest.add("Facilities");
        }

        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(TimelineActivity.this, AddPostActivity.class));
            }
        });

        check=preferences.getBoolean("session",false);

        setupViewPager(viewPager);
        tabLayout.setupWithViewPager(viewPager);

        tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener(){
            @Override
            public void onTabSelected(TabLayout.Tab tab){
                int position = tab.getPosition();
                if(!check)
                {
                    setTab("not_login");
                }
                if(check)
                {
                    if(position==0 || position==1)
                    {
                        setTab("login");
                    }
                    else
                    {
                        setTab("login_tab_profil");
                    }
                }
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });
        setFont();
    }

    public void setTab(String status)
    {
        if(status.equalsIgnoreCase("not_login"))
        {
            fab.setVisibility(View.GONE);
            btnLogout.setVisible(false);
            btnEditProfile.setVisible(false);
        }
        if(status.equalsIgnoreCase("login"))
        {
            fab.setVisibility(View.VISIBLE);
            btnLogout.setVisible(false);
            btnEditProfile.setVisible(false);
        }
        if(status.equalsIgnoreCase("login_tab_profil"))
        {
            fab.setVisibility(View.VISIBLE);
            btnLogout.setVisible(true);
            btnEditProfile.setVisible(true);
        }


    }

    public void setFont()
    {
        Typeface Roboto_Regular=Typeface.createFromAsset(getAssets(), "fonts/Roboto-Regular.ttf");

        ViewGroup vg = (ViewGroup) tabLayout.getChildAt(0);
        int tabsCount = vg.getChildCount();
        for (int j = 0; j < tabsCount; j++) {
            ViewGroup vgTab = (ViewGroup) vg.getChildAt(j);
            int tabChildsCount = vgTab.getChildCount();
            for (int i = 0; i < tabChildsCount; i++) {
                View tabViewChild = vgTab.getChildAt(i);
                if (tabViewChild instanceof TextView) {
                    ((TextView) tabViewChild).setTypeface(Roboto_Regular);
                }
            }
        }
    }

    private void setupViewPager(ViewPager viewPager) {
        ViewPagerAdapter adapter = new ViewPagerAdapter(getSupportFragmentManager());
        FragmentTimeline fragmentTimeline = new FragmentTimeline();
        Bundle bundle = new Bundle();
        fragmentTimeline.setArguments(bundle);
        bundle.putStringArrayList("listinterest",listinterest);
        adapter.addFragment(fragmentTimeline, "Home");
        adapter.addFragment(new FragmentFeedsCategory(), "Categories");
        adapter.addFragment(new FragmentProfile(), "Profile");
        viewPager.setAdapter(adapter);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        btnEditProfile=menu.findItem(R.id.action_editprofile);
        btnLogout=menu.findItem(R.id.action_logout);

        if(!check)
        {
            setTab("not_login");
        }
        if(check)
        {
            setTab("login");
        }
        return true;
    }

    @Override
    public void onBackPressed() {
        if (exit) {
            finish();
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

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if(id==R.id.action_logout)
        {
            new AlertDialog.Builder(this)
                    .setTitle("Logout")
                    .setMessage("Are you sure wanna logout?")
                    .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int which) {
                            preferences.putBoolean("session",false);
                            preferences.putString("token","");
                            preferences.putString("profile","");
                            startActivity(new Intent(TimelineActivity.this,LoginActivity.class));
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
        }
        if (id==R.id.action_editprofile)
        {
            startActivity(new Intent(TimelineActivity.this,EditProfileActivity.class));
        }

        return super.onOptionsItemSelected(item);
    }

}