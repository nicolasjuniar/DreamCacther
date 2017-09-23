package com.cheteam.dreamcatcher.AddPost.View;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.res.Resources;
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
import android.widget.Toast;

import com.cheteam.dreamcatcher.ArticlePreview.View.ActivityPreview;
import com.cheteam.dreamcatcher.R;
import com.cheteam.dreamcatcher.Timeline.Fragment.DialogFragmentSelectCategory;

import org.w3c.dom.Text;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;

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
    @BindView(R.id.et_content_add_post) EditText et_content_add_post;

    String category = "";
    Bundle bundle = new Bundle();
    private int id_background = 1;
    //idCat=0;

//    public void getIdCat(int id){
//
//    }
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
                final String[] singleChoiceItems = getResources().getStringArray(R.array.dialog_choice_array);;
                final int itemSelected = 0;

                new AlertDialog.Builder(AddPostActivity.this)
                        .setTitle("Select Category")
                        .setSingleChoiceItems(singleChoiceItems, itemSelected, new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                Toast.makeText(getApplicationContext(), singleChoiceItems[i], Toast.LENGTH_SHORT).show();
                                switch (singleChoiceItems[i]){
                                    case "Finances": category="Finances"; break;
                                    case "Skills": category="Skills"; break;
                                    case "Facilities": category="Facilities"; break;
                                    case "Opportunities": category="Opportunities"; break;
                                    case "Courses": category="Courses"; break;
                                }
                            }
                        })
                        .setPositiveButton("APPLY", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {

                            }
                        })
                        .setNegativeButton("CANCEL", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                dialog.dismiss();
                            }
                        })
                        .show();
            }
        });

        ib_bg_1_ap.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                add_post_title.setBackgroundResource(R.drawable.red_bg);
                id_background = 1;
            }
        });
        ib_bg_2_ap.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                add_post_title.setBackgroundResource(R.drawable.green_bg);
                id_background = 2;
            }
        });
        ib_bg_3_ap.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                add_post_title.setBackgroundResource(R.drawable.blue_bg);
                id_background = 3;
            }
        });
        ib_bg_4_ap.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                add_post_title.setBackgroundResource(R.drawable.yellow_bg);
                id_background = 4;
            }
        });
        ib_bg_5_ap.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                add_post_title.setBackgroundResource(R.drawable.violet_bg);
                id_background = 5;
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
            bundle.putInt("id_user", 1);
            bundle.putInt("id_background", id_background);
            bundle.putString("post_title", add_post_title.getText().toString());
            bundle.putString("categories", category);
            bundle.putString("content", et_content_add_post.getText().toString());
            Intent intent = new Intent(this, ActivityPreview.class);
            intent.putExtras(bundle);
            startActivity(intent);
        }
        return super.onOptionsItemSelected(item);

    }
}
