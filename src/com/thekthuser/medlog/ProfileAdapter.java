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
        ContentValues cValue = new ContentValues();
        cValue.put(DatabaseHelper.COLUMN_NAME, general.getName());
        cValue.put(DatabaseHelper.COLUMN_ADDRESS, general.getAddress());
        cValue.put(DatabaseHelper.COLUMN_PHONE, general.getPhone());
            if(general.getId() != -1) {
                String sId = Integer.toString(general.getId());
                String where = DatabaseHelper.COLUMN_ID + "=?";
                String[] args = {
                    sId
                };
                db.update(DatabaseHelper.TABLE_GENERAL_INFO, cValue, where, args);
                Log.i("addSelf", "update");
                //returns int
            } else {
                db.insert(DatabaseHelper.TABLE_GENERAL_INFO, null, cValue);
                Log.i("addSelf", "insert");
                //returns long
            }
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

}
