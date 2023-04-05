package com.example.todo

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.example.todo.databinding.ProfileActivityBinding
import com.example.todo.databinding.RegistrationActivityBinding
import com.google.firebase.auth.FirebaseAuth

class ProfileFragment : Fragment() {
    lateinit var binding: ProfileActivityBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = ProfileActivityBinding.inflate(inflater, container, false)
        binding.password.isEnabled = false
        binding.login.isEnabled = false
        binding.edit.setOnClickListener{
            if (binding.confirm.visibility == View.GONE) {
                binding.confirm.visibility = View.VISIBLE
                binding.password.isEnabled = true
                binding.login.isEnabled = true
            }
        }
        binding.confirm.setOnClickListener {
            if (binding.confirm.visibility == View.VISIBLE){
                binding.confirm.visibility = View.GONE
                binding.password.isEnabled = false
                binding.login.isEnabled = false
            }
        }
        binding.logOut.setOnClickListener {
            FirebaseAuth.getInstance().signOut()
            findNavController().navigate(R.id.action_profileFragment_to_authorizationFragment2)
        }
        return binding.root
    }
}