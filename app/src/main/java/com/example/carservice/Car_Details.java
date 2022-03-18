package com.example.carservice;


import androidx.appcompat.app.AppCompatActivity;

import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class Car_Details extends AppCompatActivity {
    DataBaseHelper db;
    EditText number_plate, carModel, modelYear, chassis_number, engine_number, insurance;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_car_details);
        db=new DataBaseHelper(getApplicationContext());
        number_plate=findViewById(R.id.number_plate);
        carModel=findViewById(R.id.car_model);
        modelYear=findViewById(R.id.model_year);
        chassis_number=findViewById(R.id.chassis_number);
        engine_number=findViewById(R.id.engine_number);
        insurance=findViewById(R.id.insurance);

        carSelected();
    }
    public void carSelected(){
        SelectedCarDetails selectedCarDetails=new SelectedCarDetails();
        number_plate.setText(selectedCarDetails.getNumberPlate());

    }

}