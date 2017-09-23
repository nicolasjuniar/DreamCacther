package com.cheteam.dreamcatcher.AddPost.View;


import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
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
import android.widget.Toast;

import com.cheteam.dreamcatcher.ArticlePreview.View.ActivityPreview;
import com.cheteam.dreamcatcher.ArticlePreview.View.EditPostPreview;
import com.cheteam.dreamcatcher.R;
import com.cheteam.dreamcatcher.Timeline.Fragment.DialogFragmentSelectCategory;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by Rahmat Al Hakam on 16/09/2017.
 */

public class EditPostActivity extends AppCompatActivity{
    @BindView(R.id.toolbar_ep) Toolbar myToolbar;
    @BindView(R.id.ll_select_category_ep) LinearLayout ll_select_category_ep;
    @BindView(R.id.bg_1_ep) ImageButton bg_1_ep;
    @BindView(R.id.bg_2_ep) ImageButton bg_2_ep;
    @BindView(R.id.bg_3_ep) ImageButton bg_3_ep;
    @BindView(R.id.bg_4_ep) ImageButton bg_4_ep;
    @BindView(R.id.bg_5_ep) ImageButton bg_5_ep;
    @BindView(R.id.et_title_ep) EditText et_title_ep;
    @BindView(R.id.et_content_ep) EditText et_content_ep;

    Bundle bundle = new Bundle();
    private int id_background = 1;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_post);
        ButterKnife.bind(this);
        setSupportActionBar(myToolbar);
        getSupportActionBar().setTitle("Edit Post");
        myToolbar.setTitleTextColor(getResources().getColor(R.color.White));
        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);
        myToolbar.setSubtitleTextColor(getResources().getColor(R.color.White));

        ll_select_category_ep.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                String[] singleChoiceItems = getResources().getStringArray(R.array.dialog_choice_array);;
                int itemSelected = 0;
                new AlertDialog.Builder(EditPostActivity.this)
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
        bg_1_ep.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                et_title_ep.setBackgroundResource(R.drawable.red_bg);
            }
        });
        bg_2_ep.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                et_title_ep.setBackgroundResource(R.drawable.green_bg);
            }
        });
        bg_3_ep.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                et_title_ep.setBackgroundResource(R.drawable.blue_bg);
            }
        });
        bg_4_ep.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                et_title_ep.setBackgroundResource(R.drawable.yellow_bg);
            }
        });
        bg_5_ep.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                et_title_ep.setBackgroundResource(R.drawable.violet_bg);
            }
        });


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.add_post, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if(id==R.id.add_post_menu){
            bundle.putInt("id_user",1);
            bundle.putInt("id_background", id_background);
            bundle.putString("post_title", et_title_ep.getText().toString());
            bundle.putString("categories", "Finances");
            bundle.putString("content", et_content_ep.getText().toString());
            Intent intent = new Intent(this, ActivityPreview.class);
            intent.putExtras(bundle);
            startActivity(intent);
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }

}
