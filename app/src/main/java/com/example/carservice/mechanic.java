package com.example.carservice;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.List;

public class mechanic extends AppCompatActivity {


    Button mechanicMode;
    EditText mechname, mechPhone, mechLocation;
    DataBaseHelper db;
    ImageView saveMech;
    ListView mechList;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mechanic);
        mechanicMode=findViewById(R.id.mechanicMode);
        saveMech=findViewById(R.id.saveMechanic);
        mechList=findViewById(R.id.mechanicsList);
        db=new DataBaseHelper(getApplicationContext());

        mechanics();


    }

    private void mechanics() {
        final List<myMechanics> list=db.myMechs();
        final ArrayList<String> arrayList=new ArrayList();

        for(int i=0; i<list.size(); i++){
            arrayList.add(list.get(i).getMechName()+"   "+list.get(i).getMechPhone());

        }
        ArrayAdapter arrayAdapter=new ArrayAdapter(getApplicationContext(), android.R.layout.simple_list_item_1, arrayList);
        mechList.setAdapter(arrayAdapter);
    }

    public void mechanicMode(View view) {
        setContentView(R.layout.activity_add_mechanic);
        mechname=findViewById(R.id.mechName);
        mechPhone=findViewById(R.id.mechPhone);
        mechLocation=findViewById(R.id.mechLocation);



    }

    public void saveMechDets(View view) {
        String nam=mechname.getText().toString();
        int phon= Integer.parseInt(mechPhone.getText().toString());
        String loc=mechLocation.getText().toString();

        if(nam.equals("")||phon==0 ||loc.equals("")){
            Toast.makeText(getApplicationContext(), "Enter All Fields", Toast.LENGTH_SHORT).show();
        }else {
            Boolean saveMechanic=db.addMechanic(nam, phon, loc);
            if(saveMechanic==true){
                Toast.makeText(getApplicationContext(), "Saved", Toast.LENGTH_SHORT).show();
            }else{
                Toast.makeText(getApplicationContext(), "Retry", Toast.LENGTH_LONG).show();
            }

        }
    }
}