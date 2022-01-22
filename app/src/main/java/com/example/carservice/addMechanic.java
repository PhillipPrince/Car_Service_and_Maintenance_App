package com.example.carservice;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class addMechanic extends AppCompatActivity {

    static  EditText  mechName,  mechLocation,  mechPhone;
    ImageView save, discard;
    DataBaseHelper dataBaseHelper;
    TextView err;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_mechanic);
        mechName=findViewById(R.id.mechName);
        mechLocation=findViewById(R.id.mechLocation);
        mechPhone=findViewById(R.id.mechPhone);
        save=findViewById(R.id.saveMechanic);
        discard=findViewById(R.id.discard);
        err=findViewById(R.id.err);

        dataBaseHelper=new DataBaseHelper(getApplicationContext());
    }
    public void saveMech(View view){

       String mech=mechName.getText().toString();
       String mechLoc=mechLocation.getText().toString();
       String mechPh=mechPhone.getText().toString();

       Boolean saveMechanic=dataBaseHelper.addMechanic(mech, mechLoc, mechPh);
       if(mech.equals("") || mechLoc.equals("") || mechPh.equals("")){
        err.setText("Enter all details");
       }else{

           if(saveMechanic=true){
               Toast.makeText(getApplicationContext(), "Saved", Toast.LENGTH_SHORT).show();
               startActivity(new Intent(getApplicationContext(), mechanic.class));
           }else{
               Toast.makeText(getApplicationContext(), "Something went wrong, Retry", Toast.LENGTH_LONG).show();
           }
       }

    }
}