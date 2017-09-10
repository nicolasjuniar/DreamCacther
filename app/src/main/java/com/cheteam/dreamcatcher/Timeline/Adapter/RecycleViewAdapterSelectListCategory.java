package com.cheteam.dreamcatcher.Timeline.Adapter;

import android.content.Context;
import android.graphics.Typeface;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.cheteam.dreamcatcher.R;
import com.cheteam.dreamcatcher.Timeline.Model.ModelTimeline;

import java.util.Collections;
import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

/**
 * Created by Nicolas Juniar on 14/11/2016.
 */

public class RecycleViewAdapterSelectListCategory extends RecyclerView.Adapter<RecycleViewAdapterSelectListCategory.ViewHolder> {

    List<String> list = Collections.emptyList();
    Context context;


    public View view;

    public RecycleViewAdapterSelectListCategory(List<String> list, Context context) {
        this.context = context;
        this.list = list;
    }


    @Override
    public RecycleViewAdapterSelectListCategory.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.viewholder_select_category_item, parent, false);
        RecycleViewAdapterSelectListCategory.ViewHolder holder = new RecycleViewAdapterSelectListCategory.ViewHolder(v);

        return holder;
    }

    @Override
    public void onBindViewHolder(RecycleViewAdapterSelectListCategory.ViewHolder holder, int position) {
        Typeface Merriweather_Bold=Typeface.createFromAsset(context.getAssets(), "fonts/Merriweather-Bold.ttf");
        Typeface Lobster_Regular=Typeface.createFromAsset(context.getAssets(), "fonts/Lobster-Regular.ttf");
        Typeface RockoFLF=Typeface.createFromAsset(context.getAssets(), "fonts/RockoFLF.ttf");
        Typeface Roboto_Regular=Typeface.createFromAsset(context.getAssets(), "fonts/Roboto-Regular.ttf");
        holder.btnCategory.setTypeface(Roboto_Regular);

        holder.btnCategory.setText(list.get(position));

    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    @Override
    public void onAttachedToRecyclerView(RecyclerView recyclerView) {
        super.onAttachedToRecyclerView(recyclerView);
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        Button btnCategory;
        boolean cek=false;

        ViewHolder(View itemView) {
            super(itemView);
            view = itemView;
            btnCategory=(Button) view.findViewById(R.id.btnCategory);

            btnCategory.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if(!cek)
                    {
                        cek=true;
                        btnCategory.setBackground(context.getResources().getDrawable(R.drawable.button_blue));
                        btnCategory.setTextColor(context.getResources().getColor(R.color.White));
                    }
                    else if(cek)
                    {
                        cek=false;
                        btnCategory.setBackground(context.getResources().getDrawable(R.drawable.button_white));
                        btnCategory.setTextColor(context.getResources().getColor(R.color.Black));
                    }
                }
            });
        }
    }
}