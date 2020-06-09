package com.bytedance.videoplayer;

import android.content.pm.ActivityInfo;
import android.content.res.Configuration;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.MediaController;
import android.widget.RelativeLayout;
import android.widget.VideoView;

public class MainActivity extends AppCompatActivity {
    //private View mPortraitPosition;
    private VideoView videoView;
    Button buttonPause;
    Button buttonStart;
    private int flag = 0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        buttonPause = findViewById(R.id.button_switch);
        videoView =  findViewById(R.id.videoView);
     //   mPortraitPosition = findViewById(R.id.matchview);
       // mVideolayout = findViewById(R.id.videoplay);
        MediaController  mediaController = new MediaController(MainActivity.this);
        //本地视频
        //String uri = "android.resource://" + getPackageName() + "/" + R.raw.big_buck_bunny;
        //网络视频 需要开通网络访问权限
        videoView.setMediaController(mediaController);
        mediaController.setMediaPlayer(videoView);
        // videoView.requestFocus();
        Uri uri = getIntent().getData();
//        Uri uri = Uri.parse(Environment.getExternalStorageDirectory().getPath()+"/Test_Movie.m4v");
        videoView.setVideoURI(uri);
//        videoView.setVideoURI(Uri.parse("android.resource://" +  getPackageName() + "/" + R.raw.test1));
//        videoView.start();
//        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_FULL_SENSOR);
        flag = 1;
        buttonPause.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(flag == 1) {
                    flag = 0;
                } else {
                    flag = 1;
                }
                setVideoViewPosition();
            }
        });
        buttonStart = findViewById(R.id.button_Start);
//        buttonStart.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                videoView.start();
//            }
//        });
        videoView.post(new Runnable() {
            @Override
            public void run() {
                init();
            }
        });
    }

    void init() {
        setVideoViewPosition();
        videoView.start();
    }

    private void setVideoViewPosition() {
//        switch (getResources().getConfiguration().orientation) {
//            case Configuration.ORIENTATION_FULL_SENSOR: {//横屏
               // mPortraitContent.setVisibility(View.GONE);
        if(flag == 1) {
            setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
            RelativeLayout.LayoutParams params = new RelativeLayout.LayoutParams(
                    RelativeLayout.LayoutParams.MATCH_PARENT, RelativeLayout.LayoutParams.MATCH_PARENT);
            params.addRule(RelativeLayout.CENTER_IN_PARENT);
            videoView.setLayoutParams(params);//设置VideoView的布局参数
    //        break;
        } else {

//            }
//            default: {//竖屏
               // mPortraitContent.setVisibility(View.VISIBLE);

                //获取白色背景的位置信息
                //mPortraitPosition.getLocationOnScreen(locationArry)
            setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
                RelativeLayout.LayoutParams params = new RelativeLayout.LayoutParams(
                        videoView.getWidth(), videoView.getHeight());
                params.leftMargin = 0;
                params.topMargin = 0;
                params.height = 1000;
                params.width = 2000;
                videoView.setLayoutParams(params);//设置VideoView的布局参数
               // break;
            }
   //     }
    }

    public void onConfigurationChanged(Configuration newConfig) {
        setVideoViewPosition();
        super.onConfigurationChanged(newConfig);
    }

}
