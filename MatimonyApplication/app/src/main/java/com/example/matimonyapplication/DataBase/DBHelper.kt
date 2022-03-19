package com.example.matimonyapplication.DataBase

import android.annotation.SuppressLint
import android.content.ContentValues
import android.content.Context
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import android.util.Log
import com.example.matimonyapplication.model.HomeModel
import java.lang.Exception

class DBHelper(context: Context, factory: SQLiteDatabase.CursorFactory?) :
    SQLiteOpenHelper(context, DATABASE_NAME, factory, DATABASE_VERSION) {
    var info = ArrayList<HomeModel>()
    override fun onCreate(db: SQLiteDatabase?) {
//        Query for Create Table
        val query1 = ("CREATE TABLE " + TABLE_NAME + " ("
                + ID_COL + " INTEGER PRIMARY KEY, " +
                NAME_COl + " TEXT," +
                AGE_COL + " TEXT," +
                HEIGHT_COL + " TEXT," +
                LANGUAGE_COL + " TEXT," +
                CAST_COL + " TEXT," +
                PROFESSIONAL_COL + " TEXT," +
                ADDRESS_COL + " TEXT," +
                PROFILE_ONE_COL + " TEXT, "+
                PROFILE_TWO_COL + " TEXT ,"+
                PROFILE_THREE_COL + " TEXT " + ")")
        db?.execSQL(query1)

    }

    // function for store data to tABLE
    fun addName(model: HomeModel) {
        val values = ContentValues()
        values.put(NAME_COl, model.name)
        values.put(AGE_COL, model.age)
        values.put(HEIGHT_COL, model.height)
        values.put(LANGUAGE_COL, model.language)
        values.put(CAST_COL, model.cast)
        values.put(PROFESSIONAL_COL, model.proffession)
        values.put(ADDRESS_COL, model.address)
        values.put(PROFILE_ONE_COL, model.profileImg1)
        values.put(PROFILE_TWO_COL, model.profileImg2)
        values.put(PROFILE_THREE_COL, model.profileImg3)

        println(values)
        try {
            val db = this.writableDatabase
            db.insert(TABLE_NAME, null, values)
            db.close()

        } catch (e: Exception) {

        }
    }

//    Fetch data from query
    @SuppressLint("Range")
    fun getName(): ArrayList<HomeModel> {
        Log.d(ContentValues.TAG, "hello")

        val query = "SELECT * FROM $TABLE_NAME LIMIT 5"
        val db = this.readableDatabase
        val cursor: Cursor?
        try {
            cursor = db.rawQuery(query, null)
        } catch (e: Exception) {
            e.printStackTrace()
            db.execSQL(query)
            return ArrayList()
        }
        var name: String
        var age: String
        var height: String
        var language: String
        var proffession: String
        var address: String
        var cast: String
        var profileImg1: String
    var profileImg2: String
    var profileImg3: String

        if (cursor.moveToFirst()) {
            do {
                name = cursor.getString(cursor.getColumnIndex(NAME_COl))
                age = cursor.getString(cursor.getColumnIndex(AGE_COL))
                height = cursor.getString(cursor.getColumnIndex(HEIGHT_COL))
                language = cursor.getString(cursor.getColumnIndex(LANGUAGE_COL))
                proffession = cursor.getString(cursor.getColumnIndex(PROFESSIONAL_COL))
                address = cursor.getString(cursor.getColumnIndex(ADDRESS_COL))
                cast = cursor.getString(cursor.getColumnIndex(CAST_COL))
                profileImg1 = cursor.getString(cursor.getColumnIndex(PROFILE_ONE_COL))
                profileImg2 = cursor.getString(cursor.getColumnIndex(PROFILE_TWO_COL))
                profileImg3 = cursor.getString(cursor.getColumnIndex(PROFILE_THREE_COL))
                val da = HomeModel(name, age, height, language, proffession, address, cast, profileImg1,profileImg2,profileImg3)
                info.add(da)

            } while (cursor.moveToNext())
        }

        return info

    }

    override fun onUpgrade(db: SQLiteDatabase?, p1: Int, p2: Int) {
        db?.execSQL("DROP TABLE IF EXISTS $TABLE_NAME")
        onCreate(db)
    }


    // create companion class for avoid memory wastage , create only single instance
    companion object {
        private val DATABASE_NAME = "MATRIDB"
        private val DATABASE_VERSION = 1
        val TABLE_NAME = "vr_table"
        val ID_COL = "id"
        val NAME_COl = "name"
        val AGE_COL = "age"
        val HEIGHT_COL = "height"
        val LANGUAGE_COL = "language"
        val CAST_COL = "cast"
        val PROFESSIONAL_COL = "professional"
        val ADDRESS_COL = "address"
        val PROFILE_ONE_COL = "profileone"
        val PROFILE_TWO_COL = "profiletwo"
        val PROFILE_THREE_COL = "profilethree"
    }
}
