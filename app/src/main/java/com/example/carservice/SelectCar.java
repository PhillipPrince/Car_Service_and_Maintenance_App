package com.example.carservice;

import android.os.Bundle;
import android.util.SparseIntArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import java.util.ArrayList;
import java.util.List;

public class SelectCar extends Fragment {


    DataBaseHelper db;

TextView textView;
Button button;
Spinner selectCar;
    @Nullable
    @Override
    public View onCreateView( LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view= inflater.inflate(R.layout.select_car, container, false);
        db=new DataBaseHelper(getContext());
        selectCar=view.findViewById(R.id.select_car);


        selectCar();

        return view;
    }

    public void action(){

    }

    public void selectCar(){


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
        selectCar.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
            selectCar.getSelectedItem();
            carDetails.INSTANCE.setCarId(list.get(position).getCarId());
                
            }
        });

    }



}
