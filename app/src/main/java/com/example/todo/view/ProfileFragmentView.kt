package com.example.todo.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.example.todo.R
import com.example.todo.databinding.ProfileActivityBinding
import com.example.todo.viewModel.ProfileFragmentViewModel
import com.google.firebase.auth.FirebaseAuth

class ProfileFragmentView : Fragment() {
    lateinit var binding: ProfileActivityBinding
    private lateinit var viewModel: ProfileFragmentViewModel
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = ProfileActivityBinding.inflate(inflater, container, false)
        binding.password.isEnabled = false
        binding.login.isEnabled = false
        val provider = ViewModelProvider(this)
        viewModel = provider[ProfileFragmentViewModel::class.java]

        binding.edit.setOnClickListener{
            viewModel.profileLogOut(binding)
        }

        binding.confirm.setOnClickListener {
            viewModel.checkConfirm(binding)
        }

        binding.logOut.setOnClickListener {
            FirebaseAuth.getInstance().signOut()
            findNavController().navigate(R.id.action_profileFragment_to_authorizationFragment2)
        }
        return binding.root
    }
}