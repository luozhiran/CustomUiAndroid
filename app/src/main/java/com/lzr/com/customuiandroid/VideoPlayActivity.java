package com.lzr.com.customuiandroid;

import android.content.Intent;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.VideoView;

import com.lzr.global.GlobalMVTool;
import com.lzr.global.GlobalTriggerListener;
import com.lzr.services.VideoService;

public class VideoPlayActivity extends AppCompatActivity implements View.OnClickListener {

    private VideoView mVideo;
    private Button mStart;
    private Button mScreenOff;
    private Button mBtnService;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_video_play);
        initView();
    }

    private void initView() {
        mVideo = (VideoView) findViewById(R.id.video);
        mStart = (Button) findViewById(R.id.start);
        mStart.setOnClickListener(this);
        mScreenOff = (Button) findViewById(R.id.screenOff);
        mScreenOff.setOnClickListener(this);
        mBtnService = (Button) findViewById(R.id.service_btn);
        mBtnService.setOnClickListener(this);
        mVideo.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
            @Override
            public void onPrepared(MediaPlayer mp) {
                mp.setLooping(true);
            }
        });
        GlobalMVTool.getInstance().addTriggerListener("VideoService", new GlobalTriggerListener<String>() {
            @Override
            public void trigger(Class c, String var1) {
                Log.e("VideoPlayActivity", c.getName()+"  "+var1);
            }

        });
    }

    @Override
    protected void onDestroy() {
        GlobalMVTool.getInstance().removeTriggerListner("VideoService");
        super.onDestroy();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.start:
                mVideo.setVideoURI(Uri.parse("http://res.yuanqutech.com/61mp4/CH/185.mp4"));
                mVideo.start();
                break;
            case R.id.screenOff:// TODO 19/01/16
                String ACTION_SCREEN_OFF = "hlzt.intent.action.SET_SCREEN_OFF_ACTION";
                Intent mScreenOffIntent = new Intent(ACTION_SCREEN_OFF);
                sendBroadcast(mScreenOffIntent);
                break;
            case R.id.service_btn:// TODO 19/01/28
                Intent intent = new Intent(this, VideoService.class);
                intent.putExtra("url", "http://res.yuanqutech.com/61mp4/CH/185.mp4");
                startService(intent);
                break;
            default:
                break;
        }
    }
}
