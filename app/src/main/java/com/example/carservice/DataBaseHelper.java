package com.example.carservice;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import java.util.ArrayList;
import java.util.List;

public class DataBaseHelper  extends SQLiteOpenHelper {
    public static String DBName="CarUsers.db";
    public static int DBVersion=6;
    public static String tableName="user";
    public static String tableCar="tableCar";
    private static String tableMileage="mileage";
    public static  String tableMechanics="Mechanics";
    SQLiteDatabase sQLiteDatabase=this.getReadableDatabase();
    public DataBaseHelper(@Nullable Context context) {
        super(context, DBName, null, DBVersion);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        this.getWritableDatabase();

        String sql="create table tableName(username TEXT primary key, " +
                "email TEXT, " +
                "password TEXT)";

        db.execSQL(sql);
        db.execSQL("create table tablecar(id INTEGER PRIMARY KEY AUTOINCREMENT," +
                "carModel VARCHAR, " +
                "fuel VARCHAR, " +
                "modelYear INTEGER , " +
                "engine VARCHAR ," +
                "chasisNo VARCHAR, " +
                "engineNumber VARCHAR ," +
                "numberPlate VARCHAR, " +
                "lastInsurance DATE)");
        String mechanics="CREATE TABLE tableMechanics(id INTEGER PRIMARY KEY AUTOINCREMENT, mechName VARCHAR,mechLocation VARCHAR, mechPhone INTEEGER)";
        db.execSQL(mechanics);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db=this.getWritableDatabase();
        db.execSQL("DROP TABLE IF EXISTS "+tableName);
        db.execSQL("DROP TABLE IF EXISTS "+ tableCar);
        db.execSQL("DROP TABLE IF EXISTS "+tableMechanics);
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
        Cursor cursor=db.rawQuery("select * from tableName where username=?",  new String[]{username});
        if(cursor.getCount()>0){
            return  true;
        }else {
            return false;
        }
    }

    public Boolean checkMail(String email){
        SQLiteDatabase db=this.getWritableDatabase();
        String sql="select * from users where username=?";
        Cursor cursor=db.rawQuery("select * from tableName where email=?",  new String[]{email});
        if(cursor.getCount()>0){
            return  true;
        }else {
            return false;
        }
    }
    public Boolean checkPassword(String pass){
        SQLiteDatabase db=this.getWritableDatabase();
        Cursor cursor=db.rawQuery("select *from tableName where password=?", new String[]{pass});
        if(cursor.getCount()>0){
            return true;
        }else {
            return false;
        }
    }
    public Cursor syncDetails() {
        SQLiteDatabase db=this.getReadableDatabase();
        Cursor cursor=null;
        try {
            String sql= ("SELECT * FROM tableName where logged=1");
            cursor= db.rawQuery(sql, null);
            if (cursor.getCount()>0) {
                cursor.moveToFirst();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        cursor.close();
        db.close();
        return cursor;
    }
    public Boolean saveCar( String carModel , String fuel, int modelYear  ,
            String engine  , String chasisNo , String engineNumber  , String numberPlate ,  String lastInsurance ){
        int id=0;
        SQLiteDatabase db=this.getWritableDatabase();
        ContentValues contentValues=new ContentValues();
        contentValues.put("id", id );
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
     public  List<AvailableCars> myCars(){
         SQLiteDatabase db=this.getReadableDatabase();
         String sql="SELECT * FROM "+tableCar;

         Cursor cursor=null;
         List<AvailableCars> availableCars=new ArrayList<>();
         try {
             cursor= db.rawQuery(sql, null);
             cursor.moveToFirst();
             while(cursor.moveToNext()){
                 AvailableCars avCars=new AvailableCars();
                 avCars.setCarId(cursor.getInt(0));
                 avCars.setCarModel(cursor.getString(1));
                 avCars.setFuel(cursor.getString(2));
                 avCars.setModelYear(cursor.getString(3));
                 avCars.setEngine(cursor.getString(4));
                 avCars.setChassisNo(cursor.getString(5));
                 avCars.setEngineNo(cursor.getString(6));
                 avCars.setNumberPlate(cursor.getString(7));
                 avCars.setLastInsurance(cursor.getString(8));
                 availableCars.add(avCars);
             }

         } catch (Exception e) {
             e.printStackTrace();
         }
         cursor.close();
         db.close();



         return availableCars;

     }

     public void allCars(){
        SQLiteDatabase db=this.getReadableDatabase();
     }

     public Boolean addMechanic(String mechName, String mechLocation, String mechPhone){
        SQLiteDatabase db =this.getWritableDatabase();
        ContentValues contentValues=new ContentValues();
        int id = 0;
        contentValues.put("id", id);
        contentValues.put("mechName", mechName);
        contentValues.put("mechLocation", mechLocation);
        contentValues.put(mechPhone, mechPhone);
        Long results=db.insert(tableMechanics, null, contentValues);
        if(results>0){
            return true;
        }
        return false;

     }

}
