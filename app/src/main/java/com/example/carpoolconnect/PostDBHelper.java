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


public class PostDBHelper extends SQLiteOpenHelper {

    public PostDBHelper(Context context) {
        super(context, "Posts.db", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase postDB) {
        postDB.execSQL("create Table postInfo(name Text primary key,location Text, date Text)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase postDB, int i, int i1) {

    }

    public Boolean insertData(String name, String location, String date) {
        SQLiteDatabase postDB = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("name", name);
        contentValues.put("location", location);
        contentValues.put("date", date);

        long final_result = postDB.insert("postInfo", null, contentValues);

        if (final_result == -1) {
            return false;
        } else {
            return true;
        }
    }

    public String getLocation(String username) {
        SQLiteDatabase userDB = this.getWritableDatabase();
        Cursor cursor = userDB.rawQuery("SELECT * FROM postInfo WHERE name = ?", new String[]{username});
        String test = "";
        cursor.moveToFirst();
        test = cursor.getString(cursor.getColumnIndexOrThrow("location"));
        cursor.close();
        return test;
    }

    public String getDate(String username) {
        SQLiteDatabase userDB = this.getWritableDatabase();
        Cursor cursor = userDB.rawQuery("SELECT * FROM postInfo WHERE name = ?", new String[]{username});
        String test = "";
        cursor.moveToFirst();
        test = cursor.getString(cursor.getColumnIndexOrThrow("date"));
        cursor.close();
        return test;
    }
}
