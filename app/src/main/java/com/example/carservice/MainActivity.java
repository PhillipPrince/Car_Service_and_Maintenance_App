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

import org.json.JSONException;
import org.json.JSONObject;

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


       // db.syncDetails();
       //startActivity(new Intent(MainActivity.this, home.class));

      /*  Cursor cursor= db.syncDetails();
        if(cursor !=null && cursor.getCount()>0){
            Intent intent=new Intent(getApplicationContext(), home.class);
            startActivity(intent);
        }*/


    }

    public void signUp(View view) {

        startActivity(new Intent(getApplicationContext(), newUser.class));
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
                }
            }else {
                if(checkUser==true){
                    Boolean checkPass=db.checkPassword(pass);
                    if(checkPass==true){
                        Toast.makeText(getApplicationContext(), "Login successful \n Welcome", Toast.LENGTH_SHORT).show();
                        Intent intent=new Intent(getApplicationContext(), home.class);
                        startActivity(intent);
                    }
                }

            }
        }



    }

    public void log(String email, String password){
        String type="login";

        //noinspection deprecation
        new android.os.AsyncTask<Void, Void, String>(){
            protected String doInBackground(Void[] params) {
                String response="";
                try {
                    String strings[]=new String[3];
                    strings[0]=type;
                    strings[1]=email;
                    strings[2]=password;
                    HttpProcesses httpProcesses=new HttpProcesses();
                    response = httpProcesses.sendRequest(strings);




                } catch (Exception e) {
                    response=e.getMessage();
                }

                return response;
            }
            protected void onPostExecute(String response) {

                //do something with response
                try {
                    JSONObject jsonObject=new JSONObject(response);
                    Boolean result=jsonObject.getBoolean("status");
                    if(result==true){
                        String message=jsonObject.getString("message");

                        int mechanicStatus=jsonObject.getInt("");
                        UserDetails.INSTANCE.setUserId(jsonObject.getInt("id"));
                        UserDetails.INSTANCE.setMechanicStatus(jsonObject.getInt("mechanicStatus"));
                        UserDetails.INSTANCE.setPhone(jsonObject.getString("Phone"));
                        UserDetails.INSTANCE.setName(jsonObject.getString("Name"));
                        Intent intent=new Intent(getApplicationContext(), home.class);

                        startActivity(intent);

                        Toast.makeText(getApplicationContext(), message+"\n \tWelcome", Toast.LENGTH_LONG).show();
                    }
                    else{
                        error.setText("Invalid UserName Or Password");
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }


            }

        }.execute();

    }


}