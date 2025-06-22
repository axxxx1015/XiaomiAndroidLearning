package com.example.day7;

import android.animation.Animator;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationSet;
import android.view.animation.RotateAnimation;
import android.view.animation.ScaleAnimation;
import android.view.animation.AlphaAnimation;
import android.view.animation.Interpolator;
import android.widget.Button;
import android.widget.ImageView;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {
    private static final String TAG = "MainActivity";
    private ImageView imageView;
    private Button btnTweenAnimation;
    private Button btnPropertyAnimation;
    private int repeatCount = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        imageView = findViewById(R.id.imageView);
        btnTweenAnimation = findViewById(R.id.btnTweenAnimation);
        btnPropertyAnimation = findViewById(R.id.btnPropertyAnimation);

        // 作业1：补间动画
        btnTweenAnimation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startTweenAnimation();
            }
        });

        // 作业2：属性动画
        btnPropertyAnimation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startPropertyAnimation();
            }
        });
    }

    /**
     * 作业1：补间动画
     * 基于当前View中心点放大1.5倍，同时逆时针旋转720度，由不透明变为透明度0.8
     * 持续2000ms，并且重复动画3次
     */
    private void startTweenAnimation() {
        Log.d(TAG, "animation start");

        // 创建动画集合
        AnimationSet animationSet = new AnimationSet(true);

        // 缩放动画：基于中心点放大1.5倍
        ScaleAnimation scaleAnimation = new ScaleAnimation(
                1.0f, 1.5f,  // fromXScale, toXScale
                1.0f, 1.5f,  // fromYScale, toYScale
                Animation.RELATIVE_TO_SELF, 0.5f,  // pivotXType, pivotXValue
                Animation.RELATIVE_TO_SELF, 0.5f   // pivotYType, pivotYValue
        );
        scaleAnimation.setDuration(2000);

        // 旋转动画：逆时针旋转720度
        RotateAnimation rotateAnimation = new RotateAnimation(
                0f, -720f,  // fromDegrees, toDegrees
                Animation.RELATIVE_TO_SELF, 0.5f,  // pivotXType, pivotXValue
                Animation.RELATIVE_TO_SELF, 0.5f   // pivotYType, pivotYValue
        );
        rotateAnimation.setDuration(2000);

        // 透明度动画：从1.0变为0.8
        AlphaAnimation alphaAnimation = new AlphaAnimation(1.0f, 0.8f);
        alphaAnimation.setDuration(2000);

        // 添加动画到集合
        animationSet.addAnimation(scaleAnimation);
        animationSet.addAnimation(rotateAnimation);
        animationSet.addAnimation(alphaAnimation);

        // 设置重复次数为3次
        animationSet.setRepeatCount(2); // 重复2次，总共执行3次

        // 设置动画监听器
        animationSet.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {
                Log.d(TAG, "animation start");
            }

            @Override
            public void onAnimationEnd(Animation animation) {
                Log.d(TAG, "animation end");
            }

            @Override
            public void onAnimationRepeat(Animation animation) {
                repeatCount++;
                Log.d(TAG, "animation repeat " + repeatCount);
            }
        });

        // 开始动画
        imageView.startAnimation(animationSet);
    }

    /**
     * 作业2：属性动画
     * 使用AnimatorSet，先是当前View围绕X轴旋转360度，持续1000ms
     * 然后向右移动120px，持续1000ms
     * 最后从不透明变成透明度0.5，持续500ms
     */
    private void startPropertyAnimation() {
        // 创建动画集合
        AnimatorSet animatorSet = new AnimatorSet();

        // 围绕X轴旋转360度，持续1000ms
        ObjectAnimator rotationXAnimator = ObjectAnimator.ofFloat(imageView, "rotationX", 0f, 360f);
        rotationXAnimator.setDuration(1000);
        rotationXAnimator.setInterpolator(new CustomInterpolator1());

        // 向右移动120px，持续1000ms
        ObjectAnimator translationXAnimator = ObjectAnimator.ofFloat(imageView, "translationX", 0f, 120f);
        translationXAnimator.setDuration(1000);
        translationXAnimator.setInterpolator(new CustomInterpolator2());

        // 透明度从1.0变为0.5，持续500ms
        ObjectAnimator alphaAnimator = ObjectAnimator.ofFloat(imageView, "alpha", 1.0f, 0.5f);
        alphaAnimator.setDuration(500);

        // 设置动画执行顺序：
        // 1. 旋转和移动同时执行（1000ms）
        // 2. 然后执行透明度动画（500ms）
        animatorSet.play(rotationXAnimator).with(translationXAnimator);
        animatorSet.play(alphaAnimator).after(translationXAnimator);

        // 设置动画监听器
        animatorSet.addListener(new Animator.AnimatorListener() {
            @Override
            public void onAnimationStart(Animator animation) {
                Log.d(TAG, "Property animation start");
            }

            @Override
            public void onAnimationEnd(Animator animation) {
                Log.d(TAG, "Property animation end");
            }

            @Override
            public void onAnimationCancel(Animator animation) {
                Log.d(TAG, "Property animation cancel");
            }

            @Override
            public void onAnimationRepeat(Animator animation) {
                Log.d(TAG, "Property animation repeat");
            }
        });

        // 开始动画
        animatorSet.start();
    }

    /**
     * 自定义插值器1：弹性效果
     */
    private static class CustomInterpolator1 implements Interpolator {
        @Override
        public float getInterpolation(float input) {
            return (float) (1 + Math.sin(input * Math.PI * 2) * Math.exp(-input * 3));
        }
    }

    /**
     * 自定义插值器2：缓动效果
     */
    private static class CustomInterpolator2 implements Interpolator {
        @Override
        public float getInterpolation(float input) {
            return (float) (1 - Math.pow(1 - input, 3));
        }
    }
} 