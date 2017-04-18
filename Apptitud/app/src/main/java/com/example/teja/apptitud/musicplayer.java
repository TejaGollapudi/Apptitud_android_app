package com.example.teja.apptitud;

import android.content.Intent;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;

public class musicplayer extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.d("Started","sfs");
        setContentView(R.layout.activity_musicplayer);
        MediaPlayer mediaPlayer = new MediaPlayer();
        mediaPlayer.setAudioStreamType(AudioManager.STREAM_MUSIC);
        String myUri= getIntent().getStringExtra("path");
       try {mediaPlayer.setDataSource(getApplicationContext(), Uri.parse(myUri));
        mediaPlayer.prepare();}
        catch(Exception e)
        {
            //no file existing
            Log.d("path not existing","file path error");
        }
        mediaPlayer.start();



    }

}
