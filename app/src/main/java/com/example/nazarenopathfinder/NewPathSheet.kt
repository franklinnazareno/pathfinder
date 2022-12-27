package com.example.nazarenopathfinder

import android.os.Bundle
import android.text.Editable
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import com.example.nazarenopathfinder.databinding.FragmentNewPathSheetBinding
import com.google.android.material.bottomsheet.BottomSheetDialogFragment

class NewPathSheet(var pathItem: PathItem?) : BottomSheetDialogFragment() {

    private lateinit var binding: FragmentNewPathSheetBinding
    private lateinit var pathViewModel: PathViewModel

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val activity = requireActivity()

        if (pathItem != null) {
            binding.pathTitle.text = "Edit Task"
            val editable = Editable.Factory.getInstance()
            binding.name.text = editable.newEditable(pathItem!!.name)
            binding.source.text = editable.newEditable(pathItem!!.source)
            binding.destination.text = editable.newEditable(pathItem!!.destination)
            binding.description.text = editable.newEditable(pathItem!!.description)
        } else {
            binding.pathTitle.text = "New Task"
        }

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
        val name = binding.name.text.toString()
        val source = binding.source.text.toString()
        val destination = binding.destination.text.toString()
        val description = binding.description.text.toString()
        if (pathItem == null) {
            val newPath = PathItem(name, source, destination, description)
            pathViewModel.addPathItem(newPath)
        } else {
            pathViewModel.updatePathItem(pathItem!!.id, name, source, destination, description)
        }
        binding.name.setText("")
        binding.source.setText("")
        binding.destination.setText("")
        binding.description.setText("")
        dismiss()
    }
}