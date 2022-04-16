package com.example.carservice;

import android.app.DatePickerDialog;
import android.media.Image;
import android.os.Bundle;
import android.util.SparseIntArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class SelectCar extends Fragment implements DatePickerDialog.OnDateSetListener {


    DataBaseHelper db;

TextView numberPlate, mileage, serviceDate;
Button startService;
Spinner selectCar;
ImageView sdate;

    @Nullable
    @Override
    public View onCreateView( LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view= inflater.inflate(R.layout.select_car, container, false);
        db=new DataBaseHelper(getContext());
        selectCar=view.findViewById(R.id.select_car);
        startService=view.findViewById(R.id.startService);
        numberPlate=view.findViewById(R.id.number_plate);
        mileage=view.findViewById(R.id.mileage);
        serviceDate=view.findViewById(R.id.serviceDate);
        sdate=view.findViewById(R.id.sdate);


        sdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                datePicker();
            }
        });


        carList();

        return view;
    }

    public void carList(){


        final List<AvailableCars> list=db.myCars();
        final ArrayList<String> arrayList=new ArrayList();
        //String[] arrayList=new String[]{"Select car"};

        for(int i=0; i<list.size(); i++){
           arrayList.add(list.get(i).getCarId()+list.get(i).getCarModel());
            //arrayList=new String[]{list.get(i).getCarModel()};
        }
        ArrayAdapter arrayAdapter=new ArrayAdapter(getContext(), android.R.layout.simple_spinner_item, arrayList);
        arrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        selectCar.setAdapter(arrayAdapter);
        selectCar.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
               numberPlate.setText(list.get(position).getNumberPlate());
               String mile= String.valueOf(list.get(position).getMileage());
               mileage.setText(mile);

            }
            @Override
            public void onNothingSelected(AdapterView<?> parent) {

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

        DatePickerDialog datePickerDialog=new DatePickerDialog(getContext(),
                this, year,
                month,
                day
        );
        datePickerDialog.show();
    }


    @Override
    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
        String date=year+"/"+month+"/"+dayOfMonth;
        serviceDate.setText(date);
    }
}
