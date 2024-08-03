package com.example.videoplataforma

import android.os.Bundle
import android.widget.Button
import android.widget.ListView
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {

    private lateinit var plataformas: ArrayList<String>
    private lateinit var adaptador: CustomAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val listView: ListView = findViewById(R.id.listView)
        val addButton: Button = findViewById(R.id.addButton)

        plataformas = arrayListOf("YouTube", "Vimeo", "Dailymotion")

        adaptador = CustomAdapter(this, plataformas)
        listView.adapter = adaptador

        addButton.setOnClickListener {
            // Agregar una nueva plataforma y seleccionarla para edición inmediata
            plataformas.add("Nueva Plataforma")
            adaptador.selectLastPosition()
        }
    }
}
