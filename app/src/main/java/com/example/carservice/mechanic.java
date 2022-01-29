package com.example.carservice;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.List;

public class mechanic extends AppCompatActivity {

  FloatingActionButton addMechanic;
  ListView mechanicsListView;
  DataBaseHelper db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mechanic);
        addMechanic=findViewById(R.id.addMechanic);
        mechanicsListView=findViewById(R.id.mechanicsList);
        db=new DataBaseHelper(getApplicationContext());

        MechanicsList();



    }
    public void addMechanic(View v){
        Intent intent=new Intent(getApplicationContext(), addMechanic.class);
        startActivity(intent);
    }

    public  void MechanicsList(){

        db=new DataBaseHelper(getApplicationContext());

        final List<myMechanics> list=db.myMechs();
        final ArrayList<String> arrayList=new ArrayList();

        for(int i=0; i<list.size(); i++){
            arrayList.add(list.get(i).getId()+list.get(i).getMechName());
        }
        ArrayAdapter arrayAdapter=new ArrayAdapter(getApplicationContext(), android.R.layout.simple_list_item_1, arrayList);
        mechanicsListView.setAdapter(arrayAdapter);

    }


}