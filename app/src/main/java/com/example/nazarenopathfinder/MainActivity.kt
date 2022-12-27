package com.example.nazarenopathfinder

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.nazarenopathfinder.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity(), PathItemClickListener {

    private lateinit var binding: ActivityMainBinding
    private lateinit var pathViewModel: PathViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        pathViewModel = ViewModelProvider(this).get(PathViewModel::class.java)
        binding.newPathButton.setOnClickListener {
            NewPathSheet(null).show(supportFragmentManager, "newPathTag")
        }
        setRecyclerView()
    }

    private fun setRecyclerView() {
        val mainActivity = this
        pathViewModel.pathItems.observe(this) {
            binding.pathFinderRecyclerView.apply {
                layoutManager = LinearLayoutManager(applicationContext)
                adapter = PathItemAdapter(it, mainActivity)
            }
        }
    }

    override fun editPathItem(pathItem: PathItem) {
        NewPathSheet(pathItem).show(supportFragmentManager, "newPathTag")
    }
}