package com.example.nazarenopathfinder

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.navigation.NavController
import androidx.navigation.findNavController
import androidx.navigation.fragment.findNavController
import com.example.nazarenopathfinder.databinding.FragmentHomeBinding

class HomeFragment : Fragment() {

    private lateinit var navController: NavController

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        navController = findNavController()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val binding = DataBindingUtil.inflate<FragmentHomeBinding>(inflater, R.layout.fragment_home, container, false)

        binding.addPathButton.setOnClickListener {
            val repository = (activity?.application as PathFinderApplication).pathItemRepository
            navController.navigate(R.id.viewPathFragment)
            NewPathSheet(null, repository).show(parentFragmentManager, "newPathTag")
        }

        binding.viewPathButton.setOnClickListener {
            view: View -> view.findNavController().navigate(R.id.action_homeFragment_to_viewPathFragment)
        }

        return binding.root
    }
}