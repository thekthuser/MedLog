package com.thekthuser.medlog;

import android.content.Context;
//import android.database.sqlite.*;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class DatabaseHelper extends SQLiteOpenHelper {
    //DATABASE_CREATE = "CREATE TABLE general_info (_id integer primary key autoincrement, name text, address text, phone text);";

    public static final String DATABASE_NAME = "medlog.db";
    public static final int DATABASE_VERSION = 6;

    public static final String TABLE_GENERAL_INFO = "general_info";
    public static final String TABLE_SELF = "self";
    public static final String TABLE_PRESCRIBER = "prescriber";
    public static final String TABLE_PHARMACY = "pharmacy";
    public static final String TABLE_EMERGENCY = "emergency";
    public static final String TABLE_MEDICATION = "medication";
    public static final String TABLE_PRESCRIPTION = "prescription";

    public static final String COLUMN_ID = "_id";
    public static final String COLUMN_NAME = "name";
    public static final String COLUMN_ADDRESS = "address";
    public static final String COLUMN_PHONE = "phone";

    public static final String COLUMN_HOURS = "hours";
    public static final String COLUMN_SPECIALTY = "specialty";
    public static final String COLUMN_GENERAL_INFO_ID = "general_info_id";
    public static final String COLUMN_RELATION = "relation";

    public static final String COLUMN_SCIENTIFIC_NAME = "scientific_name";
    public static final String COLUMN_BRAND_NAME = "brand_name";
    
    public static final String COLUMN_MEDICATION_ID = "medication_id";
    public static final String COLUMN_PILL_DOSAGE = "pill_dosage";
    public static final String COLUMN_DOSAGE_TAKEN = "dosage_taken";
    //public static final String COLUMN_SCHEDULE_ID = "schedule_id";

    //datetime

    public static final String GENERAL_INFO_CREATE = "CREATE TABLE "
        + TABLE_GENERAL_INFO + "(" 
        + COLUMN_ID + " integer primary key autoincrement, " 
        + COLUMN_NAME + " text,"
        + COLUMN_ADDRESS + " text,"
        + COLUMN_PHONE + " text);";

    public static final String SELF_CREATE = "CREATE TABLE "
        + TABLE_SELF + "("
        + COLUMN_ID + " integer primary key autoincrement, "
        + COLUMN_GENERAL_INFO_ID + " integer, "
        + "FOREIGN KEY (" + COLUMN_GENERAL_INFO_ID + ") REFERENCES "
        + TABLE_GENERAL_INFO + " (" + COLUMN_ID + "));";

    public static final String PRESCRIBER_CREATE = "CREATE TABLE "
        + TABLE_PRESCRIBER + "("
        + COLUMN_ID + " integer primary key autoincrement, "
        + COLUMN_SPECIALTY + " text, "
        + COLUMN_GENERAL_INFO_ID + " integer, "
        + "FOREIGN KEY (" + COLUMN_GENERAL_INFO_ID + ") REFERENCES "
        + TABLE_GENERAL_INFO + " (" + COLUMN_ID + "));";

    public static final String PHARMACY_CREATE = "CREATE TABLE "
        + TABLE_PHARMACY + "("
        + COLUMN_ID + " integer primary key autoincrement, "
        + COLUMN_HOURS + " text, "
        + COLUMN_GENERAL_INFO_ID + " integer, "
        + "FOREIGN KEY (" + COLUMN_GENERAL_INFO_ID + ") REFERENCES "
        + TABLE_GENERAL_INFO + " (" + COLUMN_ID + "));";

    public static final String EMERGENCY_CREATE = "CREATE TABLE "
        + TABLE_EMERGENCY + "("
        + COLUMN_ID + " integer primary key autoincrement, "
        + COLUMN_GENERAL_INFO_ID + " integer, "
        + COLUMN_RELATION + " text, "
        + "FOREIGN KEY (" + COLUMN_GENERAL_INFO_ID + ") REFERENCES "
        + TABLE_GENERAL_INFO + " (" + COLUMN_ID + "));";

    public static final String MEDICATION_CREATE = "CREATE TABLE "
        + TABLE_MEDICATION + "("
        + COLUMN_ID + " integer primary key autoincrement, "
        + COLUMN_SCIENTIFIC_NAME + " text, "
        + COLUMN_BRAND_NAME + " text);";

    public static final String PRESCRIPTION_CREATE = "CREATE TABLE "
        + TABLE_PRESCRIPTION + "("
        + COLUMN_ID + " integer primary key autoincrement, "
        + COLUMN_PILL_DOSAGE + " text,"
        + COLUMN_DOSAGE_TAKEN + " text, "
        + COLUMN_MEDICATION_ID + " integer, "
        + "FOREIGN KEY (" + COLUMN_MEDICATION_ID + ") REFERENCES "
        + TABLE_MEDICATION + " (" + COLUMN_ID + "));";


    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(GENERAL_INFO_CREATE);
        db.execSQL(SELF_CREATE);
        db.execSQL(PRESCRIBER_CREATE);
        db.execSQL(PHARMACY_CREATE);
        db.execSQL(EMERGENCY_CREATE);
        db.execSQL(MEDICATION_CREATE);
        db.execSQL(PRESCRIPTION_CREATE);
        //run for other tables
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        /*Log.w(MySQLiteHelper.class.getName(),
                "Upgrading database from version " + oldVersion + "to "
                + newVersion + ", which will destroy all old data.");*/
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_GENERAL_INFO);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_SELF);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_PRESCRIBER);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_PHARMACY);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_EMERGENCY);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_MEDICATION);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_PRESCRIPTION);
        onCreate(db);
    }

}
