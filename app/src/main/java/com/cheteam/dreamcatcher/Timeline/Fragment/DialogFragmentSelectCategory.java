package com.cheteam.dreamcatcher.Timeline.Fragment;

import android.support.v4.app.DialogFragment;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.TextView;


import com.cheteam.dreamcatcher.R;
import com.cheteam.dreamcatcher.Timeline.Adapter.RecycleViewAdapterSelectListCategory;
import com.cheteam.dreamcatcher.Timeline.Interface.IChangeCategory;
import com.cheteam.dreamcatcher.Timeline.Interface.ISetCategory;
import com.cheteam.dreamcatcher.Timeline.Model.ModelCategory;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Nicolas Juniar on 01/11/2016.
 */

public class DialogFragmentSelectCategory extends DialogFragment implements IChangeCategory {

    @BindView(R.id.ListCategories) RecyclerView ListCategories;
    @BindView(R.id.txtApply) TextView txtApply;
    @BindView(R.id.txtCancel) TextView txtCancel;
    @BindView(R.id.filter) TextView filter;
    @BindView(R.id.category) TextView category;

    RecycleViewAdapterSelectListCategory adapter;
    ArrayList<ModelCategory>  ListCategory;
    ArrayList<String> cekListCategory;
    ArrayList<String> ListInterest;
    ISetCategory listener;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        final View view = inflater.inflate(R.layout.dialog_select_category_layout,
                container, false);
        getDialog().getWindow().requestFeature(Window.FEATURE_NO_TITLE);
        ButterKnife.bind(this,view);
        setFont();
        Bundle arguments = getArguments();
        ListInterest=arguments.getStringArrayList("listinterest");
        cekListCategory=new ArrayList<>();
        cekListCategory.addAll(ListInterest);
        setListCategories();

        txtCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dismiss();
            }
        });
        txtApply.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                listener.setCategory(cekListCategory);
                dismiss();
            }
        });

        return view;
    }

    public void setListener(ISetCategory listener)
    {
        this.listener=listener;
    }

    public void setFont()
    {
        Typeface Roboto_Regular=Typeface.createFromAsset(getActivity().getAssets(), "fonts/Roboto-Regular.ttf");
        txtApply.setTypeface(Roboto_Regular);
        txtCancel.setTypeface(Roboto_Regular);
        filter.setTypeface(Roboto_Regular);
        category.setTypeface(Roboto_Regular);
    }

    public void setListCategories()
    {
        ListCategory=new ArrayList<>();
        ListCategory.add(new ModelCategory("Finances",false));
        ListCategory.add(new ModelCategory("Skills",false));
        ListCategory.add(new ModelCategory("Facilities",false));
        ListCategory.add(new ModelCategory("Opportunities",false));
        ListCategory.add(new ModelCategory("Courses",false));
        for (String category: cekListCategory) {
            if(ListCategory.contains(new ModelCategory(category,false)))
            {
                int index=ListCategory.indexOf(new ModelCategory(category,false));
                ListCategory.get(index).setCek(true);
            }
        }
        adapter=new RecycleViewAdapterSelectListCategory(ListCategory,getActivity(),this,cekListCategory.size());
        ListCategories.setAdapter(adapter);
        ListCategories.setLayoutManager(new LinearLayoutManager(getActivity()));
    }

    @Override
    public void addCategory(String category) {
        cekListCategory.add(category);
    }

    @Override
    public void removeCategory(String category) {
        cekListCategory.remove(category);
    }

    @Override
    public void setApplyOption(boolean set) {
        txtApply.setClickable(set);
        if(set)
        {
            txtApply.setTextColor(getActivity().getResources().getColor(R.color.dialog_true));
        }
        else
        {
            txtApply.setTextColor(getActivity().getResources().getColor(R.color.dialog_false));
        }
    }
}
