package com.example.todo.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.navigation.Navigation.findNavController
import androidx.navigation.fragment.findNavController
import com.example.todo.R
import com.example.todo.databinding.ProfileActivityBinding
import com.example.todo.model.FolderDAOModel
import com.example.todo.viewModel.ProfileFragmentViewModel

class MainAppActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }
}