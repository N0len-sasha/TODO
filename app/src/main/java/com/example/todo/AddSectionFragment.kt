package com.example.todo

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.FragmentTransaction
import androidx.navigation.fragment.findNavController
import com.example.todo.databinding.AddSectionActivityBinding
import com.example.todo.databinding.AuthorizationActivityBinding


class AddSectionFragment : Fragment() {
    private lateinit var binding: AddSectionActivityBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val bundle = Bundle()
        binding = AddSectionActivityBinding.inflate(inflater, container, false)
        binding.done.setOnClickListener {
            val name = binding.editName.text.toString()
            bundle.putString("Name", name)
            findNavController().navigate(R.id.action_addSectionFragment_to_mainFragment, bundle)
        }
        return binding.root
    }
}