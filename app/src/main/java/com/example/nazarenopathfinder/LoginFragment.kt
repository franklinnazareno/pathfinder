package com.example.nazarenopathfinder

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.viewModels
import androidx.lifecycle.viewModelScope
import androidx.navigation.NavController
import androidx.navigation.findNavController
import androidx.navigation.fragment.findNavController
import com.example.nazarenopathfinder.databinding.FragmentLoginBinding
import kotlinx.coroutines.launch


class LoginFragment : Fragment() {

    private lateinit var binding: FragmentLoginBinding
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
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_login, container, false)

        binding.btnLogin.setOnClickListener {
            val email = binding.entryEmail.text.toString()
            val password = binding.entryPassword.text.toString()
            try {
                userViewModel.viewModelScope.launch {
                    val user = userViewModel.login(email)
                    if (user != null && user.email == email && user.password == password) {
                        binding.entryEmail.setText("")
                        binding.entryPassword.setText("")
                        val toast = Toast.makeText(requireActivity(),"Welcome, ${user.firstName} ${user.lastName}", Toast.LENGTH_SHORT)
                        toast.show()
                        val intent = Intent(requireActivity(), MainActivity::class.java)
                        intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
                        activity?.finish()
                        startActivity(intent)

                    } else {
                        val toast = Toast.makeText(requireActivity(),"Invalid credentials", Toast.LENGTH_SHORT)
                        toast.show()
                    }
                }
            } catch (e: NoSuchElementException) {
                val toast = Toast.makeText(requireActivity(),"Invalid credentials", Toast.LENGTH_SHORT)
                toast.show()
            }
        }

        binding.btnReg.setOnClickListener{
            view: View -> view.findNavController().navigate(R.id.action_loginFragment_to_registerFragment)
        }

        return binding.root
    }
}