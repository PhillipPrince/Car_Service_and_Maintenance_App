package com.example.carservice;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import java.util.ArrayList;

public class DataBaseHelper  extends SQLiteOpenHelper {
    public static String DBName="CarUsers.db";
    public static int DBVersion=6;
    public static String tableName="user";
    public static String tableCar="tableCar";
    public DataBaseHelper(@Nullable Context context) {
        super(context, DBName, null, DBVersion);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        this.getWritableDatabase();
        db.execSQL("DROP TABLE IF EXISTS "+tableName);
        String sql="create table tableName(username TEXT primary key, email TEXT, password TEXT)";

        db.execSQL(sql);
        db.execSQL("create table tablecar(carModel VARCHAR, fuel VARCHAR, modelYear INTEGER , " +
                "engine VARCHAR ,chasisNo VARCHAR, engineNumber VARCHAR ,numberPlate VARCHAR, lastInsurance DATE)");

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {


    }

    public Boolean insertData(String username, String email, String password){
        SQLiteDatabase db=this.getWritableDatabase();

        ContentValues cv=new ContentValues();
        cv.put( "username", username);
        cv.put("email",email);
        cv.put("password", password);
        long result=db.insert(tableName, null, cv);
        if(result==-1){
            return false;
        }else{
            return true;
        }
    }
    public Boolean checkUser(String username){
        SQLiteDatabase db=this.getWritableDatabase();
        Cursor cursor=db.rawQuery("select * from users where username=?",  new String[]{username});
        if(cursor.getCount()>0){
            return  true;
        }else {
            return false;
        }
    }

    public Boolean checkMail(String email){
        SQLiteDatabase db=this.getWritableDatabase();
        String sql="select * from users where username=?";
        Cursor cursor=db.rawQuery("select * from users where email=?",  new String[]{email});
        if(cursor.getCount()>0){
            return  true;
        }else {
            return false;
        }
    }
    public Boolean checkPassword(String pass){
        SQLiteDatabase db=this.getWritableDatabase();
        Cursor cursor=db.rawQuery("select *from users where password=?", new String[]{pass});
        if(cursor.getCount()>0){
            return true;
        }else {
            return false;
        }
    }
    public Boolean saveCar( String carModel , String fuel, int modelYear  ,
            String engine  , String chasisNo , String engineNumber  , String numberPlate ,  String lastInsurance ){
        SQLiteDatabase db=this.getWritableDatabase();
        ContentValues contentValues=new ContentValues();
        contentValues.put("carModel", carModel);
        contentValues.put("fuel", fuel);
        contentValues.put("modelYear", modelYear);
        contentValues.put("engine", engine);
        contentValues.put("chasisNo", chasisNo);
        contentValues.put("engineNumber", engineNumber);
        contentValues.put("numberPlate", numberPlate);
        contentValues.put("lastInsurance", String.valueOf(lastInsurance));

        long result=db.insert(tableCar, null, contentValues);
        if(result>0){
            return true;
        }
        return false;
    }
     public ArrayList<String> myCars(){
        ArrayList<String> cars= new ArrayList<>();

        return cars;
     }

}
