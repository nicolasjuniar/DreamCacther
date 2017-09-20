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
import com.cheteam.dreamcatcher.ArticlePreview.View.ActivityPreview;
import com.cheteam.dreamcatcher.R;
import com.cheteam.dreamcatcher.Timeline.Fragment.DialogFragmentSelectCategory;
import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Rahmat Al Hakam on 08/09/2017.
 * Layout ADD POST
 */

public class AddPostActivity extends AppCompatActivity {

    @BindView(R.id.tv_edit_category) TextView tv_edit_category;
    @BindView(R.id.my_toolbar_add_post) Toolbar myToolbar;
    @BindView(R.id.add_post_clickOn_categories) TextView add_post_clickOn_categories;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_post);
        ButterKnife.bind(this);
        setSupportActionBar(myToolbar);
        getSupportActionBar().setTitle("Create New Post");
        myToolbar.setTitleTextColor(getResources().getColor(R.color.White));
        ActionBar ab = getSupportActionBar();
        ab.setDisplayHomeAsUpEnabled(true);
        myToolbar.setSubtitleTextColor(getResources().getColor(R.color.White));
        tv_edit_category=(TextView) findViewById(R.id.tv_edit_category);
        add_post_clickOn_categories=(TextView) findViewById(R.id.add_post_clickOn_categories);


        tv_edit_category.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new DialogFragmentSelectCategory().show(getSupportFragmentManager(),"Select Category");
            }
        });
        add_post_clickOn_categories.setOnClickListener(new View.OnClickListener() {
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
    public boolean onSupportNavigateUp(){
        onBackPressed();
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        if(id==R.id.add_post_menu)
        {
            Intent intent=new Intent(AddPostActivity.this,ActivityPreview.class);
            startActivity(intent);
        }
        return super.onOptionsItemSelected(item);
    }


}
