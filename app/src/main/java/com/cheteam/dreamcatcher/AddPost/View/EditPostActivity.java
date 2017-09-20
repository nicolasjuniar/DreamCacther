package com.cheteam.dreamcatcher.AddPost.View;


import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import com.cheteam.dreamcatcher.ArticlePreview.View.EditPostPreview;
import com.cheteam.dreamcatcher.R;
import com.cheteam.dreamcatcher.Timeline.Fragment.DialogFragmentSelectCategory;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Rahmat Al Hakam on 16/09/2017.
 * Layout Edit Post yang sesuai dengan zeplin
 */

public class EditPostActivity extends AppCompatActivity{
    @BindView(R.id.et_title_ep) TextView et_title_ep;
    @BindView(R.id.toolbar_ep) Toolbar myToolbar;
    @BindView(R.id.tv_add_category_ep) TextView tv_add_category_ep;
    @BindView(R.id.tv_edit_category_ep) TextView tv_edit_category_ep;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_post);
        ButterKnife.bind(this);
        setSupportActionBar(myToolbar);
        getSupportActionBar().setTitle("Edit Post");
        myToolbar.setTitleTextColor(getResources().getColor(R.color.White));
        ActionBar ab = getSupportActionBar();
        ab.setDisplayHomeAsUpEnabled(true);
        myToolbar.setSubtitleTextColor(getResources().getColor(R.color.White));

        tv_add_category_ep = (TextView) findViewById(R.id.tv_add_category_ep);
        tv_edit_category_ep= (TextView) findViewById(R.id.tv_edit_category_ep);

        tv_add_category_ep.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                new DialogFragmentSelectCategory().show(getSupportFragmentManager(),"Select Category");
            }
        });
        tv_edit_category_ep.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new DialogFragmentSelectCategory().show(getSupportFragmentManager(),"Select Category");
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
            Intent intent = new Intent(EditPostActivity.this,EditPostPreview.class);
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
