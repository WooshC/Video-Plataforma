package com.example.videoplataforma

import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.ListView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class VideoListActivity : AppCompatActivity() {

    private lateinit var videoAdapter: VideoAdapter
    private val videos = arrayListOf("Video 1", "Video 2", "Video 3")

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_video_list)

        val platformName = intent.getStringExtra("PLATFORM_NAME")

        // Establecer el título dinámicamente
        val titleTextView = findViewById<TextView>(R.id.title)
        titleTextView.text = platformName

        val listView = findViewById<ListView>(R.id.listView)
        val addVideoButton = findViewById<Button>(R.id.addVideoButton)

        videoAdapter = VideoAdapter(this, videos)
        listView.adapter = videoAdapter

        addVideoButton.setOnClickListener {
            // Agregar un nuevo video con un nombre por defecto
            videos.add("Nuevo Video")
            videoAdapter.notifyDataSetChanged()
            // Seleccionar la última posición para que los botones se muestren
            videoAdapter.selectLastPosition()
        }
    }
}
