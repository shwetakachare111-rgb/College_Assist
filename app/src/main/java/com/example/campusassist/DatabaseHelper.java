package com.example.campusassist;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DatabaseHelper extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "CampusAssist.db";
    private static final int DATABASE_VERSION = 1;

    public static final String TABLE_COMPLAINTS = "complaints";

    public static final String COLUMN_ID = "id";
    public static final String COLUMN_TITLE = "title";
    public static final String COLUMN_CATEGORY = "category";
    public static final String COLUMN_LOCATION = "location";
    public static final String COLUMN_DESCRIPTION = "description";
    public static final String COLUMN_PRIORITY = "priority";
    public static final String COLUMN_DATE = "date";
    public static final String COLUMN_STATUS = "status";

    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        String createTable = "CREATE TABLE " + TABLE_COMPLAINTS + " (" +
                COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                COLUMN_TITLE + " TEXT, " +
                COLUMN_CATEGORY + " TEXT, " +
                COLUMN_LOCATION + " TEXT, " +
                COLUMN_DESCRIPTION + " TEXT, " +
                COLUMN_PRIORITY + " TEXT, " +
                COLUMN_DATE + " TEXT, " +
                COLUMN_STATUS + " TEXT)";

        db.execSQL(createTable);
    }

    @Override
    public void onUpgrade(
            SQLiteDatabase db,
            int oldVersion,
            int newVersion) {

        db.execSQL(
                "DROP TABLE IF EXISTS " + TABLE_COMPLAINTS
        );

        onCreate(db);
    }

    // CREATE
    public boolean insertComplaint(
            String title,
            String category,
            String location,
            String description,
            String priority,
            String date) {

        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();

        values.put(COLUMN_TITLE, title);
        values.put(COLUMN_CATEGORY, category);
        values.put(COLUMN_LOCATION, location);
        values.put(COLUMN_DESCRIPTION, description);
        values.put(COLUMN_PRIORITY, priority);
        values.put(COLUMN_DATE, date);
        values.put(COLUMN_STATUS, "Pending");

        long result = db.insert(
                TABLE_COMPLAINTS,
                null,
                values
        );

        db.close();

        return result != -1;
    }

    // UPDATE
    public boolean updateStatus(int id, String status) {

        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();

        values.put(COLUMN_STATUS, status);

        int result = db.update(
                TABLE_COMPLAINTS,
                values,
                COLUMN_ID + "=?",
                new String[]{String.valueOf(id)}
        );

        db.close();

        return result > 0;
    }

    // DELETE
    public boolean deleteComplaint(int id) {

        SQLiteDatabase db = this.getWritableDatabase();

        int result = db.delete(
                TABLE_COMPLAINTS,
                COLUMN_ID + "=?",
                new String[]{String.valueOf(id)}
        );

        db.close();

        return result > 0;
    }
}