package com.example.carservice;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import androidx.annotation.Nullable;

public class DBHelper extends SQLiteOpenHelper {

    public static final String DbName="Login.db";
    public static final int version=3;

    public DBHelper(Context context ) {
        super(context, DbName, null, 3);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table users(username TEXT primary key, email TEXT, password TEXT)");

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        db.execSQL("drop table  if exists users");
    }

    public Boolean insertData(String username, String email, String password){
        SQLiteDatabase db=this.getWritableDatabase();
        ContentValues cv=new ContentValues();
        cv.put( "username", username);
        cv.put("email",email);
        cv.put("password", password);
        long result=db.insert("users", null, cv);
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

    public void deleteUser(String username,  String password){
        SQLiteDatabase db=this.getWritableDatabase();
        String sql="delete * from users where username=?, password=?";
       db.execSQL(sql);

    }

    public  Boolean checkUserNamePassword(String username, String password){
        SQLiteDatabase db=this.getWritableDatabase();
        String sql="select * from users where username=?, password=?";
        Cursor cursor=db.rawQuery(sql, new String[]{username, password});
        if(cursor.getCount()>0){
            return  true;
        }else{
            return false;
        }
    }
}
