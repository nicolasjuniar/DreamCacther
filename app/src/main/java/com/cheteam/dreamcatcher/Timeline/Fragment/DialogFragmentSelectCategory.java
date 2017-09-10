package com.cheteam.dreamcatcher.Timeline.Fragment;

import android.support.v4.app.DialogFragment;
import android.app.ProgressDialog;
import android.content.SharedPreferences;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;


import com.cheteam.dreamcatcher.R;
import com.cheteam.dreamcatcher.Timeline.Adapter.RecycleViewAdapterSelectListCategory;

import java.util.ArrayList;

import retrofit.Call;
import retrofit.Callback;
import retrofit.Response;

/**
 * Created by Nicolas Juniar on 01/11/2016.
 */

public class DialogFragmentSelectCategory extends DialogFragment {

    RecycleViewAdapterSelectListCategory adapter;
    ArrayList<String> ListCategory;
    RecyclerView recyclerView;
    TextView txtApply,txtCancel,filter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        final View view = inflater.inflate(R.layout.dialog_select_category_layout,
                container, false);
        getDialog().getWindow().requestFeature(Window.FEATURE_NO_TITLE);
        recyclerView=(RecyclerView) view.findViewById(R.id.ListCategories);
        txtApply=(TextView) view.findViewById(R.id.txtApply);
        txtCancel=(TextView) view.findViewById(R.id.txtCancel);
        filter=(TextView) view.findViewById(R.id.filter);
        setListCategories();
        setFont();

        txtCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dismiss();
            }
        });

        txtApply.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dismiss();
            }
        });
        return view;
    }

    public void setFont()
    {
        Typeface Roboto_Regular=Typeface.createFromAsset(getActivity().getAssets(), "fonts/Roboto-Regular.ttf");
        txtApply.setTypeface(Roboto_Regular);
        txtCancel.setTypeface(Roboto_Regular);
        filter.setTypeface(Roboto_Regular);
    }

    public void setListCategories()
    {
        ListCategory=new ArrayList<>();
        ListCategory.add("Finances");
        ListCategory.add("Skills");
        ListCategory.add("Facilities");
        ListCategory.add("Opportunities");
        ListCategory.add("Courses");
        adapter=new RecycleViewAdapterSelectListCategory(ListCategory,getActivity());
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
    }
}
