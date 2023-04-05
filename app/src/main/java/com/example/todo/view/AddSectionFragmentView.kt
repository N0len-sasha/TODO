package com.example.todo.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.example.todo.R
import com.example.todo.databinding.AddSectionActivityBinding
import com.example.todo.viewModel.ProfileFragmentViewModel


class AddSectionFragmentView : Fragment() {
    private lateinit var binding: AddSectionActivityBinding
    private lateinit var viewModel: ProfileFragmentViewModel
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?):
            View? {
        val provider = ViewModelProvider(this)
        viewModel = provider[ProfileFragmentViewModel::class.java]
        binding = AddSectionActivityBinding.inflate(inflater, container, false)

        binding.done.setOnClickListener {
            val name = binding.editName.text.toString()
            findNavController().navigate(R.id.action_addSectionFragment_to_mainFragment)
        }
        return binding.root
    }
}
