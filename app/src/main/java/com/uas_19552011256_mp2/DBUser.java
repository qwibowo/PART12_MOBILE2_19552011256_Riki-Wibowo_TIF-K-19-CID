package com.uas_19552011256_mp2;


import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class DBUser extends SQLiteOpenHelper {
    public static final String DATABASE_NAME = "pemeliharaan2.db";
    public static final String TABLE_NAME ="registeruser";
    public static final String COL_1="ID";
    public static final String COL_2="username";
    public static final String COL_3="email";
    public static final String COL_4="password";
    public static final String COL_5="nama";



    public DBUser(@Nullable Context context) {
        super(context, DATABASE_NAME , null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE IF NOT EXISTS "+TABLE_NAME + "(ID INTEGER PRIMARY KEY AUTOINCREMENT , username TEXT , email TEXT , password TEXT , nama TEXT )");

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL(" DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(db);
    }

    public boolean tambahUser(String username , String email , String password, String nama){

        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COL_2 , username);
        values.put(COL_3 , email);
        values.put(COL_4 , password);
        values.put(COL_5 , nama);

        long result = db.insert(TABLE_NAME , null , values);
        if(result == -1)
            return false;
        else
            return true;
    }

    public boolean periksaUser(String username , String password){

        SQLiteDatabase db = this.getWritableDatabase();
        String [] columns = { COL_1 };
        String selection = COL_2 + "=?" + " and " + COL_4 + "=?";
        String [] selectionargs = { username , password};
        Cursor cursor = db.query(TABLE_NAME , columns , selection ,selectionargs , null , null , null);
        int count = cursor.getCount();
        db.close();
        cursor.close();
        if (count > 0)
            return true;
        else
            return false;

    }

}
