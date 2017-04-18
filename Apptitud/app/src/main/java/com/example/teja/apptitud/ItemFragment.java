package com.example.teja.apptitud;

import android.content.Context;
import android.os.Bundle;
import android.app.Fragment;
import android.support.v4.app.ListFragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import com.example.teja.apptitud.dummy.DummyContent;
import com.example.teja.apptitud.dummy.DummyContent.DummyItem;

import java.util.List;


public class ItemFragment extends Fragment {


    public ItemFragment() {
    }



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        //This layout contains your list view
        View view = inflater.inflate(R.layout.content_song_view, container, false);

        //now you must initialize your list view
        ListView listview =(ListView)view.findViewById(R.id.songlistview);
        Log.e("ss",listview.toString());

      SongFeedAdapter adapter =
                new SongFeedAdapter(getActivity(), R.layout.song_listener, MainActivity.arrayList);

       listview.setAdapter(adapter);

        listview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
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

        //To have custom list view use this : you must define CustomeAdapter class
        // listview.setadapter(new CustomeAdapter(getActivity()));
        //getActivty is used instead of Context
        return view;
    }


}
