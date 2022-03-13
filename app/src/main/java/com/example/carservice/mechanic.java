package com.example.carservice;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.List;

public class mechanic extends AppCompatActivity {


    TextView mechanicMode;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mechanic);
        mechanicMode=findViewById(R.id.mechanicMode);

    }
    public void mechanicMode(View view) {
        mechanicMode.setText("Switch to User Mode");
    }
}