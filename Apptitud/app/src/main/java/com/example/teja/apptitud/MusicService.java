package com.example.teja.apptitud;

import android.app.Service;
import android.content.ContentUris;
import android.content.Intent;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Binder;
import android.os.IBinder;
import android.os.PowerManager;
import android.util.Log;

import java.util.ArrayList;

public class MusicService extends Service implements
        MediaPlayer.OnPreparedListener, MediaPlayer.OnErrorListener,
        MediaPlayer.OnCompletionListener {
    private MediaPlayer player;
    private ArrayList<Songs> songs;
    private int index;
    private final IBinder musicBind = new MusicBinder();
    public static int length;
    int i=0;

    public MusicService() {
    }
    public void onCreate(){
        super.onCreate();
        index=0;
        length=0;
  //      player = new MediaPlayer();
   //     initMusicPlayer();
        Log.d("musicservice","oncreate called wwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwww");
    }
    public int onStartCommand(Intent intent, int flags, int startId) {
        player=new MediaPlayer();
        initMusicPlayer();
        // We want this service to continue running until it is explicitly
        // stopped, so return sticky.
        return START_STICKY;
    }

    public void initMusicPlayer(){
        player.setWakeMode(getApplicationContext(),
                PowerManager.PARTIAL_WAKE_LOCK);
        player.setAudioStreamType(AudioManager.STREAM_MUSIC);
        player.setOnPreparedListener(this);
        player.setOnCompletionListener(this);
        player.setOnErrorListener(this);
    }
public void getSongList(){
songs=MainActivity.getSongList();
}
    public class MusicBinder extends Binder {
        MusicService getService() {
            return MusicService.this;
        }
    }

    @Override
    public void onCompletion(MediaPlayer mediaPlayer) {

    }

    @Override
    public boolean onError(MediaPlayer mediaPlayer, int i, int i1) {
        return false;
    }

    @Override
    public IBinder onBind(Intent intent) {
        return musicBind;
    }
    @Override
    public boolean onUnbind(Intent intent){
        player.stop();
        player.release();
    //    this.onDestroy();
        return false;

    }
    public void setIndex(int i){
        index=i;
    }

    public void playSong(){

       player.reset();

        try{
            player.setDataSource(getApplicationContext(), Uri.parse(songs.get(index).getmSongPath()));
        }
        catch(Exception e){
            Log.e("error in reading file", e.toString());
        }

        player.prepareAsync();

        }

    @Override
    public void onPrepared(MediaPlayer mp) {
        //start playback
        mp.start();


    }
    public void pause(){
        player.pause();
        length=player.getCurrentPosition();
        i=1;

    }
    public void restart(){
        if(i==1) {
            player.seekTo(length);
            player.start();
            i=0;
        }}

    @Override
    public void onDestroy(){
        super.onDestroy();
    }

   // public void Stop(){
    //    player.stop();
   //     player.release();

 //   }

}
