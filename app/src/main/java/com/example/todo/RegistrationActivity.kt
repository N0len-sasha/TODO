package com.example.todo

import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import android.widget.EditText
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.example.todo.databinding.AuthorizationActivityBinding
import com.example.todo.databinding.RegistrationActivityBinding

class RegistrationActivity : AppCompatActivity() {
    private lateinit var binding: RegistrationActivityBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = RegistrationActivityBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.confirm.setOnClickListener {
            val intent = Intent(this, AuthorizationActivity::class.java)
            startActivity(intent)
        }
    }
}