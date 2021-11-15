package com.example.carpoolconnect;

import static java.sql.DriverManager.println;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;
import android.widget.Toast;

import androidx.annotation.Nullable;


public class DBHelper extends SQLiteOpenHelper {

    public DBHelper(Context context) {
        super(context, "UserInfo.db", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase userDB) {
        userDB.execSQL("create Table userInfo(username Text primary key,password Text, fullname Text, age Text, email Text, phone Text, image Text)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase userDB, int i, int i1) {

    }

    public Boolean insertData(String username, String password, String fullname, String age, String email, String phone){
        SQLiteDatabase userDB = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("username", username);
        contentValues.put("password", password);
        contentValues.put("fullname", fullname);
        contentValues.put("age", age);
        contentValues.put("email", email);
        contentValues.put("phone", phone);

        long final_result = userDB.insert("userInfo", null, contentValues);

        if(final_result == -1){
            return false;
        }
        else{
            return true;
        }
    }

    public Boolean checkUserExists(String username){
        SQLiteDatabase userDB = this.getWritableDatabase();
        Cursor cursor = userDB.rawQuery("SELECT * FROM userInfo WHERE username = ?", new String[] {username});

        if(cursor.getCount()>0){
            return true;
        }
        else{
            return false;
        }
    }

    public Boolean checkUsernamePassword(String username, String password){
        SQLiteDatabase userDB = this.getWritableDatabase();
        Cursor cursor = userDB.rawQuery("SELECT * FROM userInfo WHERE username = ? AND password = ?", new String[] {username, password});

        if(cursor.getCount()>0){
            return true;
        }
        else{
            return false;
        }
    }

    public String getName(String username) {
        SQLiteDatabase userDB = this.getWritableDatabase();
        Cursor cursor = userDB.rawQuery("SELECT * FROM userInfo WHERE username = ?", new String[]{username});
        String test = "";
        cursor.moveToFirst();
        test = cursor.getString(cursor.getColumnIndexOrThrow("fullname"));
        cursor.close();
        return test;
    }

    public String getAge(String username){
        SQLiteDatabase userDB = this.getWritableDatabase();
        Cursor cursor = userDB.rawQuery("SELECT * FROM userInfo WHERE username = ?", new String[]{username});
        String test = "";
        try {
            if (cursor != null && cursor.moveToFirst()) {
                test = cursor.getString(cursor.getColumnIndexOrThrow("age"));
            }
        } finally {
            if (cursor != null) {
                cursor.close();
            }
        }
        return test;
    }

    public String getEmail(String username){
        SQLiteDatabase userDB = this.getWritableDatabase();
        Cursor cursor = userDB.rawQuery("SELECT * FROM userInfo WHERE username = ?", new String[]{username});
        String test = "";
        try {
            if (cursor != null && cursor.moveToFirst()) {
                test = cursor.getString(cursor.getColumnIndexOrThrow("email"));
            }
        } finally {
            if (cursor != null) {
                cursor.close();
            }
        }
        return test;
    }

    public String getPhone(String username){
        SQLiteDatabase userDB = this.getWritableDatabase();
        Cursor cursor = userDB.rawQuery("SELECT * FROM userInfo WHERE username = ?", new String[]{username});
        String test = "";
        try {
            if (cursor != null && cursor.moveToFirst()) {
                test = cursor.getString(cursor.getColumnIndexOrThrow("phone"));
            }
        } finally {
            if (cursor != null) {
                cursor.close();
            }
        }
        return test;
    }

    public void setImage(String username, String image){
        SQLiteDatabase userDB = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("image", image);
        userDB.update("userInfo", contentValues, "username = ?", new String[]{username});
    }

    public String getImage(String username){
        SQLiteDatabase userDB = this.getWritableDatabase();
        Cursor cursor = userDB.rawQuery("SELECT image FROM userInfo WHERE username = ? AND image IS NOT NULL", new String[]{username});
        String test = "";
        try {
            if (cursor != null && cursor.moveToFirst()) {
                test = cursor.getString(cursor.getColumnIndexOrThrow("image"));
            }
        } finally {
            if (cursor != null) {
                cursor.close();
            }
        }
        return test;
    }
}
