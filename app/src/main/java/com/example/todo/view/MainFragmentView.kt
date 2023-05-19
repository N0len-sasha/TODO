package com.example.todo

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.todo.databinding.MainScreenBinding
import com.example.todo.databinding.SectionItemBinding
import com.example.todo.model.Folder
import com.example.todo.view.SectionAdapter
import com.example.todo.viewModel.FolderViewModel
import java.util.*


class MainFragmentView : Fragment() {
    private lateinit var binding: MainScreenBinding
    private lateinit var sectionbinding: SectionItemBinding
    private val sectionAdapter = SectionAdapter()
    private var draggedItemIndex: Int = 0
    private lateinit var viewModel: FolderViewModel

    companion object{
        const val REQUEST = 1
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = MainScreenBinding.inflate(inflater, container, false)
        sectionbinding = SectionItemBinding.inflate(inflater, container, false)
        binding.rcView.layoutManager = LinearLayoutManager(activity)
        binding.rcView.adapter = sectionAdapter

        val provider = ViewModelProvider(this)

        viewModel = ViewModelProvider(this).get(FolderViewModel::class.java)
        viewModel.readAllData.observe(viewLifecycleOwner, androidx.lifecycle.Observer { folder ->
            sectionAdapter.setData(folder as MutableList<Folder>)
        })

        binding.btAdd.setOnClickListener {
            findNavController().navigate(R.id.action_mainFragment_to_addSectionFragment)
        }

        binding.btProfile.setOnClickListener{
            findNavController().navigate(R.id.action_mainFragment_to_profileFragment)
        }

        val swipeToDeleteCallBack = object : ItemTouchHelper.Callback() {
            override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
                viewModel.deleteFolder(sectionAdapter.getFolderId(viewHolder.layoutPosition))
                Toast.makeText(context, "Папка удалена", Toast.LENGTH_LONG).show()
            }

            override fun getMovementFlags(
                recyclerView: RecyclerView,
                viewHolder: RecyclerView.ViewHolder
            ): Int {
                return makeMovementFlags(
                    ItemTouchHelper.UP or ItemTouchHelper.DOWN,
                    ItemTouchHelper.RIGHT or ItemTouchHelper.LEFT
                )
            }

            override fun onMove(
                recyclerView: RecyclerView,
                viewHolder: RecyclerView.ViewHolder,
                target: RecyclerView.ViewHolder
            ): Boolean {
                draggedItemIndex = viewHolder.absoluteAdapterPosition
                var targetIndex = target.absoluteAdapterPosition

                Collections.swap(sectionAdapter.folders, draggedItemIndex, targetIndex)
                sectionAdapter.notifyItemMoved(draggedItemIndex, targetIndex)

                return false
            }
        }

        val itemTouchHelper = ItemTouchHelper(swipeToDeleteCallBack)
        itemTouchHelper.attachToRecyclerView(binding.rcView)

        return binding.root
    }
}
