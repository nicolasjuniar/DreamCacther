package com.cheteam.dreamcatcher.AddPost.View;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentHostCallback;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.cheteam.dreamcatcher.ArticlePreview.View.ActivityPreview;
import com.cheteam.dreamcatcher.R;
import com.cheteam.dreamcatcher.Timeline.Fragment.DialogFragmentSelectCategory;

import org.w3c.dom.Text;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by Rahmat Al Hakam on 08/09/2017.
 * Layout ADD POST
 */

public class AddPostActivity extends AppCompatActivity  {

    @BindView(R.id.my_toolbar_add_post) Toolbar myToolbar;
    @BindView(R.id.iv_icon_next) ImageView iv_icon_next;
    @BindView(R.id.add_post_add_category) LinearLayout add_post_add_category;
    @BindView(R.id.add_post_title) EditText add_post_title;
    @BindView(R.id.ib_bg_1_ap) ImageButton ib_bg_1_ap;
    @BindView(R.id.ib_bg_2_ap) ImageButton ib_bg_2_ap;
    @BindView(R.id.ib_bg_3_ap) ImageButton ib_bg_3_ap;
    @BindView(R.id.ib_bg_4_ap) ImageButton ib_bg_4_ap;
    @BindView(R.id.ib_bg_5_ap) ImageButton ib_bg_5_ap;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_post);
        ButterKnife.bind(this);
        setSupportActionBar(myToolbar);
        getSupportActionBar().setTitle("Create New Post");
        myToolbar.setTitleTextColor(getResources().getColor(R.color.White));
        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);
        myToolbar.setSubtitleTextColor(getResources().getColor(R.color.White));
        add_post_add_category.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                String[] singleChoiceItems = getResources().getStringArray(R.array.dialog_choice_array);;
                int itemSelected = 0;
                new AlertDialog.Builder(AddPostActivity.this)
                        .setTitle("Select Category")
                        .setSingleChoiceItems(singleChoiceItems, itemSelected, new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                            }
                        })
                        .setPositiveButton("APPLY",null)
                        .setNegativeButton("CANCEL", null)
                        .show();
            }
        });

        ib_bg_1_ap.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                add_post_title.setBackgroundResource(R.drawable.red_bg);
            }
        });
        ib_bg_2_ap.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                add_post_title.setBackgroundResource(R.drawable.green_bg);
            }
        });
        ib_bg_3_ap.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                add_post_title.setBackgroundResource(R.drawable.blue_bg);
            }
        });
        ib_bg_4_ap.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                add_post_title.setBackgroundResource(R.drawable.yellow_bg);
            }
        });
        ib_bg_5_ap.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                add_post_title.setBackgroundResource(R.drawable.violet_bg);
            }
        });

    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.add_post, menu);
        return true;
    }
    @Override
    public boolean onSupportNavigateUp(){
        onBackPressed();
        return true;
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if(id==R.id.add_post_menu)
        {
            Intent intent=new Intent(AddPostActivity.this,ActivityPreview.class);
            startActivity(intent);
        }
        return super.onOptionsItemSelected(item);

    }
}
