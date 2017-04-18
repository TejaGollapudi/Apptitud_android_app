package com.example.teja.apptitud;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.annotation.NonNull;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

/**
 * Created by teja on 09-04-2017.
 */


    public class SongFeedAdapter extends ArrayAdapter {
        private static final String TAG = "SongFeedAdapter";
        private final int layoutResource;
        private final LayoutInflater layoutInflater;
        private List<Songs> apps;

        @NonNull
        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            ViewHolder viewHolder;
            if(convertView==null){

                convertView=layoutInflater.inflate(layoutResource,parent,false);
                viewHolder =new ViewHolder(convertView);
                convertView.setTag(viewHolder) ;

            }
            else{
                viewHolder=(ViewHolder) convertView.getTag();
            }


           Songs currentnews=apps.get(position);
            int k=0;
            String s=" ";
            for(Album album:MainActivity.albumss)
            {
                if(album.getAlbumid()!=null && album.getAlbumid().equalsIgnoreCase(currentnews.getAlbumid()))
                {k=1;
                    s=album.getAlbumpath();
                break;
                }
            }
            if(k==1)
            {
                Bitmap bm= BitmapFactory.decodeFile(s);
                viewHolder.pic.setImageBitmap(bm);
                k=0;
            }

            viewHolder.name.setText(currentnews.getSongTitle());
           

            return convertView;
        }


        private class ViewHolder{
            final TextView name;
            final ImageView pic;


            ViewHolder (View v){
                this.name=(TextView) v.findViewById(R.id.song);
                this.pic=(ImageView) v.findViewById(R.id.imageView);


            }

        }



        @Override
        public int getCount() {
            return apps.size();
        }

        public SongFeedAdapter(Context context, int resource, List<Songs> apps) {
            super(context, resource);
            this.layoutResource=resource;
            this.layoutInflater=LayoutInflater.from(context);
            this.apps = apps;



        }
    }

