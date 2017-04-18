package com.example.teja.apptitud;

/**
 * Created by teja on 10-04-2017.
 */

public class Album {
    private String albumid;
    private String albumpath;

    public String getAlbumid() {
        return albumid;
    }

    public String getAlbumpath() {
        return albumpath;
    }

    public Album(String albumpath, String albumid) {

        this.albumpath = albumpath;
        this.albumid = albumid;
    }

    @Override
    public String toString() {
        return "Album{" +
                "albumid=" + albumid +
                ", albumpath='" + albumpath + '\'' +
                '}';
    }
}
