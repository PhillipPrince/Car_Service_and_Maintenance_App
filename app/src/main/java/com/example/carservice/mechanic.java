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

        ArrayList<String> mechanics=new ArrayList<>();
        mechanics.add("Mechanics List");
        String sql="SELECT name, phone FROM Mechanics";
        Cursor cursor=db.sQLiteDatabase.rawQuery(sql, null);
        cursor.moveToFirst();
        while (cursor.moveToNext()){
            mechanics.add(cursor.toString());
        }


         ArrayAdapter grpAdapter=new ArrayAdapter(getApplicationContext(),android.R.layout.simple_list_item_1, mechanics);
        mechanicsListView.setAdapter(grpAdapter);
        mechanicsListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

            }
        });



    }


}