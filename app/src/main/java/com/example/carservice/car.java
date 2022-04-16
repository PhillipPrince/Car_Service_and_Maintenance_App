package com.example.carservice;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.icu.text.DateFormat;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import org.json.JSONException;
import org.json.JSONObject;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class car extends AppCompatActivity implements  DatePickerDialog.OnDateSetListener{
    EditText carModel, fuel, modelYear, engine, chasisNo,engineNumber, numberPlate, lastInsurance, nextInsurance, nextService, mileage;

    FloatingActionButton floatingActionButton;
    ListView carList;
    DataBaseHelper db;
    TextView linsurance, nInsurance;
    String dateselected=null;

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
        nextInsurance=findViewById(R.id.nextInsurance);
        nextService=findViewById(R.id.nextService);
        saveCar=findViewById(R.id.saveCar);
        cancel=findViewById(R.id.cancel);
        linsurance=findViewById(R.id.cal);
        nInsurance=findViewById(R.id.ncal);
        mileage=findViewById(R.id.mile);




        linsurance.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dateselected="linsurance";
                datePicker();
            }
        });
        nInsurance.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dateselected="nInsurance";
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



        if (dateselected.equals("linsurance")) {
            lastInsurance.setText(date);
        }else if(dateselected.equals("nInsurance")){
           nextInsurance.setText(date);
        }


    }
    public void saveCar(){
        String cm=carModel.getText().toString();
        String f=fuel.getText().toString();
        String my= modelYear.getText().toString();
        String eng=engine.getText().toString();
        String chas=chasisNo.getText().toString();
        String engNo=engineNumber.getText().toString();
        String nplate=numberPlate.getText().toString();
        String lins=lastInsurance.getText().toString();
        int mile= Integer.parseInt(mileage.getText().toString());
        String ninsurance=nextInsurance.getText().toString();
       // String nservice=nextService.getText().toString();


        if(cm.equals("")){
            Toast.makeText(getApplicationContext(), "Enter car Name", Toast.LENGTH_SHORT).show();
        }
        try {
            Boolean insCar=db.saveCar(cm, f, Integer.parseInt(my),eng, chas, engNo, nplate, mile, lins, ninsurance);
            if(insCar==true){
                Toast.makeText(getApplicationContext(), "Saved", Toast.LENGTH_SHORT).show();
                //newCar(cm,nplate, mile, nservice, ninsurance );
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
    public void newCar(String carModel, String numberPlate, String mileage, String nextService, String insuranceExpiry){

        final String type = "saveCar";


        //noinspection deprecation
        new android.os.AsyncTask<Void, Void, String>() {
            protected String doInBackground(Void[] params) {
                String response = "";
                try {

                    String[] strings = new String[5];
                    strings[0] = type;
                    strings[1] = carModel;
                    strings[2] = numberPlate;
                    strings[3] = mileage;
                    strings[4] = nextService;
                    strings[5]= String.valueOf(insuranceExpiry);

                    HttpProcesses httpProcesses = new HttpProcesses();
                    response = httpProcesses.sendRequest(strings);
                } catch (Exception e) {
                    response = e.getMessage();
                }
                return response;
            }
            protected void onPostExecute(String response) {

                try {
                    JSONObject jsonObject = new JSONObject(response);
                    Boolean result = jsonObject.getBoolean("status");
                    //  JSONArray      jsonArray = jsonObject.optJSONArray("data");
                    String message = jsonObject.getString("message");
                    if (result == true) {
                        // String names=jsonArray.getString(jsonObject.getInt("name "));

                        Toast.makeText(getApplicationContext(), message, Toast.LENGTH_SHORT).show();

                    } else {
                        Toast.makeText(getApplicationContext(),message , Toast.LENGTH_SHORT).show();
                    }
                } catch (JSONException e) {
                    e.printStackTrace();




                }
            }
        }.execute();

    }

    public void carsList(){
         db=new DataBaseHelper(getApplicationContext());

        final List<AvailableCars> list=db.myCars();
        final ArrayList<String> arrayList=new ArrayList();

        for(int i=0; i<list.size(); i++){
            arrayList.add(list.get(i).getNumberPlate()+"   "+list.get(i).getCarModel());

        }
        ArrayAdapter arrayAdapter=new ArrayAdapter(getApplicationContext(), android.R.layout.simple_list_item_1, arrayList);
        carList.setAdapter(arrayAdapter);
        carList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
               startActivity(new Intent(getApplicationContext(), Car_Details.class));
            }
        });
    }



}