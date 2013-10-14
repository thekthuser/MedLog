package com.thekthuser.medlog;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.SQLException;
import android.content.ContentValues;
import android.database.Cursor;

import android.util.Log;

public class ProfileAdapter {

    private Context context;
    private SQLiteDatabase db;
    private SQLiteDatabase dbr;
    private DatabaseHelper dbHelper;

    public ProfileAdapter(Context context) {
        this.context = context;
    }

    public ProfileAdapter open() throws SQLException {
        dbHelper = new DatabaseHelper(context);
        db = dbHelper.getWritableDatabase();
        dbr = dbHelper.getReadableDatabase();
        return this;
    }

    public void close() {
        dbHelper.close();
    }

    public void addSelf(GeneralInfo general) {
        //long?
        ContentValues cValues = new ContentValues();
        cValues.put(DatabaseHelper.COLUMN_NAME, general.getName());
        cValues.put(DatabaseHelper.COLUMN_ADDRESS, general.getAddress());
        cValues.put(DatabaseHelper.COLUMN_PHONE, general.getPhone());
            if(general.getId() != -1) {
                String sId = Integer.toString(general.getId());
                String where = DatabaseHelper.COLUMN_ID + "=?";
                String[] args = {
                    sId
                };
                db.update(DatabaseHelper.TABLE_GENERAL_INFO, cValues, where, args);
                //Log.i("addSelf", "update");
                //returns int
            } else {
                db.insert(DatabaseHelper.TABLE_GENERAL_INFO, null, cValues);
                //Log.i("addSelf", "insert");
                //returns long
            }
    }

    public void addPrescriber(Prescriber prescriber) {
        long general_id = addGeneralInfo(prescriber.getGeneralInfo());
        ContentValues cValues = new ContentValues();
        cValues.put(DatabaseHelper.COLUMN_SPECIALTY, prescriber.getSpecialty());
        cValues.put(DatabaseHelper.COLUMN_GENERAL_INFO_ID, general_id);
        Log.i("addpres", "pres.id = " + prescriber.getId());
        if (prescriber.getId() != -1) {
            String sId = Integer.toString(prescriber.getId());
            String where = DatabaseHelper.COLUMN_ID + "=?";
            String[] args = {
                sId
            };
            db.update(DatabaseHelper.TABLE_PRESCRIBER, cValues, where, args);
            Log.i("addpres", "after update");
        } else {
            db.insert(DatabaseHelper.TABLE_PRESCRIBER, null, cValues);
            Log.i("addpres", "after insert");
        }
    }

    public long addGeneralInfo(GeneralInfo general) {
        ContentValues cValues = new ContentValues();
        cValues.put(DatabaseHelper.COLUMN_NAME, general.getName());
        cValues.put(DatabaseHelper.COLUMN_ADDRESS, general.getAddress());
        cValues.put(DatabaseHelper.COLUMN_PHONE, general.getPhone());
        long rowId;
            if (general.getId() != -1) {
                String sId = Integer.toString(general.getId());
                String where = DatabaseHelper.COLUMN_ID + "=?";
                String[] args = {
                    sId
                };
                db.update(DatabaseHelper.TABLE_GENERAL_INFO, cValues, where, args);
                rowId = general.getId();
                //returns int # rows updated
            } else {
                rowId = db.insert(DatabaseHelper.TABLE_GENERAL_INFO, null, cValues);
                //returns long rowid
            }
        return rowId;
    }

    public GeneralInfo getGeneralInfo(int id) {
        String[] projection = {
            DatabaseHelper.COLUMN_ID,
            DatabaseHelper.COLUMN_NAME,
            DatabaseHelper.COLUMN_ADDRESS,
            DatabaseHelper.COLUMN_PHONE
        };

        String selection = DatabaseHelper.COLUMN_ID + " = ?";

        String sId = Integer.toString(id);
        String[] selectionArgs = { 
            sId
        };

        String order = DatabaseHelper.COLUMN_ID + " DESC LIMIT 1";

        Cursor cursor = dbr.query(
                DatabaseHelper.TABLE_GENERAL_INFO,
                projection,
                selection, //String Where-clause "where id=1 and name=?"
                selectionArgs, //String[] selectionArgs "fills ? above"
                null, //String[] groupBy
                null, //String[] having (filter for groups)
                order  //String[] orderBy
        );
        GeneralInfo general = new GeneralInfo("Enter a name", "Enter an address", "Enter a phone");
        if(cursor.moveToFirst()) {
            general.setId(cursor.getInt(0));
            general.setName(cursor.getString(1));
            general.setAddress(cursor.getString(2));
            general.setPhone(cursor.getString(3));
        }
        return general;
    }

    public Prescriber getPrescriber(int id) {
        String[] projection = {
            DatabaseHelper.COLUMN_ID,
            DatabaseHelper.COLUMN_SPECIALTY,
            DatabaseHelper.COLUMN_GENERAL_INFO_ID
        };
        String selection = DatabaseHelper.COLUMN_ID + " = ?";
        String sId = Integer.toString(id);
        String[] selectionArgs = {
            sId
        };
        String order = DatabaseHelper.COLUMN_ID + " DESC LIMIT 1";

        Cursor cursor = dbr.query(
            DatabaseHelper.TABLE_PRESCRIBER,
            projection,
            selection,
            selectionArgs,
            null,
            null,
            order
        );

        Prescriber prescriber;
        if (cursor.moveToFirst()) {
            Log.i("getpres", "in success");
            GeneralInfo pGeneral = getGeneralInfo(cursor.getInt(2));
            prescriber = new Prescriber(cursor.getInt(0), cursor.getString(1), pGeneral);
        } else {
            Log.i("getpres", "not found");
            GeneralInfo pGeneral = getGeneralInfo(-1);
            prescriber = new Prescriber(-1, "Specialty", pGeneral);
        }
        return prescriber;
    }

}
