package com.example.todo

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.todo.databinding.SectionItemBinding

class SectionItem : Fragment() {
    private lateinit var binding: SectionItemBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = SectionItemBinding.inflate(inflater, container, false)
        return binding.root
    }
}
