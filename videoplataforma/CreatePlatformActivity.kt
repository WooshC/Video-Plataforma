package com.example.videoplataforma

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import android.widget.Button
import android.widget.EditText

class CreatePlatformActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_create_platform)

        val editTextName: EditText = findViewById(R.id.editTextName)
        val editTextTotalVideos: EditText = findViewById(R.id.editTextTotalVideos)
        val editTextNumeroCuentas: EditText = findViewById(R.id.editTextNumeroCuentas)
        val editTextSubscriptionFee: EditText = findViewById(R.id.editTextSubscriptionFee)
        val editTextCreationDate: EditText = findViewById(R.id.editTextCreationDate)
        val btnRegister: Button = findViewById(R.id.btnRegister)

        btnRegister.setOnClickListener {
            // Aquí puedes añadir la lógica para registrar la plataforma
            // Por ahora, solo limpiaremos los campos de texto
            editTextName.text.clear()
            editTextTotalVideos.text.clear()
            editTextNumeroCuentas.text.clear()
            editTextSubscriptionFee.text.clear()
            editTextCreationDate.text.clear()
        }
    }
}

