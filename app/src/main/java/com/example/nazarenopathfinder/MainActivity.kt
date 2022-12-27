package com.example.nazarenopathfinder

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import com.example.nazarenopathfinder.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var pathViewModel: PathViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        pathViewModel = ViewModelProvider(this).get(PathViewModel::class.java)
        binding.newPathButton.setOnClickListener {
            NewPathSheet().show(supportFragmentManager, "newPathTag")
        }

        pathViewModel.name.observe(this) {
            binding.pathName.text = String.format("Path Name: %s", it)
        }

        pathViewModel.source.observe(this) {
            binding.pathSource.text = String.format("Path Source: %s", it)
        }

        pathViewModel.destination.observe(this) {
            binding.pathDestination.text = String.format("Path Destination: %s", it)
        }

        pathViewModel.description.observe(this) {
            binding.pathDescription.text = String.format("Path Description: %s", it)
        }
    }
}