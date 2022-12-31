package com.example.nazarenopathfinder

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.navigation.NavController
import androidx.navigation.findNavController
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.nazarenopathfinder.databinding.FragmentViewPathBinding


class ViewPathFragment : Fragment(), PathItemClickListener {

    private lateinit var binding: FragmentViewPathBinding
    private lateinit var navController: NavController
    private val pathViewModel: PathViewModel by viewModels {
        PathItemModelFactory((activity?.application as PathFinderApplication).repository)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        navController = findNavController()
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

        binding.clearPathsButton.setOnClickListener {
            pathViewModel.deleteAllPathItems()
            val toast = Toast.makeText(requireActivity(),"Deleted all paths successfully", Toast.LENGTH_SHORT)
            toast.show()
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

    override fun viewPathDetail(pathItem: PathItem) {
        val bundle = Bundle().apply {
            putString("name", pathItem.name)
            putString("source", pathItem.source)
            putString("destination", pathItem.destination)
            putString("description", pathItem.description)
        }
        view?.findNavController()?.navigate(R.id.action_viewPathFragment_to_pathDetailFragment, bundle)
    }
}
