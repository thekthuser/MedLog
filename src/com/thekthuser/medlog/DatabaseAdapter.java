package com.thekthuser.medlog;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.SQLException;

public class DatabaseAdapter {

    private Context context;
    private SQLiteDatabase db;
    private DatabaseHelper dbHelper;

    public DatabaseAdapter(Context context) {
        this.context = context;
    }

    public DatabaseAdapter open() throws SQLException {
        dbHelper = new DatabaseHelper(context);
        db = dbHelper.getWritableDatabase();
        return this;
    }

    public void close() {
        dbHelper.close();
    }

}
