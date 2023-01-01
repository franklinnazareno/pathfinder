package com.example.nazarenopathfinder

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.navigation.NavController
import androidx.navigation.fragment.findNavController
import com.example.nazarenopathfinder.databinding.FragmentRegisterBinding

class RegisterFragment(): Fragment() {

    private lateinit var binding: FragmentRegisterBinding
    private lateinit var navController: NavController
    private val userViewModel: UserViewModel by viewModels {
        UserModelFactory((activity?.application as PathFinderApplication).userRepository)
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
        binding = FragmentRegisterBinding.inflate(inflater, container, false)

        binding.btnReg.setOnClickListener {
            registerUser()
        }

        return binding.root
    }

    private fun registerUser() {
        val firstName = binding.entryFirstName.text.toString()
        val lastName = binding.entryLastName.text.toString()
        val email = binding.entryEmail.text.toString()
        val password = binding.entryPassword.text.toString()
        if (firstName.isEmpty() || lastName.isEmpty() || email.isEmpty() || password.isEmpty()) {
            val toast = Toast.makeText(requireActivity(),"Please do not leave any fields blank", Toast.LENGTH_SHORT)
            toast.show()
        } else if (password.length < 8) {
            val toast = Toast.makeText(requireActivity(),"Password must be at least 8 characters long", Toast.LENGTH_SHORT)
            toast.show()
        } else {
            val newUser = User(firstName, lastName, email, password)
            userViewModel.register(newUser)
            binding.entryFirstName.setText("")
            binding.entryLastName.setText("")
            binding.entryEmail.setText("")
            binding.entryPassword.setText("")
            val toast = Toast.makeText(requireActivity(),"Registration successful! Now login your newly created account!", Toast.LENGTH_SHORT)
            toast.show()
            navController.navigate(R.id.loginFragment)
        }
    }

}