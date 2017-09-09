package com.cheteam.dreamcatcher.Timeline.Fragment;

import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.cheteam.dreamcatcher.Login.View.LoginActivity;
import com.cheteam.dreamcatcher.R;

/**
 * Created by Nicolas Juniar on 09/09/2017.
 */

public class FragmentProfile extends Fragment {

    TextView txtLogin;

    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        final View view = inflater.inflate(R.layout.fragment_profile_layout,
                container, false);
        txtLogin=(TextView) view.findViewById(R.id.txtLogin);
        setFont();

        txtLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getActivity(), LoginActivity.class));
            }
        });
        return view;
    }

    public void setFont()
    {
        Typeface Roboto_Regular=Typeface.createFromAsset(getActivity().getAssets(), "fonts/Roboto-Regular.ttf");
        txtLogin.setTypeface(Roboto_Regular);
    }
}
