package com.example.nazarenopathfinder

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.nazarenopathfinder.databinding.FragmentViewPathBinding


class ViewPathFragment : Fragment(), PathItemClickListener {

    private lateinit var binding: FragmentViewPathBinding
    private val pathViewModel: PathViewModel by viewModels {
        PathItemModelFactory((activity?.application as PathFinderApplication).repository)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentViewPathBinding.inflate(inflater, container, false)

        // Set the click listener for the new path button
        binding.newPathButton.setOnClickListener {
            val repository = (activity?.application as PathFinderApplication).repository
            NewPathSheet(null, repository).show(parentFragmentManager, "newPathTag")
        }

        // Set up the recycler view
        setRecyclerView()

        return binding.root
    }

    private fun setRecyclerView() {
        val viewPathFragment = this
        pathViewModel.pathItems.observe(viewLifecycleOwner) {
            binding.pathFinderRecyclerView.apply {
                adapter = PathItemAdapter(it, viewPathFragment)
                layoutManager = LinearLayoutManager(context)
            }
        }
    }

    override fun editPathItem(pathItem: PathItem) {
        val repository = (activity?.application as PathFinderApplication).repository
        NewPathSheet(pathItem, repository).show(parentFragmentManager, "newPathTag")
    }
}
