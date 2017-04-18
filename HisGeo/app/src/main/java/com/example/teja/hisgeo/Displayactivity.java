package com.example.teja.hisgeo;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

public class Displayactivity extends AppCompatActivity {

    Spinner ss;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.content_displayactivity);
        ss=(Spinner) findViewById(R.id.spinner);
        String s=getIntent().getStringExtra("river");
        String [] arr=s.split(",");
        ArrayAdapter<String> a=new ArrayAdapter<String>(this, android.R.layout.simple_spinner_dropdown_item,arr);
        ss.setAdapter(a);

            }

    }


