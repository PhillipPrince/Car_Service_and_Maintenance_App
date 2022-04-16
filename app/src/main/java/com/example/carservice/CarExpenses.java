package com.example.carservice;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public  class CarExpenses extends AppCompatActivity implements DatePickerDialog.OnDateSetListener {
    FloatingActionButton addExpense;
    EditText expenseDate;
    DataBaseHelper db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_car_expense);
        addExpense=findViewById(R.id.addExpense);
        db=new DataBaseHelper(getApplicationContext());
        addExpense.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ;
                addCarExpense();
            }
        });


    }

    public  void addCarExpense(){
        setContentView(R.layout.add_car_expense);
        ImageView save=findViewById(R.id.saveExpense);
        ImageView cancel=findViewById(R.id.cancel);
        EditText expenseName=findViewById(R.id.expenseName);
        EditText expenseAmount=findViewById(R.id.expenseAmount);
        expenseDate=findViewById(R.id.expenseDate);

        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name=expenseName.getText().toString();
                String expAmount= expenseAmount.getText().toString();
                String expDate=expenseDate.getText().toString();
                if(name.equals("")||expAmount.equals("")||expDate.equals("")){
                    Toast.makeText(getApplicationContext(), "Record Expense", Toast.LENGTH_SHORT).show();
                }else{
                    Boolean insertexpense=db.insertExpense(name,expAmount, expDate);
                    if (insertexpense==true){
                        Toast.makeText(getApplicationContext(),"Expense Added", Toast.LENGTH_SHORT).show();
                        startActivity(new Intent(getApplicationContext(), CarExpenses.class));
                    }else {
                        Toast.makeText(getApplicationContext(),"Retry", Toast.LENGTH_SHORT).show();
                    }
                }



            }
        });
        expenseDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                datePicker();
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

        expenseDate.setText(date);


    }
}