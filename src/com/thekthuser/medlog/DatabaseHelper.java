package com.thekthuser.medlog;

import android.content.Context;
//import android.database.sqlite.*;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class DatabaseHelper extends SQLiteOpenHelper {
    //DATABASE_CREATE = "CREATE TABLE general_info (_id integer primary key autoincrement, name text, address text, phone text);";

    public static final String DATABASE_NAME = "medlog.db";
    public static final int DATABASE_VERSION = 1;

    public static final String TABLE_GENERAL_INFO = "general_info";
    public static final String COLUMN_ID = "_id";
    public static final String COLUMN_NAME = "name";
    public static final String COLUMN_ADDRESS = "address";
    public static final String COLUMN_PHONE = "phone";
    //datetime

    public static final String DATABASE_CREATE = "CREATE TABLE "
        + TABLE_GENERAL_INFO + "(" 
        + COLUMN_ID + " integer primary key autoincrement, " 
        + COLUMN_NAME + " text,"
        + COLUMN_ADDRESS + " text,"
        + COLUMN_PHONE + " text);";


    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(DATABASE_CREATE);
        //run for other tables
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        /*Log.w(MySQLiteHelper.class.getName(),
                "Upgrading database from version " + oldVersion + "to "
                + newVersion + ", which will destroy all old data.");*/
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_GENERAL_INFO);
        onCreate(db);
    }

}
