package com.example.todo

import android.content.Intent
import android.os.Binder
import android.os.Bundle
import android.os.PersistableBundle
import androidx.appcompat.app.AppCompatActivity
import com.example.todo.databinding.AddSectionActivityBinding
import com.example.todo.databinding.SectionItemBinding

lateinit var binding: AddSectionActivityBinding

class AddSectionActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = AddSectionActivityBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.done.setOnClickListener {
            val name = binding.editName.text.toString()
            val intent = Intent(this, MainActivity::class.java)
            intent.putExtra("name", name)
            startActivity(intent)
        }
    }
}