package com.example.activitytracker

import android.content.ContentValues
import android.content.Context
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

class DatabaseHelper(context: Context) : SQLiteOpenHelper(context, DATABASE_NAME, null, DATABASE_VERSION) {

    companion object {
        private const val DATABASE_NAME = "activity_tracker.db"
        private const val DATABASE_VERSION = 1
        private const val TABLE_NAME = "activities"
        private const val COLUMN_ID = "_id"
        const val COLUMN_ACTIVITY_TYPE = "activity_type"
        const val COLUMN_ACTIVITY_DATE = "date"
        const val COLUMN_ACTIVITY_TIME = "time"
        const val COLUMN_ACTIVITY_DURATION = "duration"

    }

    override fun onCreate(db: SQLiteDatabase) {
        val CREATE_TABLE = ("CREATE TABLE " + TABLE_NAME + "("
                + COLUMN_ID + " INTEGER PRIMARY KEY,"
                + COLUMN_ACTIVITY_TYPE + " TEXT,"
                + COLUMN_ACTIVITY_TIME + " TEXT,"
                + COLUMN_ACTIVITY_DURATION + " INTEGER,"
                + COLUMN_ACTIVITY_DATE + " TEXT" + ")")
        db.execSQL(CREATE_TABLE)
    }

    override fun onUpgrade(db: SQLiteDatabase, oldVersion: Int, newVersion: Int) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME)
        onCreate(db)
    }

    fun addDataInDatabase(activityType: String, date: String, time: String, duration: Int): Long {
        val db = this.writableDatabase
        val values = ContentValues()
        values.put(COLUMN_ACTIVITY_TYPE, activityType)
        values.put(COLUMN_ACTIVITY_DATE, date)
        values.put(COLUMN_ACTIVITY_TIME, time)
        values.put(COLUMN_ACTIVITY_DURATION, duration)

        val id = db.insert(TABLE_NAME, null, values)
        db.close()
        return id
    }

    fun getDataCursor(): Cursor{
        val cursor = this.readableDatabase.rawQuery("SELECT * FROM " + TABLE_NAME, null);
        return cursor;
    }
}
