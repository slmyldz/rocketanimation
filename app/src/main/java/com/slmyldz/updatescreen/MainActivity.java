package com.slmyldz.updatescreen;

import android.animation.Animator;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.os.Handler;
import android.support.v4.view.animation.FastOutLinearInInterpolator;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.AnimationSet;
import android.view.animation.TranslateAnimation;

import java.util.Random;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);

        View plane =   findViewById(R.id.plane);
        View fire =   findViewById(R.id.fire);

        final View light1 =   findViewById(R.id.light);
        final View light2 =   findViewById(R.id.light2);
        final View light3 =   findViewById(R.id.light3);
        final View light5 =   findViewById(R.id.light5);


        ObjectAnimator an1 = ObjectAnimator.ofFloat(plane, "translationX", 0, 7, -7, 12, -7,3, -3, 2, -2, 0);
        an1.setRepeatCount(ObjectAnimator.INFINITE | ObjectAnimator.RESTART);
        an1.setDuration(3000);
        ObjectAnimator an2 = ObjectAnimator.ofFloat(plane, "translationY", 0, 6, -7, 8, -6,4, -12, 7, -5, 0);
        an2.setRepeatCount(ObjectAnimator.INFINITE | ObjectAnimator.RESTART);
        an2.setDuration(3000);



        ObjectAnimator firean2 = ObjectAnimator.ofFloat(fire,"scaleY",1,1.2f,1.3f,1.5f,1.2f,1f);
        firean2.setRepeatCount(ObjectAnimator.INFINITE | ObjectAnimator.RESTART);
        firean2.setDuration(1000);
        ObjectAnimator firean3 =   ObjectAnimator.ofFloat(fire, "translationY", 0, 6, -7, 8, -6,4, -12, 7, -5, 0);
        firean3.setRepeatCount(ObjectAnimator.INFINITE | ObjectAnimator.RESTART);
        firean3.setDuration(3000);
        ObjectAnimator firean4 =  ObjectAnimator.ofFloat(fire, "translationX", 0, 7, -7, 12, -7,3, -3, 2, -2, 0);
        firean4.setRepeatCount(ObjectAnimator.INFINITE | ObjectAnimator.RESTART);
        firean4.setDuration(3000);

        AnimatorSet set = new AnimatorSet();
        set.playTogether(an1,an2,firean4,firean3,firean2);
        //set.setDuration(5000);

        set.start();


        startRandomLights(new View[]{light1,light2,light3,light5});

    }


    public void startRandomLights(final View[] views ){


       Handler  handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                View rv = views[new Random().nextInt(views.length-1)];
                rv.setVisibility(View.VISIBLE);
                startLightAnimations(rv,250);
                startRandomLights(views);

            }
        },new Random().nextInt(5000));








    }


    public void startLightAnimations(final View view, final int delay){
        ObjectAnimator light2_animationY =   ObjectAnimator.ofFloat(view, "translationY",  -300,2000);
        ObjectAnimator light2_animationX =   ObjectAnimator.ofFloat(view, "translationX", 250, -2000);
        AnimatorSet lset = new AnimatorSet();
        lset.setDuration(200);
        lset.setStartDelay(delay);
        lset.playTogether(light2_animationX,light2_animationY);
        lset.addListener(new Animator.AnimatorListener() {
            @Override
            public void onAnimationStart(Animator animation) {
                view.animate().alpha(1f).setStartDelay(delay).setDuration(0);
            }

            @Override
            public void onAnimationEnd(Animator animation) {
                view.animate().alpha(0f).setStartDelay(0).setDuration(0);
            }

            @Override
            public void onAnimationCancel(Animator animation) {

            }

            @Override
            public void onAnimationRepeat(Animator animation) {

            }
        });
        lset.setInterpolator(new FastOutLinearInInterpolator());
        lset.start();



    }

}
