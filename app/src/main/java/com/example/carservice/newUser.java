 package com.example.carservice;

import android.content.Intent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;

public class newUser extends AppCompatActivity {
    EditText username, email, password, repassword;
    TextView login;
    Button signUp;
    //DBHelper db;
    DataBaseHelper db;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        username=findViewById(R.id.name);
        email=findViewById(R.id.email);
        password=findViewById(R.id.pass);
        repassword=findViewById(R.id.rePass);
        signUp=findViewById(R.id.create);
        login=findViewById(R.id.loginstead);
        db=new DataBaseHelper(this);
    }

    public void logInstead(View view) {
        Intent intent=new Intent(getApplicationContext(), MainActivity.class);
        startActivity(intent);
    }

    public void create(View view) {
        String name=username.getText().toString();
        String mail=email.getText().toString();
        String pass=password.getText().toString();
        String repass=repassword.getText().toString();

        if(name.equals("") || mail.equals("")||pass.equals("")||repass.equals("")){
            Toast.makeText(getApplicationContext(), "Enter all Fields", Toast.LENGTH_SHORT).show();
        }else{
            if(pass.equals(repass)){
                Intent intent=new Intent(getApplicationContext(), home.class);
                //startActivity(intent);
                Boolean ins=db.insertData(name, mail,pass);

                if(ins==true){
                    Toast.makeText(getApplicationContext(), "Registration successful", Toast.LENGTH_SHORT).show();
                    // Intent intent=new Intent(getApplicationContext(), home.class);
                     startActivity(intent);
                }else{
                    Toast.makeText(getApplicationContext(), "Registration Failed. Try again", Toast.LENGTH_SHORT).show();
                }
              /*Boolean checkUser=db.checkUser(name);
              if(checkUser==false){
                  Boolean checkMail=db.checkMail(mail);
                  if(checkMail==false){
                      Boolean insert=db.insertData(name,mail, pass);
                      if(insert==true){
                          Toast.makeText(getApplicationContext(), "Registration successful", Toast.LENGTH_SHORT).show();
                         // Intent intent=new Intent(getApplicationContext(), home.class);
                         // startActivity(intent);
                      }else{
                          Toast.makeText(getApplicationContext(), "Registration Failed. Try again", Toast.LENGTH_SHORT).show();
                      }
                  }else{
                      Toast.makeText(getApplicationContext(), "Email Already Exists", Toast.LENGTH_SHORT).show();
                  }
              }else{
                  Toast.makeText(getApplicationContext(), "Username already exists", Toast.LENGTH_SHORT).show();
              }

               // Intent intent=new Intent(getApplicationContext(), home.class);
                startActivity(intent);*/
            }else{
                Toast.makeText(getApplicationContext(), "Password did not Match", Toast.LENGTH_SHORT).show();
            }
        }
    }
}