package com.example.carservice;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class car extends AppCompatActivity implements  DatePickerDialog.OnDateSetListener{
    EditText carModel, fuel, modelYear, engine, chasisNo,engineNumber, numberPlate, lastInsurance;

    FloatingActionButton floatingActionButton;
    ListView carList;
    DataBaseHelper db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_car);
        floatingActionButton=findViewById(R.id.fab);
        carList=findViewById(R.id.carList);
        db=new DataBaseHelper(getApplicationContext());

        floatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               addCar();
            }
        });

        carsList();
    }

    public void addCar(){
        setContentView(R.layout.addcar);

        ImageView saveCar, cancel;
        carModel=findViewById(R.id.model);
        fuel=findViewById(R.id.fuelType);
        modelYear=findViewById(R.id.modelYear);
        engine=findViewById(R.id.engine);
        chasisNo=findViewById(R.id.chasisNo);
        engineNumber=findViewById(R.id.engineNumber);
        numberPlate=findViewById(R.id.numberPlate);
        lastInsurance=findViewById(R.id.lastInsurance);
        saveCar=findViewById(R.id.saveCar);
        cancel=findViewById(R.id.cancel);
        ImageView cal=findViewById(R.id.cal);




        cal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getApplicationContext(), "Click", Toast.LENGTH_SHORT).show();
                datePicker();
            }
        });


        saveCar.setOnClickListener(new View.OnClickListener() {
             @Override
             public void onClick(View v) {
                 saveCar();

             }
         });

        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getApplicationContext(), "Canceled", Toast.LENGTH_SHORT).show();

            }
        });
    }
    public void datePicker(){
        Calendar cal=Calendar.getInstance();

        Date date=cal.getTime();
        SimpleDateFormat format=new SimpleDateFormat("yyyy");
        int year=Integer.valueOf(format.format(date));
        format=new SimpleDateFormat("MM");
        int month=Integer.valueOf(format.format(date));
        format=new SimpleDateFormat("dd");
        int day=Integer.valueOf(format.format(date));

        DatePickerDialog datePickerDialog=new DatePickerDialog(this,
                this, year,
                month,
                day
        );
        datePickerDialog.show();
    }


    @Override
    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
        String date=year+"/"+month+"/"+dayOfMonth;
        lastInsurance.setText(date);

    }
    public void saveCar(){
        String cm=carModel.getText().toString();
        String f=fuel.getText().toString();
        String my= modelYear.getText().toString();
        String eng=engine.getText().toString();
        String chas=chasisNo.getText().toString();
        String engNo=engineNumber.getText().toString();
        String nplate=numberPlate.getText().toString();
        String ins=lastInsurance.getText().toString();

        if(cm.equals("")){
            Toast.makeText(getApplicationContext(), "Enter car Name", Toast.LENGTH_SHORT).show();
        }
        try {
            Boolean insCar=db.saveCar(cm, f, Integer.parseInt(my),eng, chas, engNo, nplate, ins);
            if(insCar=true){
                Toast.makeText(getApplicationContext(), "Saved", Toast.LENGTH_SHORT).show();
                startActivity(new Intent(getApplicationContext(), car.class));
            }  else {
                Toast.makeText(getApplicationContext(), "Failed. Try again", Toast.LENGTH_SHORT).show();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }


                 /*AlertDialog.Builder builder=new AlertDialog.Builder(getApplicationContext());
                 builder
                         .setMessage("Do you want to save"+cm)
                         .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                             @Override
                             public void onClick(DialogInterface dialog, int which) {



                             }
                         })
                         .setNegativeButton("No", new DialogInterface.OnClickListener() {
                             @Override
                             public void onClick(DialogInterface dialog, int which) {
                                 return;
                             }
                         });
                 AlertDialog alert = builder.create();
                 alert.setTitle("Save Car??");
                 alert.show();*/



    }
    public void carsList(){
         db=new DataBaseHelper(getApplicationContext());

        final List<AvailableCars> list=db.myCars();
        final ArrayList<String> arrayList=new ArrayList();

        for(int i=0; i<list.size(); i++){
            arrayList.add(list.get(i).getCarId()+list.get(i).getCarModel());

        }
        ArrayAdapter arrayAdapter=new ArrayAdapter(getApplicationContext(), android.R.layout.simple_list_item_1, arrayList);
        carList.setAdapter(arrayAdapter);
    }



}