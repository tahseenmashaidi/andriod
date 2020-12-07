package com.example.myapplication;

import android.app.Service;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.IBinder;


class MyService extends Service {
    MediaPlayer player;
    @Override
    public void onCreate(){
        player= MediaPlayer.create(this,R.raw.braincandy);
    }
    @Override
    public int onStartCommand(Intent intent,int flags,int startId){
        new Thread(new Runnable() {
            @Override
            public void run() {
                player.start();
            }
        }).start();
        return super.onStartCommand(intent,flags,startId);
    }
    @Override
    public void onDestroy(){
        player.stop();
    }
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }
}
