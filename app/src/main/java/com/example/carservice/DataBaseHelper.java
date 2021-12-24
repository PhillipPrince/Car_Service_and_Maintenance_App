package com.example.carservice;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.database.sqlite.SQLiteAccessPermException;
import android.widget.Toast;

import androidx.annotation.Nullable;

public class DataBaseHelper  extends SQLiteOpenHelper {
    public static String DBName="CarUsers.db";
    public static int DBVersion=5;
    public static String tableName="user";
    public DataBaseHelper(@Nullable Context context) {
        super(context, DBName, null, DBVersion);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        this.getWritableDatabase();
        String sql="create table tableName(username TEXT primary key, email TEXT, password TEXT)";
        db.execSQL(sql);

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
}
