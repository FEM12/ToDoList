package com.example.todolist

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.ListView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {

    lateinit var dbHelper: DBHelper
    lateinit var tarea: EditText
    lateinit var guardar: Button
    lateinit var data: ListView

    override fun onCreate(savedInstanceState: Bundle?){

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        dbHelper = DBHelper(this)
        guardar = findViewById(R.id.BtnAgregar)
        tarea = findViewById(R.id.TxtTarea)
        data = findViewById(R.id.LvDatos)

        //Botón para guardar los datos del campo de texto en la BDD
        guardar.setOnClickListener {

            val textoTarea = tarea.text.toString()
            if(!textoTarea.equals("")) {
                dbHelper.insertarTarea(textoTarea)
                Toast.makeText(this, "Tarea registrada exitosamente", Toast.LENGTH_SHORT).show()
                actualizarLista()
            }
            else { Toast.makeText(this, "Error: Campo de texto vacío", Toast.LENGTH_SHORT).show() }

        }

        actualizarLista()

    }

    private fun actualizarLista() {
        val listaTareas = dbHelper.obtenerTareas().toMutableList()
        val adapter = CustomAdapter(this, listaTareas, dbHelper)
        data.adapter = adapter
    }

}