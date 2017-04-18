package com.example.teja.apptitud;

/**
 * Created by teja on 09-04-2017.
 */

    public class Songs
    {
        private long mSongID;
        private String mSongTitle;
        private String mSongPath;
        private String albumid;

        public String getAlbumid() {
            return albumid;
        }

        public Songs(long id, String title, String Path, String al){
            mSongID = id;
            mSongTitle = title;
            mSongPath= Path;
            albumid=al;
        }

        public long getSongID(){
            return mSongID;
        }

        public String getSongTitle(){
            return mSongTitle;
        }

        @Override
        public String toString() {
            return "Songs{" +
                    "mSongID" + mSongID +
                    ", mSongTitle='" + mSongTitle + '\'' +
                    "msongPAth="+mSongPath+"malbum"+albumid+
                    '}';
        }


        public String getmSongPath() {
            return mSongPath;
        }
    }


