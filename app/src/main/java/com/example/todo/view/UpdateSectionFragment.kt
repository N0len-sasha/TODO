package com.example.todo.view

import android.content.Intent
import android.os.Bundle
import android.text.TextUtils
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.example.todo.MainFragmentView
import com.example.todo.R
import com.example.todo.databinding.ActivityMainBinding
import com.example.todo.databinding.FragmentUpdateSectionBinding
import com.example.todo.model.Task
import com.example.todo.viewModel.TaskViewModel

class UpdateSectionFragment : Fragment() {

    private lateinit var binding: FragmentUpdateSectionBinding
    private val viewModel: TaskViewModel by viewModels()
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        viewModel.id.observe(this) {
            binding.done2.setOnClickListener {
                updateDataToDatabase()
            }
        }
    }
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentUpdateSectionBinding.inflate(inflater)
        return binding.root
    }

    private fun updateDataToDatabase() {
        val name = binding.textView6.text.toString()
        val remind = binding.textView7.text.toString()


            if (inputCheck(name)) {
                val task = Task(viewModel.id.value!!, name, remind)
                viewModel.updateTask(task)
                Toast.makeText(requireContext(), "Добавлено!", Toast.LENGTH_LONG).show()
                findNavController().navigate(R.id.action_updateSectionFragment_to_mainFragment)
            } else {
                Toast.makeText(requireContext(), "Заполните поля!", Toast.LENGTH_LONG).show()
            }
        }
    }

    private fun inputCheck(name: String): Boolean {
        return !(TextUtils.isEmpty(name))
    }