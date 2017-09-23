package com.cheteam.dreamcatcher.Timeline.Fragment;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.cheteam.dreamcatcher.Helper.PreferenceHelper;
import com.cheteam.dreamcatcher.Login.View.LoginActivity;
import com.cheteam.dreamcatcher.R;
import com.cheteam.dreamcatcher.ServiceGenerator;
import com.cheteam.dreamcatcher.Timeline.Adapter.RecyclerViewAdapterMypost;
import com.cheteam.dreamcatcher.Timeline.API.ProfileAPI;
import com.cheteam.dreamcatcher.Timeline.Controller.ProfileController;
import com.cheteam.dreamcatcher.Timeline.Model.ProfileResponse;
import com.cheteam.dreamcatcher.ViewPagerAdapter;

import butterknife.BindView;
import butterknife.ButterKnife;
import de.hdodenhof.circleimageview.CircleImageView;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by Nicolas Juniar on 09/09/2017.
 */

public class FragmentProfile extends Fragment implements ProfileController.onProfileResponse{

    @BindView(R.id.txtLogin) TextView txtLogin;
    @BindView(R.id.LayoutProfile) RelativeLayout LayoutProfile;
    @BindView(R.id.BgProfile) ImageView BgProfile;
    @BindView(R.id.txtJumlahPost) TextView txtJumlahPost;
    @BindView(R.id.Username) TextView Username;
    @BindView(R.id.Location) TextView Location;
    @BindView(R.id.UserBio) TextView UserBio;
    @BindView(R.id.tabs) TabLayout tabLayout;
    @BindView(R.id.viewpager) ViewPager viewPager;
    @BindView(R.id.AvatarUser) CircleImageView AvatarUser;

    RecyclerViewAdapterMypost adapter;
    private PreferenceHelper preferences;
    ProfileController PC;

    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        final View view = inflater.inflate(R.layout.fragment_profile_layout,
                container, false);

        ButterKnife.bind(this,view);
        setupViewPager(viewPager);
        tabLayout.setupWithViewPager(viewPager);
        setFont();
        preferences=PreferenceHelper.getInstance(getActivity());

        PC=new ProfileController(this);
        //PC.GetProfile();

        setContent(preferences.getBoolean("session",false));

        txtLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getActivity(), LoginActivity.class));
            }
        });
        return view;
    }

    public void setContent(boolean login)
    {
        if(login)
        {
            txtLogin.setVisibility(View.GONE);
            LayoutProfile.setVisibility(View.VISIBLE);
        }
        else if(!login)
        {
            txtLogin.setVisibility(View.VISIBLE);
            LayoutProfile.setVisibility(View.GONE);
        }
    }

    public void setCover(int id_cover)
    {
        if(id_cover==1)
        {
            BgProfile.setBackgroundResource(R.drawable.cover_1);
        }
        if(id_cover==2)
        {
            BgProfile.setBackgroundResource(R.drawable.cover_2);
        }
        if(id_cover==3)
        {
            BgProfile.setBackgroundResource(R.drawable.cover_3);
        }
        if(id_cover==4)
        {
            BgProfile.setBackgroundResource(R.drawable.cover_4);
        }
        if(id_cover==5)
        {
            BgProfile.setBackgroundResource(R.drawable.cover_5);
        }
        if(id_cover==6)
        {
            BgProfile.setBackgroundResource(R.drawable.cover_6);
        }
        if(id_cover==7)
        {
            BgProfile.setBackgroundResource(R.drawable.cover_7);
        }
        if(id_cover==8)
        {
            BgProfile.setBackgroundResource(R.drawable.cover_8);
        }
        if(id_cover==9)
        {
            BgProfile.setBackgroundResource(R.drawable.cover_9);
        }
        if(id_cover==1)
        {
            BgProfile.setBackgroundResource(R.drawable.cover_1);
        }
    }

    private void setupViewPager(ViewPager viewPager){
        ViewPagerAdapter adapter = new ViewPagerAdapter(getChildFragmentManager());
        adapter.addFragment(new FragmentMypost(),"Posts");
        adapter.addFragment(new FragmentBookmarks(), "Bookmarks");
        viewPager.setAdapter(adapter);
    }

    public void setFont()
    {
        Typeface Roboto_Regular=Typeface.createFromAsset(getActivity().getAssets(), "fonts/Roboto-Regular.ttf");
        txtLogin.setTypeface(Roboto_Regular);
        txtJumlahPost.setTypeface(Roboto_Regular);
        Username.setTypeface(Roboto_Regular);
        Location.setTypeface(Roboto_Regular);
        UserBio.setTypeface(Roboto_Regular);

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

    @Override
    public void getProfileResponse(boolean error, ProfileResponse response, Throwable t) {
        if(!error)
        {
            ProfileResponse model=response;
            Username.setText(model.name);
            Location.setText(model.address);
            UserBio.setText(model.bio);
            txtJumlahPost.setText(String.valueOf(model.total_posts)+" Posts");
            setCover(model.id_cover_photo);

            if(model.id_avatar==1)
            {
                AvatarUser.setImageResource(R.drawable.avatar_1);
            }
            if(model.id_avatar==2)
            {
                AvatarUser.setImageResource(R.drawable.avatar_2);
            }
            if(model.id_avatar==3)
            {
                AvatarUser.setImageResource(R.drawable.avatar_3);
            }
            if(model.id_avatar==4)
            {
                AvatarUser.setImageResource(R.drawable.avatar_4);
            }
            if(model.id_avatar==5)
            {
                AvatarUser.setImageResource(R.drawable.avatar_5);
            }
        }
    }
}
