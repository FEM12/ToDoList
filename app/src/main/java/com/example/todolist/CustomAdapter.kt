package com.example.todolist

import android.content.Context
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
    private val items: List<String>
) : ArrayAdapter<String>(context, 0, items) {

    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        val itemView = convertView ?: LayoutInflater.from(context)
            .inflate(R.layout.list_item, parent, false)

        val checkBox = itemView.findViewById<CheckBox>(R.id.itemCheckBox)
        val textView = itemView.findViewById<TextView>(R.id.itemText)
        val button = itemView.findViewById<Button>(R.id.itemButton)

        val item = items[position]
        textView.text = item

        // acción del botón
        button.setOnClickListener {
            Toast.makeText(context, "Botón de: $item", Toast.LENGTH_SHORT).show()
        }

        checkBox.setOnCheckedChangeListener { _, isChecked ->
            // Guardar estado o realizar acción
        }

        return itemView
    }
}
