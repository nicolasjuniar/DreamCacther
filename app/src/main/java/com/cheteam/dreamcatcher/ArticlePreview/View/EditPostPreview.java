package com.cheteam.dreamcatcher.ArticlePreview.View;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.cheteam.dreamcatcher.ArticlePreview.Controller.ArticleController;
import com.cheteam.dreamcatcher.ArticlePreview.Model.ViewArticleResponse;
import com.cheteam.dreamcatcher.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import de.hdodenhof.circleimageview.CircleImageView;

/**
 * Created by Rahmat Al Hakam on 17/09/2017.
 */

public class EditPostPreview extends AppCompatActivity implements ArticleController.onViewArticleResponse{
    @BindView(R.id.bg_epp) ImageView bg_epp;
    @BindView(R.id.tv_title_epp) TextView tv_title_epp;
    @BindView(R.id.civ_avatar_epp) CircleImageView civ_avatar_epp;
    @BindView(R.id.tv_name_epp) TextView tv_name_epp;
    @BindView(R.id.tv_in_epp) TextView tv_in_epp;
    @BindView(R.id.tv_category_epp) TextView tv_category_epp;
    @BindView(R.id.tv_date_epp) TextView tv_date_epp;
    @BindView(R.id.tv_content_epp) TextView tv_content_epp;
    @BindView(R.id.toolbar_epp) Toolbar myToolbar;
    @BindView(R.id.bookmark_icon_epp) ImageButton bookmark_icon_epp;
    Boolean cek=false;
    ArticleController AC;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        setContentView(R.layout.activity_edit_post_preview);
        super.onCreate(savedInstanceState);
        ButterKnife.bind(this);
        setSupportActionBar(myToolbar);
        getSupportActionBar().setTitle("View Post");
        myToolbar.setTitleTextColor(getResources().getColor(R.color.White));
        ActionBar ab = getSupportActionBar();
        ab.setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        myToolbar.setSubtitleTextColor(getResources().getColor(R.color.White));
        AC=new ArticleController(this);
        AC.GetArticle(1);
        bookmark_icon_epp.setBackgroundResource(R.drawable.ic_bookmark_border_black_24dp);

        bookmark_icon_epp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!cek)
                {
                    cek=true;
                    bookmark_icon_epp.setBackgroundResource(R.drawable.icon_bookmark);
                }
                else if(cek)
                {
                    cek=false;
                    bookmark_icon_epp.setBackgroundResource(R.drawable.ic_bookmark_border_black_24dp);
                }
            }
        });
    }

    @Override
    public void getViewArticleResponse(boolean error, ViewArticleResponse response, Throwable t) {
        if(!error) {
            ViewArticleResponse model = response;
            tv_name_epp.setText(model.name);
            tv_title_epp.setText(model.post_title);
            tv_date_epp.setText(model.published_at);
            tv_content_epp.setText(model.content);
            if(model.id_background==1)
                bg_epp.setBackgroundResource(R.drawable.red_bg);
            if(model.id_background==2)
                bg_epp.setBackgroundResource(R.drawable.green_bg);
            if(model.id_background==3)
                bg_epp.setBackgroundResource(R.drawable.blue_bg);
            if(model.id_background==4)
                bg_epp.setBackgroundResource(R.drawable.yellow_bg);
            if(model.id_background==5)
                bg_epp.setBackgroundResource(R.drawable.violet_bg);
            if(model.id_avatar==0)
                civ_avatar_epp.setBackgroundResource(R.drawable.avatar_0);
            if(model.id_avatar==1)
                civ_avatar_epp.setBackgroundResource(R.drawable.avatar_1);
            if(model.id_avatar==2)
                civ_avatar_epp.setBackgroundResource(R.drawable.avatar_2);
            if(model.id_avatar==3)
                civ_avatar_epp.setBackgroundResource(R.drawable.avatar_3);
            if(model.id_avatar==4)
                civ_avatar_epp.setBackgroundResource(R.drawable.avatar_4);
            if(model.id_avatar==5)
                civ_avatar_epp.setBackgroundResource(R.drawable.avatar_5);
            if(model.id_avatar==6)
                civ_avatar_epp.setBackgroundResource(R.drawable.avatar_6);
            if(model.id_avatar==7)
                civ_avatar_epp.setBackgroundResource(R.drawable.avatar_7);
            if(model.id_avatar==8)
                civ_avatar_epp.setBackgroundResource(R.drawable.avatar_8);
            if(model.id_avatar==9)
                civ_avatar_epp.setBackgroundResource(R.drawable.avatar_9);
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        onBackPressed();
        return super.onOptionsItemSelected(item);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.add_post, menu);
        return true;
    }
}
