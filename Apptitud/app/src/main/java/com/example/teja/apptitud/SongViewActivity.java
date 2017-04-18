package com.example.teja.apptitud;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class SongViewActivity extends AppCompatActivity {
    ListView listView;


    int count = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.content_song_view);
        listView = (ListView) findViewById(R.id.songlistview);
        ArrayList<Songs> arrayList = MainActivity.getSongList();
        Log.d("arraylist", arrayList.toString());
        SongFeedAdapter feedAdaptered = new SongFeedAdapter(SongViewActivity.this, R.layout.song_listener, arrayList);
        Log.d("D", feedAdaptered.toString());
        listView.setAdapter(feedAdaptered);

        Log.d("songactivity", "songactivity eeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeee");

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position,
                                    long id) {

                //String item= MainActivity.arrayList.get(position).getmSongPath();
                // Intent in = new Intent(getApplicationContext(),musicplayer.class);
                // in.putExtra("path", item);
                // if(count>0)
                // {finish();}
                // count++;
                // startActivity(in);
                MainActivity.musicSrv.setIndex(position);
                MainActivity.musicSrv.playSong();
                //Toast.makeText(getBaseContext(), item, Toast.LENGTH_LONG).show();

            }
        });
    }
}

