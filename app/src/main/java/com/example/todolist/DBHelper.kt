package com.example.todolist

import android.content.ContentValues
import android.content.Context
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

class DBHelper(context: Context) : SQLiteOpenHelper(context, "ToDoList.db", null, 1) {

    override fun onCreate(db: SQLiteDatabase) {
        db.execSQL(
            """
            CREATE TABLE IF NOT EXISTS tareas (
                id INTEGER PRIMARY KEY AUTOINCREMENT,
                tarea TEXT NOT NULL,
                estado BOOLEAN NOT NULL
            )
            """
        )
    }

    override fun onUpgrade(db: SQLiteDatabase, oldVersion: Int, newVersion: Int) {
        db.execSQL("DROP TABLE IF EXISTS tareas")
        onCreate(db)
    }

    fun insertarTarea(tarea: String): Long {

        val db = writableDatabase
        val valores = ContentValues()

        valores.put("tarea", tarea)
        valores.put("estado", 0)
        return db.insert("tareas", null, valores)

    }

    fun obtenerTareas(): List<Tarea> {

        val lista = mutableListOf<Tarea>()
        val db = readableDatabase
        val cursor = db.rawQuery("SELECT * FROM tareas", null)

        while (cursor.moveToNext()) {
            val id = cursor.getInt(cursor.getColumnIndexOrThrow("id"))
            val nombre = cursor.getString(cursor.getColumnIndexOrThrow("tarea"))
            val estado = cursor.getInt(cursor.getColumnIndexOrThrow("estado")) == 1
            lista.add(Tarea(id, nombre, estado))
        }

        cursor.close()
        return lista

    }

    fun actualizarEstadoTarea(id: Int, estado: Boolean): Int {

        val db = writableDatabase
        val valores = ContentValues().apply {
            put("estado", if (estado) 1 else 0)
        }

        return db.update("tareas", valores, "id = ?", arrayOf(id.toString()))

    }

    fun eliminarTarea(id: Int): Int {

        val db = writableDatabase
        return db.delete("tareas", "id=?", arrayOf(id.toString()))

    }

}
