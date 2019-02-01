package com.lzr.services;

import android.app.Service;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.IBinder;
import android.util.Log;

import com.lzr.global.GlobalMVTool;

import java.io.IOException;

public class VideoService extends Service {

    private MediaPlayer mMediaPlayer;

    public VideoService() {
    }

    @Override
    public void onCreate() {
        super.onCreate();
        mMediaPlayer = new MediaPlayer();
        if (GlobalMVTool.getInstance().getGlobalTriggerListener("VideoService")!=null) {
            GlobalMVTool.getInstance().getGlobalTriggerListener("VideoService").trigger(VideoService.class, "进入服务成功,启动onCreate()");
        }

    }

    @Override
    public IBinder onBind(Intent intent) {
        initMediaPlay(intent);
        // TODO: Return the communication channel to the service.
        throw new UnsupportedOperationException("Not yet implemented");
    }


    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        initMediaPlay(intent);
        return super.onStartCommand(intent, flags, startId);
    }

    private void initMediaPlay(Intent intent){
        String url = intent.getStringExtra("url");
        if (GlobalMVTool.getInstance().getGlobalTriggerListener("VideoService")!=null){
            GlobalMVTool.getInstance().getGlobalTriggerListener("VideoService").trigger(VideoService.class,"进入服务成功 onStartCommand()");

        }
        if (mMediaPlayer != null) {
            if (mMediaPlayer.isPlaying()) {

            } else {
                try {
                    mMediaPlayer.reset();
                    mMediaPlayer.setDataSource(url);
                    mMediaPlayer.setLooping(true);
                    mMediaPlayer.prepare();
                    mMediaPlayer.start();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }


    @Override
    public void onDestroy() {
        Log.e("VideoService","服务死亡");
        super.onDestroy();
    }
}
