package com.marcy.androidintroduction.animation;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageView;

import com.marcy.androidintroduction.R;

/**
 * Created by Marcy on 2019/4/19
 */
public class AnimationActivity extends AppCompatActivity {
    private ImageView ivImage;
    private Button btAlphaAnimation;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_animation);

        ivImage = findViewById(R.id.iv_show);
        btAlphaAnimation = findViewById(R.id.bt_show_alpha_animation);
    }

    public void showAlphaAnimation(View view){
        Animation animation = AnimationUtils.loadAnimation(this , R.anim.alpha_animation);
        animation.setFillAfter(true);
        ivImage.startAnimation(animation);
    }
}
