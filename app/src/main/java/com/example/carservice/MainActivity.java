package com.example.carservice;

import android.content.Intent;
import android.view.View;
import android.widget.*;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;

public class MainActivity extends AppCompatActivity {
    EditText userName, password;
    Button login;
    TextView signUp;
    LinearLayout linearLayout;
    DBHelper db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        userName=findViewById(R.id.userName);
        password=findViewById(R.id.password);
        login=findViewById(R.id.login);
        signUp=findViewById(R.id.signUp);
        linearLayout=findViewById(R.id.log);
        db=new DBHelper(this);





    }

    public void signUp(View view) {
        Intent intent=new Intent(getApplicationContext(), newUser.class);
        startActivity(intent);
    }

    public void login(View view) {
        String name=userName.getText().toString();
        String pass=password.getText().toString();

        if(name.equals("") || pass.equals("")){
            Toast.makeText(getApplicationContext(), "Enter all Fields", Toast.LENGTH_SHORT).show();

        }else {
            Toast.makeText(getApplicationContext(), "Login Successful", Toast.LENGTH_SHORT).show();
            Intent intent=new Intent(getApplicationContext(), home.class);
            startActivity(intent);
           /* Boolean userPass=db.checkUserNamePassword(name, pass);
            if(userPass==true){

                    Toast.makeText(getApplicationContext(), "Login Successful", Toast.LENGTH_SHORT).show();
                    Intent intent=new Intent(getApplicationContext(), home.class);
                    startActivity(intent);
                }else {
                    Toast.makeText(getApplicationContext(), "Wrong user details", Toast.LENGTH_SHORT).show();

            }*/

        }
    }
}