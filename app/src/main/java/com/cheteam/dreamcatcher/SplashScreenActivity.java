package com.cheteam.dreamcatcher;

import android.content.Intent;
import android.content.res.Resources;
import android.graphics.Typeface;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.cheteam.dreamcatcher.Login.Controller.LoginAPI;
import com.cheteam.dreamcatcher.Login.Model.LoginResponse;
import com.cheteam.dreamcatcher.Login.View.LoginActivity;
import com.google.gson.Gson;
import com.squareup.okhttp.ResponseBody;

import retrofit.Call;
import retrofit.Callback;
import retrofit.Response;

/**
 * Created by Nicolas Juniar on 31/08/2017.
 */

public class SplashScreenActivity extends AppCompatActivity{

    TextView title1,title2;

    LoginAPI service;
    Call<LoginResponse> CallBody;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splashscreen_layout);
        title1=(TextView) findViewById(R.id.title1);
        title2=(TextView) findViewById(R.id.title2);
        Typeface Lobster_Regular=Typeface.createFromAsset(getAssets(), "fonts/Lobster-Regular.ttf");
        title1.setTypeface(Lobster_Regular);
        Typeface justAnotherHand=Typeface.createFromAsset(getAssets(), "fonts/JustAnotherHand.ttf");
        title2.setTypeface(justAnotherHand);

        Thread logoTimer = new Thread() {
            public void run() {
                try {
                    sleep(2000);
                } catch (InterruptedException e) {
                    Log.d("Exception", "Exception" + e);
                } finally {
                    startActivity(new Intent(SplashScreenActivity.this,LoginActivity.class));
                }
                finish();
            }
        };
        logoTimer.start();


//        LinearLayout linearLayout=(LinearLayout) findViewById(R.id.LogoDab);
//
//        linearLayout.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                setAnim(view, R.anim.anim_zoom_in, new AnimListener() {
//                    @Override
//                    public void onAnimStart() {
//
//                    }
//
//                    @Override
//                    public void onAnimRepeat() {
//
//                    }
//
//                    @Override
//                    public void onAnimEnd() {
//                        Toast.makeText(SplashScreenActivity.this, "Animasine Rampung", Toast.LENGTH_SHORT).show();
//                    }
//                });
//            }
//        });
//
//
//        setAnim(linearLayout, R.anim.anim_zoom_in, new AnimListener() {
//            @Override
//            public void onAnimStart() {
//
//            }
//
//            @Override
//            public void onAnimRepeat() {
//
//            }
//
//            @Override
//            public void onAnimEnd() {
//                Toast.makeText(SplashScreenActivity.this, "Animasine Rampung", Toast.LENGTH_SHORT).show();
//                Thread logoTimer = new Thread() {
//                    public void run() {
//                        try {
//                            sleep(2000);
//                        } catch (InterruptedException e) {
//                            Log.d("Exception", "Exception" + e);
//                        } finally {
//                            startActivity(new Intent(SplashScreenActivity.this,LoginActivity.class));
//                        }
//                        finish();
//                    }
//                };
//                logoTimer.start();
//            }
//        });

    }




    /////////////////////////Animation///////////////////////////////////

    private Animation animZoomIn()
    {
        Animation anim=null;
        try {
            anim= AnimationUtils.loadAnimation(SplashScreenActivity.this,R.anim.anim_zoom_in);
        }
        catch (Resources.NotFoundException e)
        {
            Toast.makeText(this, e.getMessage(), Toast.LENGTH_SHORT).show();
            e.printStackTrace();
        }
        return anim;
    }

    public interface AnimListener
    {
        void onAnimStart();
        void onAnimRepeat();
        void onAnimEnd();
    }

    public void startAnimZoomInAnim(final View view, final Animation animation)
    {
//        ///Handler
//        new Handler().post(new Runnable() {
//            @Override
//            public void run() {
//                view.startAnimation(animation);
//            }
//        });
//
//        ///view
//        view.post(new Runnable() {
//            @Override
//            public void run() {
//                view.startAnimation(animation);
//            }
//        });


        ////new thread
        final Handler handler=new Handler();
        new Thread(new Runnable() {
            @Override
            public void run() {
                handler.post(new Runnable() {
                    @Override
                    public void run() {
                        view.startAnimation(animation);
                    }
                });
            }
        }).start();
    }

    public void setAnim(View view, final int animType,final AnimListener listener)
    {
        Animation animation=animZoomIn();
        animation.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {
                listener.onAnimStart();
            }

            @Override
            public void onAnimationEnd(Animation animation) {
                listener.onAnimEnd();
            }

            @Override
            public void onAnimationRepeat(Animation animation) {
                listener.onAnimRepeat();
            }
        });

        startAnimZoomInAnim(view,animation);
    }


    //////////////////////////////////////////////////////////////////////////

}
