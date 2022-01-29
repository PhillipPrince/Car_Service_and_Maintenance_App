package com.example.carservice;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {
    EditText userName, password;
    Button login;
    TextView signUp, error;
    LinearLayout linearLayout;
    DataBaseHelper db;
    public static String tableName="user";

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


        Cursor cursor= db.syncDetails();
        if(cursor !=null && cursor.getCount()>0){
            Intent intent=new Intent(getApplicationContext(), home.class);

            startActivity(intent);


        }


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
            Boolean checkUser=db.checkUser(name);



            if(checkUser==false){
                Boolean checkMail=db.checkMail(name);
                if(checkMail==true){
                    Boolean checkPass=db.checkPassword(pass);
                    if(checkPass==true){
                        Toast.makeText(getApplicationContext(), "Login successful \n Welcome", Toast.LENGTH_SHORT).show();
                        Intent intent=new Intent(getApplicationContext(), home.class);
                        startActivity(intent);
                    }else {
                        Toast.makeText(getApplicationContext(), "wrong Password", Toast.LENGTH_SHORT).show();
                        password.setText("Enter a Valid Password");
                    }
                }else {
                    Toast.makeText(getApplicationContext(), "Wrong Email", Toast.LENGTH_SHORT).show();
                }
            }else {
                Boolean checkPass=db.checkPassword(pass);
                if(checkPass==true){
                    Intent intent=new Intent(getApplicationContext(), home.class);
                    startActivity(intent);
                }
            }
        }



    }

    private void startPb() {

        final ProgressBar pb = (ProgressBar) findViewById(R.id.progressBar);
        pb.setIndeterminate(false);
        pb.setProgress(0);
        new Thread(new Runnable() {

            public void run() {
                for (int i = 0; i <= 100; i++)
                {
                   // Thread.sleep(100);
                    pb.setProgress(i);
                    System.out.println(i);
                    linearLayout.setActivated(false);
                }
                pb.post(new Runnable() {
                    public void run() {
                        Intent intent=new Intent(getApplicationContext(), home.class);
                        startActivity(intent);
                        pb.incrementProgressBy(1);
                    }
                });

            }
        }).start();
    }

}