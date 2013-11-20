package com.thekthuser.medlog;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.SQLException;
import android.content.ContentValues;
import android.database.Cursor;
import java.util.HashMap;
import java.util.ArrayList;
import java.util.List;

import android.util.Log;

public class ManageAdapter {

    private Context context;
    private SQLiteDatabase db;
    private SQLiteDatabase dbr;
    private DatabaseHelper dbHelper;

    public ManageAdapter(Context context) {
        this.context = context;
    }

    public ManageAdapter open() throws SQLException {
        dbHelper = new DatabaseHelper(context);
        db = dbHelper.getWritableDatabase();
        dbr = dbHelper.getReadableDatabase();
        return this;
    }

    public void close() {
        dbHelper.close();
    }

    public void addMedication(Medication medication) {
        ContentValues cValues = new ContentValues();
        cValues.put(DatabaseHelper.COLUMN_SCIENTIFIC_NAME, medication.getScientificName());
        cValues.put(DatabaseHelper.COLUMN_BRAND_NAME, medication.getBrandName());
        Log.i("AAAAAAAAAAAAAAAA", medication.getBrandName());
        if (medication.getId() != -1) {
            String sId = Integer.toString(medication.getId());
            String where = DatabaseHelper.COLUMN_ID + " = ?";
            String[] args = {
                sId
            };
            db.update(DatabaseHelper.TABLE_MEDICATION, cValues, where, args);
        } else {
            db.insert(DatabaseHelper.TABLE_MEDICATION, null, cValues);
        }
    }


    public Medication getMedication(int id) {
        String[] projection = {
            DatabaseHelper.COLUMN_ID,
            DatabaseHelper.COLUMN_SCIENTIFIC_NAME,
            DatabaseHelper.COLUMN_BRAND_NAME
        };
        String selection = DatabaseHelper.COLUMN_ID + " = ?";
        String sId = Integer.toString(id);
        String[] selectionArgs = {
            sId
        };
        String order = DatabaseHelper.COLUMN_ID + " DESC LIMIT 1";

        Cursor cursor = dbr.query(
            DatabaseHelper.TABLE_MEDICATION,
            projection,
            selection,
            selectionArgs,
            null,
            null,
            order
        );

        Medication medication;
        if (cursor.moveToFirst()) {
            medication = new Medication(cursor.getInt(0), cursor.getString(1), cursor.getString(2));
        } else {
            medication = new Medication(-1, "Scientific Name", "Brand Name");
        }
        return medication;
    }

    public List getMedicationList() {
        String[] projection = {
            DatabaseHelper.COLUMN_ID,
            DatabaseHelper.COLUMN_SCIENTIFIC_NAME,
            DatabaseHelper.COLUMN_BRAND_NAME
        };
        String order = DatabaseHelper.COLUMN_ID + " ASC";

        Cursor cursor = dbr.query(
            DatabaseHelper.TABLE_MEDICATION,
            projection,
            null,
            null,
            null,
            null,
            order
        );

        ArrayList meds = new ArrayList();
        if (cursor.moveToFirst()) {
            do {
                HashMap m = new HashMap();
                m.put("Scientific Name", cursor.getString(1));
                m.put("Brand Name", cursor.getString(2));
                meds.add(m);
            } while (cursor.moveToNext());
        }
        return meds;
    }


}
