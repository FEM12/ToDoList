package com.example.todolist

import android.content.Context
import android.graphics.Paint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.CheckBox
import android.widget.TextView
import android.widget.Toast

class CustomAdapter(
    private val context: Context,
    private val items: MutableList<Tarea>,
    private val dbHelper: DBHelper
) : ArrayAdapter<Tarea>(context, 0, items) {


    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {

        val itemView = convertView ?: LayoutInflater.from(context)
            .inflate(R.layout.list_item, parent, false)

        val checkBox = itemView.findViewById<CheckBox>(R.id.itemCheckBox)
        val textView = itemView.findViewById<TextView>(R.id.itemText)
        val button = itemView.findViewById<Button>(R.id.itemButton)

        val tarea = items[position]
        textView.text = tarea.tarea

        //Botón para eliminar una tarea de la BDD
        button.setOnClickListener {
            dbHelper.eliminarTarea(tarea.id)
            items.removeAt(position)
            notifyDataSetChanged()
            Toast.makeText(context, "Tarea eliminada exitosamente", Toast.LENGTH_SHORT).show()
        }

        if(tarea.estado) {
            textView.paintFlags = textView.paintFlags or Paint.STRIKE_THRU_TEXT_FLAG
            checkBox.isChecked = true
            checkBox.isEnabled = false
        }

        //Checkbox para cambiar el estado de la tarea y el diseño del texto
        checkBox.setOnCheckedChangeListener { _, isChecked ->
            textView.paintFlags = textView.paintFlags or Paint.STRIKE_THRU_TEXT_FLAG
            dbHelper.actualizarEstadoTarea(tarea.id, isChecked)
            checkBox.isEnabled = false
            Toast.makeText(context, "Tarea finalizada", Toast.LENGTH_SHORT).show()

        }

        return itemView

    }
}
