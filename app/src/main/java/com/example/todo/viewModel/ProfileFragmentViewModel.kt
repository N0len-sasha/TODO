package com.example.todo.viewModel

import android.view.View
import androidx.lifecycle.ViewModel
import com.example.todo.databinding.ProfileActivityBinding

class ProfileFragmentViewModel : ViewModel (){

    fun checkConfirm(binding: ProfileActivityBinding) {
        if (binding.confirm.visibility == View.GONE) {
            binding.confirm.visibility = View.VISIBLE
            binding.password.isEnabled = true
            binding.login.isEnabled = true
        }
    }

    fun profileLogOut(binding: ProfileActivityBinding) {
        if (binding.confirm.visibility == View.VISIBLE){
            binding.confirm.visibility = View.GONE
            binding.password.isEnabled = false
            binding.login.isEnabled = false
        }
    }
}