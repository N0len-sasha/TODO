package com.example.todo

import android.os.Bundle
import android.text.TextUtils
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.example.todo.databinding.AddSectionActivityBinding
import com.example.todo.model.Folder
import com.example.todo.view.Section
import com.example.todo.viewModel.FolderViewModel


class AddSectionFragmentView : Fragment() {

    private val folder: Folder?=null

    private lateinit var mFolderViewModel: FolderViewModel
    private lateinit var binding: AddSectionActivityBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = AddSectionActivityBinding.inflate(inflater, container, false)

        mFolderViewModel = ViewModelProvider(this).get(FolderViewModel::class.java)

        binding.done.setOnClickListener {
            insertDataToDatabase()
        }
        return binding.root
    }


    private fun insertDataToDatabase(){
        val name = binding.FolderName.text.toString()
        if (inputCheck(name)){
            val folder = Folder(0, name)
            mFolderViewModel.addFolder(folder)
            Toast.makeText(requireContext(), "Добавлено!", Toast.LENGTH_LONG).show()
            findNavController().navigate(R.id.action_addSectionFragment_to_mainFragment)
        }
        else{
            Toast.makeText(requireContext(), "Заполните поля!", Toast.LENGTH_LONG).show()
        }
    }

    private fun inputCheck(name: String):Boolean{
        return !(TextUtils.isEmpty(name))
    }
}