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

     EditText  mechName,  mechLocation,  mechPhone;
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

}