package com.example.secondhomework_pages

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

class MyDatabaseHelper(val context: Context, name: String, version: Int) :
    SQLiteOpenHelper(context, name, null, version) {

    private val createAccount = "create table Account (" +
            " id integer primary key autoincrement," +
            "emailAccount text," +
            "password text)"


    override fun onCreate(db: SQLiteDatabase) {
        db.execSQL(createAccount)
    }

    override fun onUpgrade(
        db: SQLiteDatabase?,
        oldVersion: Int,
        newVersion: Int
    ) {
        TODO("Not yet implemented")
    }
}