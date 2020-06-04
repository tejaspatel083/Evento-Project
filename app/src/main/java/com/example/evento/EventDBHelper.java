package com.example.evento;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import com.example.evento.EventContract.*;

import androidx.annotation.Nullable;

public class EventDBHelper extends SQLiteOpenHelper {

    public static final String DATABASE_NAME = "eventlist.db";
    public static final int DATABASE_VERSION = 1;

    public EventDBHelper(@Nullable Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        final String SQL_CREATE_EVENTLIST_TABLE = "CREATE TABLE " +
                EventEntry.TABLE_NAME + " (" +
                EventEntry._ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                EventEntry.COLUMN_EVENT_NAME + " TEXT NOT NULL, " +
                EventEntry.COLUMN_EVENT_HOST_NAME + " TEXT NOT NULL, " +
                EventEntry.COLUMN_EVENT_LOCATION + " TEXT NOT NULL, " +
                EventEntry.COLUMN_EVENT_DATE + " TEXT NOT NULL, " +
                EventEntry.COLUMN_EVENT_TIME + " TEXT NOT NULL, " +
                EventEntry.COLUMN_EVENT_COST + " INTEGER NOT NULL, " +
                EventEntry.COLUMN_TIMESTAMP + " TIMESTAMP DEFAULT CURRENT_TIMESTAMP" +
                ");";

        db.execSQL(SQL_CREATE_EVENTLIST_TABLE);


    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        db.execSQL("DROP TABLE IF EXISTS " + EventEntry.TABLE_NAME);
        onCreate(db);

    }
}
