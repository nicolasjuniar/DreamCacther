package com.cheteam.dreamcatcher.Timeline.Adapter;

import android.content.Context;
import android.graphics.Typeface;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SwitchCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import com.cheteam.dreamcatcher.R;
import com.cheteam.dreamcatcher.Timeline.Interface.IChangeCategory;
import com.cheteam.dreamcatcher.Timeline.Model.ModelCategory;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Nicolas Juniar on 14/11/2016.
 */

public class RecycleViewAdapterSelectListCategory extends RecyclerView.Adapter<RecycleViewAdapterSelectListCategory.ViewHolder> {

    ArrayList<ModelCategory> ListCategory;
    Context context;
    public View view;
    private IChangeCategory listener;
    int totalInterest;

    public RecycleViewAdapterSelectListCategory(ArrayList<ModelCategory> ListCategory, Context context, IChangeCategory listener, int totalInterest) {
        this.context = context;
        this.ListCategory=ListCategory;
        this.listener = listener;
        this.totalInterest=totalInterest;
    }


    @Override
    public RecycleViewAdapterSelectListCategory.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.viewholder_select_category_item, parent, false);
        RecycleViewAdapterSelectListCategory.ViewHolder holder = new RecycleViewAdapterSelectListCategory.ViewHolder(v);

        return holder;
    }

    @Override
    public void onBindViewHolder(RecycleViewAdapterSelectListCategory.ViewHolder holder, int position) {
        Typeface Roboto_Regular=Typeface.createFromAsset(context.getAssets(), "fonts/Roboto-Regular.ttf");
        holder.txtCategory.setTypeface(Roboto_Regular);

        ModelCategory model=ListCategory.get(position);
        holder.txtCategory.setText(model.getCategory());
        holder.btnSwitch.setChecked(model.isCek());
    }

    @Override
    public int getItemCount() {
        return ListCategory.size();
    }

    @Override
    public void onAttachedToRecyclerView(RecyclerView recyclerView) {
        super.onAttachedToRecyclerView(recyclerView);
    }



    public class ViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.txtCategory) TextView txtCategory;
        @BindView(R.id.btnSwitch) SwitchCompat btnSwitch;

        ViewHolder(View itemView) {
            super(itemView);
            view = itemView;
            ButterKnife.bind(this,view);

            btnSwitch.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if(btnSwitch.isChecked())
                    {
                        listener.addCategory(txtCategory.getText().toString());
                        totalInterest++;
                        if(totalInterest>=3)
                        {
                            listener.setApplyOption(true);
                        }
                    }
                    else
                    {
                        listener.removeCategory(txtCategory.getText().toString());
                        totalInterest--;
                        if(totalInterest<3)
                        {
                            listener.setApplyOption(false);
                        }
                    }
                }
            });
        }
    }
}