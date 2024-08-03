package com.example.videoplataforma

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.InputMethodManager
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.EditText
import android.widget.LinearLayout
import android.widget.TextView
import androidx.core.view.isVisible

class CustomAdapter(context: Context, private val plataformas: ArrayList<String>) :
    ArrayAdapter<String>(context, R.layout.list_item, plataformas) {

    private var selectedPosition: Int? = null
    private var editModePosition: Int? = null

    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        val inflater = context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
        val rowView = convertView ?: inflater.inflate(R.layout.list_item, parent, false)

        val platformName = rowView.findViewById<TextView>(R.id.platformName)
        val editText = rowView.findViewById<EditText>(R.id.editPlatformName)
        val editButton = rowView.findViewById<Button>(R.id.editButton)
        val deleteButton = rowView.findViewById<Button>(R.id.deleteButton)
        val viewVideosButton = rowView.findViewById<Button>(R.id.viewVideosButton)
        val buttonLayout = rowView.findViewById<LinearLayout>(R.id.buttonLayout)

        platformName.text = plataformas[position]

        // Manejar la visibilidad de los botones y EditText
        buttonLayout.isVisible = position == selectedPosition
        editText.isVisible = position == editModePosition

        platformName.setOnClickListener {
            selectedPosition = if (selectedPosition == position) null else position
            editModePosition = null
            notifyDataSetChanged()
        }

        editButton.setOnClickListener {
            if (editModePosition == position) {
                // Guardar los cambios
                plataformas[position] = editText.text.toString()
                platformName.text = editText.text.toString()
                platformName.isVisible = true
                editText.isVisible = false
                hideKeyboard(editText)
                editModePosition = null
                selectedPosition = null // Ocultar los botones después de guardar
            } else {
                // Habilitar la edición
                platformName.isVisible = false
                editText.isVisible = true
                editText.setText(plataformas[position])
                editText.requestFocus()
                showKeyboard(editText)
                editModePosition = position
            }
            notifyDataSetChanged()
        }

        deleteButton.setOnClickListener {
            plataformas.removeAt(position)
            selectedPosition = null
            editModePosition = null
            notifyDataSetChanged()
        }

        viewVideosButton.setOnClickListener {
            val intent = Intent(context, VideoListActivity::class.java)
            intent.putExtra("PLATFORM_NAME", plataformas[position])
            context.startActivity(intent)
        }

        return rowView
    }

    // Método para seleccionar la última posición, útil para cuando se agrega una nueva plataforma
    fun selectLastPosition() {
        selectedPosition = plataformas.size - 1
        notifyDataSetChanged()
    }

    private fun showKeyboard(view: View) {
        val imm = context.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        imm.showSoftInput(view, InputMethodManager.SHOW_IMPLICIT)
    }

    private fun hideKeyboard(view: View) {
        val imm = context.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        imm.hideSoftInputFromWindow(view.windowToken, 0)
    }
}
