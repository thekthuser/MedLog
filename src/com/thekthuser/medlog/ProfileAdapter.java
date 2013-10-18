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

    public void addSelf(Self self) {
        //long?
        Log.i("addSelf", "inside");
        long general_id = addGeneralInfo(self.getGeneralInfo());
        ContentValues cValues = new ContentValues();
        /*cValues.put(DatabaseHelper.COLUMN_NAME, general.getName());
        cValues.put(DatabaseHelper.COLUMN_ADDRESS, general.getAddress());
        cValues.put(DatabaseHelper.COLUMN_PHONE, general.getPhone());*/
        cValues.put(DatabaseHelper.COLUMN_GENERAL_INFO_ID, general_id);
            if(self.getId() != -1) {
                String sId = Integer.toString(self.getId());
                String where = DatabaseHelper.COLUMN_ID + " = ?";
                String[] args = {
                    sId
                };
                db.update(DatabaseHelper.TABLE_SELF, cValues, where, args);
                Log.i("addSelf", "after update");
                //Log.i("addSelf", "update");
                //returns int
            } else {
                db.insert(DatabaseHelper.TABLE_SELF, null, cValues);
                Log.i("addSelf", "after insert");
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

    public void addPharmacy(Pharmacy pharmacy) {
        long general_id = addGeneralInfo(pharmacy.getGeneralInfo());
        ContentValues cValues = new ContentValues();
        cValues.put(DatabaseHelper.COLUMN_HOURS, pharmacy.getHours());
        cValues.put(DatabaseHelper.COLUMN_GENERAL_INFO_ID, general_id);

        if (pharmacy.getId() != -1) {
            String sId = Integer.toString(pharmacy.getId());
            String where = DatabaseHelper.COLUMN_ID + " = ?";
            String[] args = {
                sId
            };
            db.update(DatabaseHelper.TABLE_PHARMACY, cValues, where, args);
        } else {
            db.insert(DatabaseHelper.TABLE_PHARMACY, null, cValues);
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

    public Self getSelf(int id) {
        Log.i("getSelf", "inside, id = " + id);
        String[] projection = {
            DatabaseHelper.COLUMN_ID,
            DatabaseHelper.COLUMN_GENERAL_INFO_ID
        };
        String selection = DatabaseHelper.COLUMN_ID + " = ?";
        String sId = Integer.toString(id);
        String[] selectionArgs = {
            sId
        };
        String order = DatabaseHelper.COLUMN_ID + " DESC LIMIT 1";

        Cursor cursor = dbr.query(
            DatabaseHelper.TABLE_SELF,
            projection,
            selection,
            selectionArgs,
            null,
            null,
            order
        );

        Self self;
        if (cursor.moveToFirst()) {
            Log.i("getSelf", "cursor found");
            GeneralInfo sGeneral = getGeneralInfo(cursor.getInt(1));
            self = new Self(cursor.getInt(0), sGeneral);
        } else {
            Log.i("getSelf", "curosr empty");
            GeneralInfo sGeneral = getGeneralInfo(-1);
            self = new Self(-1, sGeneral);
        }
        return self;
    }

    public Pharmacy getPharmacy(int id) {
        String[] projection = {
            DatabaseHelper.COLUMN_ID,
            DatabaseHelper.COLUMN_HOURS,
            DatabaseHelper.COLUMN_GENERAL_INFO_ID
        };
        String selection = DatabaseHelper.COLUMN_ID + " = ?";
        String sId = Integer.toString(id);
        String[] selectionArgs = {
            sId
        };
        String order = DatabaseHelper.COLUMN_ID + " DESC LIMIT 1";

        Cursor cursor = dbr.query(
            DatabaseHelper.TABLE_PHARMACY,
            projection,
            selection,
            selectionArgs,
            null,
            null,
            order
        );

        Pharmacy pharmacy;
        if (cursor.moveToFirst()) {
            GeneralInfo pGeneral = getGeneralInfo(cursor.getInt(2));
            pharmacy = new Pharmacy(cursor.getInt(0), cursor.getString(1), pGeneral);
        } else {
            GeneralInfo pGeneral = getGeneralInfo(-1);
            pharmacy = new Pharmacy(-1, "Hours", pGeneral);
        }
        return pharmacy;
    }





}
