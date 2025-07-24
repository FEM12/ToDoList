package com.example.todolist

import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.EditText
import android.widget.ListView
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class MainActivity : AppCompatActivity() {

    lateinit var tarea: EditText
    lateinit var mensaje: TextView
    lateinit var data: ListView

    override fun onCreate(savedInstanceState: Bundle?){
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val data = findViewById<ListView>(R.id.LvDatos)
        val opciones = listOf("Opción 1", "Opción 2", "Opción 3")

        val adapter = CustomAdapter(this, opciones)
        data.adapter = adapter

    }
}