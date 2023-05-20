package com.example.todo.view

import android.os.Bundle
import android.text.TextUtils
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.example.todo.R
import com.example.todo.databinding.CreateTaskActivityBinding
import com.example.todo.model.Task
import com.example.todo.viewModel.TaskViewModel

class CreateTask : Fragment() {

    private lateinit var mTaskViewModel: TaskViewModel

    private lateinit var binding: CreateTaskActivityBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = CreateTaskActivityBinding.inflate(inflater, container, false)
        mTaskViewModel = ViewModelProvider(this)[TaskViewModel::class.java]
        binding.done2.setOnClickListener {
            insertDataToDatabase()
        }
        return binding.root
    }

    private fun insertDataToDatabase() {
        val name = binding.textView6.text.toString()
        val remind = binding.textView7.text.toString()
        val comment = binding.textView15.text.toString()
        val writeText = binding.textView17.text.toString()

        if (inputCheck(name)) {
            val task = Task(0, name, remind, comment, writeText)
            mTaskViewModel.addTask(task)
            Toast.makeText(requireContext(), "Добавлено!", Toast.LENGTH_LONG).show()
            findNavController().navigate(R.id.action_createTask_to_mainFragment)
        } else {
            Toast.makeText(requireContext(), "Заполните поля!", Toast.LENGTH_LONG).show()
        }
    }

    private fun inputCheck(name: String): Boolean {
        return !(TextUtils.isEmpty(name))
    }
}