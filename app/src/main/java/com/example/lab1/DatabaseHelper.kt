package com.example.lab1

import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

class DatabaseHelper(context: Context) : SQLiteOpenHelper(context, DATABASE_NAME, null, DATABASE_VERSION) {

    companion object {
        private const val DATABASE_NAME = "TrainRoutes.db"
        private const val DATABASE_VERSION = 1
        private const val TABLE_NAME = "routes"
        private const val COLUMN_ID = "id"
        private const val COLUMN_FROM = "from_city"
        private const val COLUMN_TO = "to_city"
        private const val COLUMN_TIME = "train_time"
    }

    override fun onCreate(db: SQLiteDatabase) {
        val createTableQuery = ("CREATE TABLE $TABLE_NAME ("
                + "$COLUMN_ID INTEGER PRIMARY KEY AUTOINCREMENT, "
                + "$COLUMN_FROM TEXT, "
                + "$COLUMN_TO TEXT, "
                + "$COLUMN_TIME TEXT)")
        db.execSQL(createTableQuery)
    }

    override fun onUpgrade(db: SQLiteDatabase, oldVersion: Int, newVersion: Int) {
        db.execSQL("DROP TABLE IF EXISTS $TABLE_NAME")
        onCreate(db)
    }

    fun insertRoute(from: String, to: String, time: String): Boolean {
        val db = this.writableDatabase
        val values = ContentValues()
        values.put(COLUMN_FROM, from)
        values.put(COLUMN_TO, to)
        values.put(COLUMN_TIME, time)

        val result = db.insert(TABLE_NAME, null, values)
        db.close()
        return result != -1L
    }

    fun getAllRoutes(): List<String> {
        val routeList = ArrayList<String>()
        val db = this.readableDatabase
        val cursor = db.rawQuery("SELECT * FROM $TABLE_NAME", null)

        if (cursor.moveToFirst()) {
            do {
                val from = cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_FROM))
                val to = cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_TO))
                val time = cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_TIME))
                routeList.add("Маршрут: $from — $to\nЧас: $time")
            } while (cursor.moveToNext())
        }
        cursor.close()
        db.close()
        return routeList
    }
}