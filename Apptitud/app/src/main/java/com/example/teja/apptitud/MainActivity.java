package com.example.teja.apptitud;

import android.app.FragmentTransaction;
import android.app.PendingIntent;
import android.content.ComponentName;
import android.content.ContentResolver;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.database.Cursor;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.hardware.TriggerEvent;
import android.hardware.TriggerEventListener;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Environment;
import android.os.IBinder;
import android.provider.MediaStore;
//import android.support.v4.app.Fragment;
import android.app.Fragment;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.NavigationView;
import android.support.design.widget.Snackbar;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.location.ActivityRecognition;

import java.io.File;
import java.io.FilenameFilter;
import java.util.ArrayList;

import static java.lang.Math.pow;

public class MainActivity extends AppCompatActivity implements SensorEventListener,GoogleApiClient.ConnectionCallbacks, GoogleApiClient.OnConnectionFailedListener , NavigationView.OnNavigationItemSelectedListener{
private TextView textview;
    ListView listView;
    Button button;
    Button pause;
    Button resume;
    FloatingActionButton fab;
      static MusicService musicSrv;
    static  Intent playIntent;
    static float steps;
    Intent intent;
    private static boolean musicBound = false;
   static ArrayList<Songs> arrayList = new ArrayList<Songs>();
    static ArrayList<Album> albumss=new ArrayList<Album>();
    static int ok=1;
    private SensorManager sensorManager;
    boolean activityRunning;
    public GoogleApiClient mApiClient;
    static TextView tv;
    TextView t;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
       Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

         fab = (FloatingActionButton) findViewById(R.id.fab);
       fab.setImageResource(R.drawable.ic_media_play);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               String tog= musicSrv.toggle();
                if(tog.equalsIgnoreCase("paused"))
                {
                    fab.setImageResource(R.drawable.ic_media_play);
                }
                else
                {
                    fab.setImageResource(R.drawable.ic_media_pause);
                }
                Snackbar.make(view, tog, Snackbar.LENGTH_SHORT)
                        .setAction("Action", null).show();
            }
        });

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        SongList songlist =new SongList();
        songlist.execute("ds");
        listView =(ListView) findViewById(R.id.songlistview);
      //  button=(Button) findViewById(R.id.button);
        //Button pd=(Button) findViewById(R.id.button4);

        t=(TextView)findViewById(R.id.pedodisplay);

        pause=(Button) findViewById(R.id.button3);
        resume=(Button) findViewById(R.id.button2);

        Fragment fragment= new BlankFragment();
        FragmentTransaction transaction = getFragmentManager().beginTransaction();
        transaction.replace(R.id.frame,fragment);
        transaction.addToBackStack(null);
        transaction.commit();



// sensorManager = (SensorManager) getSystemService(Context.SENSOR_SERVICE);
   //     sensorManager.registerListener(this, sensorManager.getDefaultSensor(Sensor.TYPE_STEP_COUNTER), SensorManager.SENSOR_DELAY_NORMAL);

     //   sensorManager = (SensorManager) getSystemService(Context.SENSOR_SERVICE);
       // Sensor mSensor= sensorManager.getDefaultSensor(Sensor.TYPE_STEP_COUNTER);
        //sensorManager.registerListener(this, mSensor, SensorManager.SENSOR_DELAY_NORMAL);

       // TriggerEventListener triggerEventListener = new TriggerEventListener() {
         //   @Override
           // public void onTrigger(TriggerEvent event) {
                // Do work
            //}
        //};
        //sensorManager.requestTriggerSensor(triggerEventListener, mSensor);
     /*   pd.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                Fragment fragment= new PedoFragment();
                FragmentTransaction transaction = getFragmentManager().beginTransaction();
                transaction.replace(R.id.activity_main,fragment);
                transaction.addToBackStack(null);
                transaction.commit();


            }
        });

        button.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
              //  Intent in = new Intent(getApplicationContext(),SongViewActivity.class);
              //  startActivity(in);
                Fragment fragment= new ItemFragment();
                FragmentTransaction transaction = getFragmentManager().beginTransaction();
                transaction.replace(R.id.activity_main,fragment);
                transaction.addToBackStack(null);
                transaction.commit();
            }
        });

        */
/*      pause.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                musicSrv.pause();
            }

        });
        */
        /*resume.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                musicSrv.restart();
            }

        });
*/

      mApiClient = new GoogleApiClient.Builder(this)
                .addApi(ActivityRecognition.API)
                .addConnectionCallbacks(this)
                .addOnConnectionFailedListener(this)
                .build();


       mApiClient.connect();

    }

    @Override
    public void onSensorChanged(SensorEvent event) {

        }



    @Override
    public void onAccuracyChanged(Sensor sensor, int i) {

    }
    @Override
    public void onConnected(@Nullable Bundle bundle) {
      intent = new Intent( this, ActivityRecognisedService.class );
       PendingIntent pendingIntent = PendingIntent.getService( this, 0, intent, PendingIntent.FLAG_UPDATE_CURRENT );
        ActivityRecognition.ActivityRecognitionApi.requestActivityUpdates( mApiClient, 0, pendingIntent );

    }




    @Override
    public void onConnectionSuspended(int i) {

    }

    @Override
    public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {

    }

    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.pedometer) {
            Fragment fragment= new PedoFragment();
            FragmentTransaction transaction = getFragmentManager().beginTransaction();
            transaction.replace(R.id.frame,fragment);
            transaction.addToBackStack(null);
            transaction.commit();


            // Handle the camera action
        } else if (id == R.id.music) {
            Fragment fragment= new ItemFragment();
            FragmentTransaction transaction = getFragmentManager().beginTransaction();
            transaction.replace(R.id.frame,fragment);
            transaction.addToBackStack(null);
            transaction.commit();



        } else if (id == R.id.home) {
            Fragment fragment= new BlankFragment();
            FragmentTransaction transaction = getFragmentManager().beginTransaction();
            transaction.replace(R.id.frame,fragment);
            transaction.addToBackStack(null);
            transaction.commit();


        }
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }


    class SongList extends AsyncTask<String,Void,String> {
              String names=" ";

            @Override
            protected String doInBackground(String... strings) {

                ContentResolver contentResolver = getContentResolver();
                Uri songUri = MediaStore.Audio.Media.EXTERNAL_CONTENT_URI;
                Cursor songCursor = contentResolver.query(songUri, null, null, null, null);

                if (songCursor != null && songCursor.moveToFirst()) {
                    int songId = songCursor.getColumnIndex(MediaStore.Audio.Media._ID);
                    int songTitle = songCursor.getColumnIndex(MediaStore.Audio.Media.TITLE);
                    int songPath = songCursor.getColumnIndex(MediaStore.Audio.Media.DATA);
                    int albumid=songCursor.getColumnIndex(MediaStore.Audio.Media.ALBUM);

                    do {
                        long currentId = songCursor.getLong(songId);
                        String currentTitle = songCursor.getString(songTitle);
                        String path = songCursor.getString(songPath);
                        String album=songCursor.getString(albumid);
                        if (path.substring(path.length() - 3).equalsIgnoreCase("mp3"))
                            arrayList.add(new Songs(currentId, currentTitle, path,album));
                        names=names+ currentTitle+" ";
                    } while (songCursor.moveToNext());
                }

                Uri albumUri= MediaStore.Audio.Albums.EXTERNAL_CONTENT_URI;
                Cursor albumCursor = contentResolver.query(albumUri, null, null, null, null);

                if (albumCursor != null && albumCursor.moveToFirst()) {
                    int AlbumId = albumCursor.getColumnIndex(MediaStore.Audio.Albums.ALBUM);
                    int AlbumPic=albumCursor.getColumnIndex(MediaStore.Audio.Albums.ALBUM_ART);
        //            Log.d("error", "doInBackground: "+AlbumId+AlbumPic);

                    do {
                        String currentId = albumCursor.getString(AlbumId);
                        String currentPic = albumCursor.getString(AlbumPic);
                        albumss.add(new Album(currentPic,currentId));
                        Log.d("album",currentPic+"alccx"+currentId);
                    } while (albumCursor.moveToNext());
                }




                int i;
                for (i = 0; i < arrayList.size(); i++) {
                    Log.d("songs", arrayList.get(i).toString());
                }
                return "done";
            }



              protected void onPostExecute(String s) {
                  super.onPostExecute(s);

                 // SongFeedAdapter feedAdaptered=new SongFeedAdapter(MainActivity.this,R.layout.song_listener,arrayList);

                  //listView.setAdapter(feedAdaptered);

              }


        }

    public static ArrayList<Songs> getSongList(){

        return arrayList;
    }



            /*try {

                // create new file
                f = new File("/storage");

                // returns pathnames for files and directory
                paths = f.listFiles();
                if(f.listFiles()==null)
                    Log.d("null files","bukk");
               for(File path:paths)
               {
                   Log.d("sds",path.getName());
               }

                // for each pathname in pathname array

                    // prints file and directory paths
                    Log.d("list files to string", "onCreate: "+ f.listFiles());
                Log.wtf("sdfsf", Environment.getRootDirectory().toString());
                Log.wtf("dsg",Environment.getExternalStorageDirectory().toString());


            } catch(Exception e) {

                // if any error occurs
                Log.wtf("sfs",e.toString());
            }*/



/*class FileExtensionFilter implements FilenameFilter {
    public boolean accept(File dir, String name) {
        return (name.endsWith(".mp3") || name.endsWith(".MP3"));
    }
}
*/
private ServiceConnection musicConnection = new ServiceConnection(){

    @Override
    public void onServiceConnected(ComponentName name, IBinder service) {
        MusicService.MusicBinder binder = (MusicService.MusicBinder)service;
        //get service
        musicSrv = binder.getService();
        //pass list
        musicSrv.getSongList();
        musicBound = true;
    }

    @Override
    public void onServiceDisconnected(ComponentName name) {
        musicBound = false;
    }
};
    @Override
    protected void onStart() {
        super.onStart();
        if(playIntent==null){
            playIntent = new Intent(this, MusicService.class);
            bindService(playIntent, musicConnection, Context.BIND_AUTO_CREATE);
            startService(playIntent);
        }
    }
    public static void settext(String s){
        tv.setText("s");
    }
    protected void onResume(){
        super.onStart();
        if(playIntent==null){
            playIntent = new Intent(this, MusicService.class);
            bindService(playIntent, musicConnection, Context.BIND_AUTO_CREATE);
            startService(playIntent);

        }

    }
    protected void onStop(){
        super.onStop();
        //  musicSrv.Stop();
     //   unbindService(musicConnection);
        //  this.onDestroy();
     //   stopService(playIntent);
        Log.d("MAIN ACTIVITY","stopped Main Activity");
       // intent.setAction("stop");
  //    stopService(intent);

   //     sensorManager.unregisterListener(this);
    }

    protected void onDestroy(){
        super.onDestroy();
        Log.d("MAinActivirt","Destroyed");
      //  intent.setAction("stop");
    //    stopService(intent);
       stopService(playIntent);

    }
}



