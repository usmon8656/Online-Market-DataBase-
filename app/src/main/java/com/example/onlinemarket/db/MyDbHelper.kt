package com.example.onlinemarket.db

import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import com.example.onlinemarket.Models.Buyurtma
import com.example.onlinemarket.Models.Sotuvchi
import com.example.onlinemarket.Models.Xaridor

class MyDbHelper(val context: Context):SQLiteOpenHelper(context , DB_NAME , null , DB_VERSION),MyDbInterface {

    companion object{
        val DB_NAME = "buyurtma_db"
        val DB_VERSION = 1

        val DB_BUYURTMA="buyurtma_table"
        val DB_SOTUVCHI="sotuvchi_table"
        val DB_XARIDOR="xaridor_table"


    }

    override fun onCreate(p0: SQLiteDatabase?) {
        val sotuvciQuery = "create table $DB_SOTUVCHI(id integer not null primary key autoincrement unique, name text not null, number text not null)"
        val xaridorQuery = "create table $DB_XARIDOR(id integer not null primary key autoincrement unique, name text not null, number text not null,address text not null)"
        val buyurtmaQuery = "create table $DB_BUYURTMA(id integer not null primary key autoincrement unique, name text not null,date text not null , sotuvchi_id integer not null, xaridor_id integer not null, foreign key (sotuvchi_id) references $DB_SOTUVCHI(id),foreign key (xaridor_id) references $DB_XARIDOR(id))"

        p0?.execSQL(sotuvciQuery)
        p0?.execSQL(xaridorQuery)
        p0?.execSQL(buyurtmaQuery)
    }

    override fun onUpgrade(p0: SQLiteDatabase?, p1: Int, p2: Int) {
        TODO("Not yet implemented")
    }

    override fun addSotuvchi(sotuvchi: Sotuvchi) {
        val database = this.writableDatabase
        val contentValues = ContentValues()
        contentValues.put("name" , sotuvchi.name)
        contentValues.put("number" , sotuvchi.phone)
        database.insert("$DB_SOTUVCHI",null,contentValues)
        database.close()
    }

    override fun getAllSotuvchi(): List<Sotuvchi> {
        val database = this.readableDatabase
        val list = ArrayList<Sotuvchi>()
        val query = "select * from $DB_SOTUVCHI"
        val cursor = database.rawQuery(query,null)
        if (cursor.moveToFirst()){
            do {
                list.add(
                    Sotuvchi(
                        cursor.getInt(0),
                        cursor.getString(1),
                        cursor.getString(2)
                    )
                )
            }while (cursor.moveToNext())
        }
        return  list
    }

    override fun addXaridor(xaridor: Xaridor) {
        val database = this.writableDatabase
        val contentValues = ContentValues()
        contentValues.put("name" , xaridor.name)
        contentValues.put("number" , xaridor.phone)
        contentValues.put("address" , xaridor.adres)
        database.insert("$DB_XARIDOR",null,contentValues)
        database.close()
    }

    override fun getAllXaridor(): List<Xaridor> {
        val database = this.readableDatabase
        val list = ArrayList<Xaridor>()
        val query = "select * from $DB_XARIDOR"
        val cursor = database.rawQuery(query,null)
        if (cursor.moveToFirst()){
            do {
                list.add(
                    Xaridor(
                        cursor.getInt(0),
                        cursor.getString(1),
                        cursor.getString(2),
                        cursor.getString(3)
                    )
                )
            }while (cursor.moveToNext())
        }
        return  list
    }

    override fun addBuyurtma(buyurtma: Buyurtma) {
        val database = this.writableDatabase
        val contentValues = ContentValues()
        contentValues.put("name" , buyurtma.maxsulot)
        contentValues.put("date6" , buyurtma.data)
        contentValues.put("sotuvchi_id" , buyurtma.sotuvchi.id)
        contentValues.put("xaridor_id" , buyurtma.xaridor.id)
        database.insert("$DB_BUYURTMA",null,contentValues)
        database.close()
    }

    override fun getAllBuyurtma(): List<Buyurtma> {
        val database = this.readableDatabase
        val list = ArrayList<Buyurtma>()
        val query = "select * from $DB_BUYURTMA"
        val cursor = database.rawQuery(query,null)
        if (cursor.moveToFirst()){
            do {
                list.add(
                    Buyurtma(
                        cursor.getInt(0),
                        cursor.getString(1),
                        cursor.getString(2),
                        getSotuvchiById(cursor.getInt(3)),
                        getXaridorById(cursor.getInt(4))
                    )
                )
            }while (cursor.moveToNext())
        }
        return  list
    }

    override fun getXaridorById(id: Int): Xaridor {
        val database = this.readableDatabase
        val cursor = database.query(
            "$DB_XARIDOR",
            arrayOf(
                "id",
                "name",
                "number",
                "address"
            ),
            "id = ?",
            arrayOf(id.toString()),
            null, null, null
        )
        cursor.moveToFirst()
        val xaridor = Xaridor(
            cursor.getInt(0),
            cursor.getString(1),
            cursor.getString(2),
            cursor.getString(3)
        )
        return xaridor

    }

    override fun getSotuvchiById(id: Int): Sotuvchi {
        val database = this.readableDatabase
        val cursor = database.query(
            "$DB_SOTUVCHI",
            arrayOf(
                "id",
                "name",
                "number"
            ),
            "id = ?",
            arrayOf(id.toString()),
            null, null, null
        )
        cursor.moveToFirst()
        val sotuvchi = Sotuvchi(
            cursor.getInt(0),
            cursor.getString(1),
            cursor.getString(2),
        )
        return sotuvchi
    }
}