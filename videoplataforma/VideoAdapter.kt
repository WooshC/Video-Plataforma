package com.example.videoplataforma

import android.content.Context
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

class VideoAdapter(context: Context, private val videos: ArrayList<String>) :
    ArrayAdapter<String>(context, R.layout.list_item_video, videos) {

    private var selectedPosition: Int? = null
    private var editModePosition: Int? = null

    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        val inflater = context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
        val rowView = convertView ?: inflater.inflate(R.layout.list_item_video, parent, false)

        val videoName = rowView.findViewById<TextView>(R.id.videoName)
        val editText = rowView.findViewById<EditText>(R.id.editVideoName)
        val editButton = rowView.findViewById<Button>(R.id.editButton)
        val deleteButton = rowView.findViewById<Button>(R.id.deleteButton)
        val buttonLayout = rowView.findViewById<LinearLayout>(R.id.buttonLayout)

        videoName.text = videos[position]

        // Manejar la visibilidad de los botones y EditText
        buttonLayout.isVisible = position == selectedPosition
        editText.isVisible = position == editModePosition

        videoName.setOnClickListener {
            selectedPosition = if (selectedPosition == position) null else position
            editModePosition = null
            notifyDataSetChanged()
        }

        editButton.setOnClickListener {
            if (editModePosition == position) {
                // Guardar los cambios
                videos[position] = editText.text.toString()
                videoName.text = editText.text.toString()
                videoName.isVisible = true
                editText.isVisible = false
                hideKeyboard(editText)
                editModePosition = null
                selectedPosition = null // Ocultar los botones después de guardar
            } else {
                // Habilitar la edición
                videoName.isVisible = false
                editText.isVisible = true
                editText.setText(videos[position])
                editText.requestFocus()
                showKeyboard(editText)
                editModePosition = position
            }
            notifyDataSetChanged()
        }

        deleteButton.setOnClickListener {
            videos.removeAt(position)
            selectedPosition = null
            editModePosition = null
            notifyDataSetChanged()
        }

        return rowView
    }

    // Método para seleccionar la última posición, útil para cuando se agrega un nuevo video
    fun selectLastPosition() {
        selectedPosition = videos.size - 1
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
