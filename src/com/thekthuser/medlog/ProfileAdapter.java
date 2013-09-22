package com.thekthuser.medlog;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.SQLException;
import android.content.ContentValues;
import android.database.Cursor;

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
        //long
        ContentValues contentValue = new ContentValues();
        contentValue.put("name", general.getName());
        contentValue.put("address", general.getAddress());
        contentValue.put("phone", general.getPhone());
        //return db.insert("medlog", null, contentValue);
        //DATABASE_TABLE
    }

    public void getGeneralInfo(int id) {
        //return GeneralInfo
        String[] projection = {
            DatabaseHelper.COLUMN_NAME,
            DatabaseHelper.COLUMN_ADDRESS,
            DatabaseHelper.COLUMN_PHONE
        };
        Cursor c = dbr.query(
                DatabaseHelper.TABLE_GENERAL_INFO,
                projection,
                null,
                null,
                null,
                null,
                null
        );
    }

}
