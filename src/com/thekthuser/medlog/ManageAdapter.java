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
            Log.i("BBBBBBBBBBBBBBBBBB", "b4 update");
            db.update(DatabaseHelper.TABLE_MEDICATION, cValues, where, args);
        } else {
            Log.i("BBBBBBBBBBBBBBBBBB", "b4 insert " + medication.getId());
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
            medication = new Medication("Scientific Name", "Brand Name");
        }
        return medication;
    }

    public ArrayList<Medication> getMedicationList() {
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

        ArrayList<Medication> meds = new ArrayList<Medication>();
        if (cursor.moveToFirst()) {
            do {
                Log.i("BBBBBBBBBBBBBBB", "getMedList id = " + cursor.getInt(0));
                Medication m = new Medication(cursor.getInt(0), 
                cursor.getString(1), cursor.getString(2));
                meds.add(m);
            } while (cursor.moveToNext());
        }
        return meds;
    }

    public void addPrescription(Prescription prescription) {
        ContentValues cValues = new ContentValues();
        cValues.put(DatabaseHelper.COLUMN_PILL_DOSAGE, prescription.getPillDosage());
        cValues.put(DatabaseHelper.COLUMN_DOSAGE_TAKEN, prescription.getDosageTaken());
        cValues.put(DatabaseHelper.COLUMN_MEDICATION_ID, prescription.getMedicationId());

        if (prescription.getId() != -1) {
            String sId = Integer.toString(prescription.getId());
            String where = DatabaseHelper.COLUMN_ID + " = ?";
            String[] args = {
                sId
            };
            db.update(DatabaseHelper.TABLE_MEDICATION, cValues, where, args);
        } else {
            db.insert(DatabaseHelper.TABLE_MEDICATION, null, cValues);
        }
    }

    public Prescription getPrescription(int id) {
        String[] projection = {
            DatabaseHelper.COLUMN_ID,
            DatabaseHelper.COLUMN_MEDICATION_ID,
            DatabaseHelper.COLUMN_PILL_DOSAGE,
            DatabaseHelper.COLUMN_DOSAGE_TAKEN
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

        Prescription prescription;
        if (cursor.moveToFirst()) {
            prescription = new Prescription(cursor.getInt(0), cursor.getInt(1), 
            cursor.getString(2), cursor.getString(3));
        } else {
            prescription = new Prescription(-1, "Pill Dosage", "Dosage Taken");
        }
        return prescription;
    }

    public ArrayList<Prescription> getPrescriptionList(int medId) {
        String[] projection = {
            DatabaseHelper.COLUMN_ID,
            DatabaseHelper.COLUMN_MEDICATION_ID,
            DatabaseHelper.COLUMN_PILL_DOSAGE,
            DatabaseHelper.COLUMN_DOSAGE_TAKEN
        };
        String order = DatabaseHelper.COLUMN_ID + " ASC";

        Cursor cursor = dbr.query(
            DatabaseHelper.TABLE_PRESCRIPTION,
            projection,
            null,
            null,
            null,
            null,
            order
        );

        ArrayList<Prescription> pres = new ArrayList<Prescription>();
        if (cursor.moveToFirst()) {
            do {
                Prescription p = new Prescription(cursor.getInt(0), 
                cursor.getInt(1), cursor.getString(2), cursor.getString(3));
                pres.add(p);
            } while (cursor.moveToNext());
        }
        return pres;
    }

}
