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


        Intent intent=new Intent(getApplicationContext(), home.class);
       // startActivity(intent);
        //String sql= ("SELECT * FROM tableName where logged=1");
        String sql="SELECT *FROM tableName";
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
            Boolean checkUser=db.checkUser(name);
            Boolean checkMail=db.checkMail(name);
            if(checkUser=checkMail =true){


                    Boolean checkPass=db.checkPassword(pass);
                    if(checkPass=true){
                        startPb();

                    }else{
                        error.setText("Wrong password");
                    }
            }else{
                error.setText("Details do not match");
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
                    try {
                        Thread.sleep(100);
                        pb.setProgress(i);
                        System.out.println(i);
                        linearLayout.setActivated(false);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
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