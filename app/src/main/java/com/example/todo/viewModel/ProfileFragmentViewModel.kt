package com.example.todo.viewModel

import android.view.View
import androidx.lifecycle.ViewModel
import androidx.navigation.fragment.findNavController
import com.example.todo.R
import com.example.todo.databinding.ProfileActivityBinding
import com.google.firebase.auth.FirebaseAuth
import androidx.navigation.findNavController

class ProfileFragmentViewModel : ViewModel (){

    fun checkConfirm(binding: ProfileActivityBinding) {
        if (binding.confirm.visibility == View.GONE) {
            binding.confirm.visibility = View.VISIBLE
            binding.password.isEnabled = true
            binding.login.isEnabled = true
        }
    }

    fun profileEdit(binding: ProfileActivityBinding) {
        if (binding.confirm.visibility == View.VISIBLE){
            binding.confirm.visibility = View.GONE
            binding.password.isEnabled = false
            binding.login.isEnabled = false
        }
    }

    fun profileLogOut(binding: ProfileActivityBinding) {
        FirebaseAuth.getInstance().signOut()
    }
}