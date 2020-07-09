package com.example.chapter3.homework;

import android.animation.ObjectAnimator;
import android.os.Bundle;
//import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.SeekBar;

import androidx.appcompat.app.AppCompatActivity;

import com.airbnb.lottie.LottieAnimationView;

public class Ch3Ex1Activity extends AppCompatActivity {
    private LottieAnimationView animationView;
    private CheckBox loopCheckBox;
    private SeekBar seekBar;
    private ObjectAnimator animator3;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ch3ex1);

        animationView = findViewById(R.id.animation_view);
        loopCheckBox = findViewById(R.id.loop_checkbox);
        seekBar = findViewById(R.id.seekbar);
        animator3=ObjectAnimator.ofInt(seekBar,"Progress",0,100);
        animator3.setDuration(5000);
//        animator3.setRepeatMode(ObjectAnimator.);
//        animator3.setRepeatCount(1);
        animator3.start();
        loopCheckBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    // 当选中自动播放的时候，开始播放 lottie 动画，同时禁止手动修改进度
                    animationView.playAnimation();
                    seekBar.setEnabled(false);
                    animator3.resume();
                } else {
                    // 当去除自动播放时，停止播放 lottie 动画，同时允许手动修改进度
                    animationView.pauseAnimation();
                    seekBar.setEnabled(true);
                    animator3.pause();
                }
            }
        });

        seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                // TODO ex1-2: 这里应该调用哪个函数呢
                // 提示1：可以参考 https://airbnb.io/lottie/#/android?id=custom-animators
                // 提示2：SeekBar 的文档可以把鼠标放在 OnProgressChanged 中间，并点击 F1 查看，
                // 或者到官网查询 https://developer.android.google.cn/reference/android/widget/SeekBar.OnSeekBarChangeListener.html#onProgressChanged(android.widget.SeekBar,%20int,%20boolean
//                seekBar.setProgress(progress);
                if(fromUser){
                    Log.i("seekbar","before"+animator3.getAnimatedValue());
                    seekBar.setProgress(progress);
                    animator3.setCurrentFraction(progress/100f);
                    Log.i("SEEKBAR","progress="+animator3.getAnimatedValue("Progress")+", actual: "+progress);
                }
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {
            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
            }
        });
    }
}
