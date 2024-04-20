package com.example.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.TranslateAnimation;
import android.widget.Button;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity; //impor



public class startMainActivity extends AppCompatActivity {
     private ImageView ip;
    /**private LinearLayout linearLayout;**/
    private Button login;
    private Button registered;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.startactivity_main);
        ip=findViewById(R.id.ip);
     /**   linearLayout=findViewById(R.id.linearlayout);**/
        login=findViewById(R.id.login);
        registered=findViewById(R.id.registered);

        /**linearLayout.animate().alpha(0f).setDuration(1);**/


            TranslateAnimation animation=new TranslateAnimation(0,0,0,-1000);
            animation.setDuration(1000);
            animation.setFillAfter(false);
            animation.setAnimationListener(new myanimationlistner());

         ip.setAnimation(animation);

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(startMainActivity.this,login.class).addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK|Intent.FLAG_ACTIVITY_CLEAR_TOP));
            }
        });

        registered.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(startMainActivity.this,register.class).addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK|Intent.FLAG_ACTIVITY_CLEAR_TOP));
            }
        });
    }
    private  class myanimationlistner implements Animation.AnimationListener{


        @Override
        public void onAnimationStart(Animation animation) {

        }

        @Override
        public void onAnimationEnd(Animation animation) {
            ip.clearAnimation();
            ip.setVisibility(View.INVISIBLE);
            /**linearLayout.animate().alpha(1f).setDuration(1000);**/
        }

        @Override
        public void onAnimationRepeat(Animation animation) {

        }
    }

}