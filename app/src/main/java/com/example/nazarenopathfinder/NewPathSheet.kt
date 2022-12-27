package com.example.nazarenopathfinder

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import com.example.nazarenopathfinder.databinding.FragmentNewPathSheetBinding
import com.google.android.material.bottomsheet.BottomSheetDialogFragment

class NewPathSheet : BottomSheetDialogFragment() {

    private lateinit var binding: FragmentNewPathSheetBinding
    private lateinit var pathViewModel: PathViewModel

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val activity = requireActivity()
        pathViewModel = ViewModelProvider(activity).get(PathViewModel::class.java)
        binding.saveButton.setOnClickListener {
            saveAction()
        }
    }


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentNewPathSheetBinding.inflate(inflater,container,false)
        return binding.root
    }

    private fun saveAction() {
        pathViewModel.name.value = binding.name.text.toString()
        pathViewModel.source.value = binding.source.text.toString()
        pathViewModel.destination.value = binding.destination.text.toString()
        pathViewModel.description.value = binding.description.text.toString()
        binding.name.setText("")
        binding.source.setText("")
        binding.destination.setText("")
        binding.description.setText("")
        dismiss()
    }
}