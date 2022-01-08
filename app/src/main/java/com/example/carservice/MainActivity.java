package com.example.carservice;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {
    EditText userName, password;
    Button login;
    TextView signUp, error;
    LinearLayout linearLayout;
    DataBaseHelper db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        userName=findViewById(R.id.userName);
        password=findViewById(R.id.password);
        login=findViewById(R.id.login);
        signUp=findViewById(R.id.signUp);
        linearLayout=findViewById(R.id.log);
        db=new DataBaseHelper(getApplicationContext());
        error=findViewById(R.id.error);


        String sql= ("SELECT * FROM tableName where logged=1");
        Cursor cursor=db.sQLiteDatabase.rawQuery(sql, null);
        cursor.getCount();





    }

    public void signUp(View view) {
        Intent intent=new Intent(getApplicationContext(), newUser.class);
        startActivity(intent);
    }

    public void login(View view) {
        String name=userName.getText().toString();
        String pass=password.getText().toString();



        if(name.equals("")||pass.equals("")){
            String err="Enter all Credentials";
            error.setText(err);

        }else{
            Boolean checkName=db.checkUser(name);

            if(checkName=true){
                Boolean checkMail=db.checkMail(name);
                if(checkMail=true){
                    Boolean checkPass=db.checkPassword(pass);
                    if(checkPass=true){
                        Intent intent=new Intent(getApplicationContext(), home.class);
                        startActivity(intent);
                    }else{
                        error.setText("Wrong password");
                    }
                }else{
                    error.setText("Wrong Email");
                }
            }else{
                error.setText("Details do not match");
            }
        }



    }
}