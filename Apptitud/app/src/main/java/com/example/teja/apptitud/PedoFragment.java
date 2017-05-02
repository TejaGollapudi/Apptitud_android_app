package com.example.teja.apptitud;

import android.app.Activity;
import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
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
import android.widget.TextView;

import com.example.teja.apptitud.dummy.DummyContent;
import com.example.teja.apptitud.dummy.DummyContent.DummyItem;

import java.util.List;


public class PedoFragment extends Fragment  implements SensorEventListener{

TextView pd;
    int i=0;
    public PedoFragment() {


    }

public void onCreate(){
 //   pd=(TextView) getActivity().findViewById(R.id.pedodisplay);

}

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        i=1;
        // Inflate the layout for this fragment

        View view = inflater.inflate(R.layout.pedolayout, container, false);
        pd =(TextView)view.findViewById(R.id.pedodisplay);
      //  pd.setText(Float.toString(MainActivity.steps));

       SensorManager sensorManager = (SensorManager) getActivity().getSystemService(Context.SENSOR_SERVICE);
        Sensor mSensor= sensorManager.getDefaultSensor(Sensor.TYPE_STEP_COUNTER);
        sensorManager.registerListener(this, mSensor, SensorManager.SENSOR_DELAY_NORMAL);

        //To have custom list view use this : you must define CustomeAdapter class
        // listview.setadapter(new CustomeAdapter(getActivity()));
        //getActivty is used instead of Context
        return view;
    }


    @Override
    public void onSensorChanged(SensorEvent sensorEvent) {

            pd.setText(Float.toString(sensorEvent.values[0]));


    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int i) {

    }
}