 package com.example.carservice;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import org.json.JSONException;
import org.json.JSONObject;

 public class newUser extends AppCompatActivity {
    EditText username,phoneNumber, email, password, repassword;
    TextView login;
    Button signUp;
    //DBHelper db;
    DataBaseHelper db;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        username=findViewById(R.id.name);
        phoneNumber=findViewById(R.id.phoneNumber);
        email=findViewById(R.id.email);
        password=findViewById(R.id.pass);
        repassword=findViewById(R.id.rePass);
        signUp=findViewById(R.id.create);
        login=findViewById(R.id.loginstead);
        db=new DataBaseHelper(getApplicationContext());

        signUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                create();
            }
        });
    }

    public void logInstead(View view) {
        Intent intent=new Intent(getApplicationContext(), MainActivity.class);
        startActivity(intent);
    }

    public void create() {
        String name=username.getText().toString();
        String phone=phoneNumber.getText().toString();
        String mail=email.getText().toString();
        String pass=password.getText().toString();
        String repass=repassword.getText().toString();

        if(name.equals("") || mail.equals("")||pass.equals("")||repass.equals("")){
            Toast.makeText(getApplicationContext(), "Enter all Fields", Toast.LENGTH_SHORT).show();
        }else{

            try {
                if(pass.equals(repass)){
                    Intent intent=new Intent(getApplicationContext(), home.class);
                    //startActivity(intent);

                    Boolean ins=db.insertData(name, mail,pass);

                    if(ins==true){
                     //   Toast.makeText(getApplicationContext(), "Registration successful", Toast.LENGTH_SHORT).show();
                        // Intent intent=new Intent(getApplicationContext(), home.class);
                        startActivity(intent);
                    }else{
                        Toast.makeText(getApplicationContext(), "Registration Failed. Try again", Toast.LENGTH_SHORT).show();
                    }
                    Boolean checkUser=db.checkUser(name);
                    if(checkUser==false){
                        Boolean checkMail=db.checkMail(mail);
                        if(checkMail==false){
                            Boolean insert=db.insertData(name,mail, pass);
                            if(insert==true){
                                register(name, phone, mail, pass);

                                startActivity(intent);
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
                    startActivity(intent);
                }else{
                    Toast.makeText(getApplicationContext(), "Password did not Match", Toast.LENGTH_SHORT).show();
                }

            } catch (Exception e) {
                e.printStackTrace();
            }

        }
		

    }

    public void register(String name, String phone, String email, String password){

        final String type = "reg";


        //noinspection deprecation
        new android.os.AsyncTask<Void, Void, String>() {
                protected String doInBackground(Void[] params) {
                    String response = "";
                    try {

                        String[] strings = new String[5];
                        strings[0] = type;
                        strings[1] = name;
                        strings[2] = phone;
                        strings[3] = email;
                        strings[4] = password;

                        HttpProcesses httpProcesses = new HttpProcesses();
                        response = httpProcesses.sendRequest(strings);
                    } catch (Exception e) {
                        response = e.getMessage();
                    }
                    return response;
                }
                protected void onPostExecute(String response) {

                    try {
                        JSONObject jsonObject = new JSONObject(response);
                        Boolean result = jsonObject.getBoolean("status");
                        //  JSONArray      jsonArray = jsonObject.optJSONArray("data");
                        String message = jsonObject.getString("message");
                        if (result == true) {
                            // String names=jsonArray.getString(jsonObject.getInt("name "));

                            Toast.makeText(getApplicationContext(), message, Toast.LENGTH_SHORT).show();

                        } else {
                            Toast.makeText(getApplicationContext(),message , Toast.LENGTH_SHORT).show();
                        }
                    } catch (JSONException e) {
                        e.printStackTrace();




                    }
                }
            }.execute();

    }
}