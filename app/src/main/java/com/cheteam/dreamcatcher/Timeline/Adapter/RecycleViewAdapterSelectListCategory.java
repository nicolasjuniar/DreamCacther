package com.cheteam.dreamcatcher.Timeline.Adapter;

import android.content.Context;
import android.graphics.Typeface;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

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

    public RecycleViewAdapterSelectListCategory(ArrayList<ModelCategory> ListCategory, Context context, IChangeCategory listener) {
        this.context = context;
        this.ListCategory=ListCategory;
        this.listener = listener;
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
        holder.btnCategory.setTypeface(Roboto_Regular);

        ModelCategory model=ListCategory.get(position);
        holder.btnCategory.setText(model.getCategory());
        holder.cek=model.isCek();
        if(model.isCek())
        {
            holder.btnCategory.setBackground(context.getResources().getDrawable(R.drawable.button_blue));
            holder.btnCategory.setTextColor(context.getResources().getColor(R.color.White));
        }
        else
        {
            holder.btnCategory.setBackground(context.getResources().getDrawable(R.drawable.button_white));
            holder.btnCategory.setTextColor(context.getResources().getColor(R.color.Black));
        }
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

        @BindView(R.id.btnCategory) Button btnCategory;
        boolean cek=false;

        ViewHolder(View itemView) {
            super(itemView);
            view = itemView;
            ButterKnife.bind(this,view);

            btnCategory.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if(!cek)
                    {
                        cek=true;
                        btnCategory.setBackground(context.getResources().getDrawable(R.drawable.button_blue));
                        btnCategory.setTextColor(context.getResources().getColor(R.color.White));
                        listener.addCategory(btnCategory.getText().toString());
                    }
                    else if(cek)
                    {
                        cek=false;
                        btnCategory.setBackground(context.getResources().getDrawable(R.drawable.button_white));
                        btnCategory.setTextColor(context.getResources().getColor(R.color.Black));
                        listener.removeCategory(btnCategory.getText().toString());
                    }
                }
            });
        }
    }
}