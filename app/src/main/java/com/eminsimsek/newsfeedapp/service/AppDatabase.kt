package com.eminsimsek.newsfeedapp.service

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

class AppDatabase(context: Context?) : SQLiteOpenHelper(context, "appDatabase.sqlite", null, 1) {


    override fun onCreate(db: SQLiteDatabase?) {
        db?.execSQL("CREATE TABLE \"Favorite\" (\n" +
                "\t\"publishedAt\"\tTEXT,\n" +
                "\t\"author\"\tTEXT,\n" +
                "\t\"urlToImage\"\tTEXT,\n" +
                "\t\"description\"\tTEXT,\n" +
                "\t\"source\"\tTEXT,\n" +
                "\t\"title\"\tTEXT,\n" +
                "\t\"url\"\tTEXT UNIQUE,\n" +
                "\t\"content\"\tTEXT,\n" +
                "\t\"articleID\"\tINTEGER NOT NULL,\n" +
                "\tPRIMARY KEY(\"articleID\" AUTOINCREMENT)\n" +
                ");")
    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        db?.execSQL("DROP TABLE IF EXISTS Favorite")
        onCreate(db)
    }


}