package com.example.todo

import android.content.Intent
import android.os.Bundle
import android.os.PersistableBundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.example.todo.databinding.ProfileActivityBinding
import com.google.firebase.auth.FirebaseAuth


class ProfileActivity : AppCompatActivity(){
    lateinit var binding: ProfileActivityBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ProfileActivityBinding.inflate(layoutInflater)
        setContentView(binding.root)
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
            val intent = Intent(this, AuthorizationFragment::class.java)
            startActivity(intent)
        }
    }
}