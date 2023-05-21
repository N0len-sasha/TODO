package com.example.todo

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.todo.databinding.MainScreenBinding
import com.example.todo.databinding.SectionItemBinding
import com.example.todo.model.Task
import com.example.todo.view.SectionAdapter
import com.example.todo.viewModel.TaskViewModel
import java.util.Collections


class MainFragmentView : Fragment(){
    private lateinit var binding: MainScreenBinding
    private lateinit var recyclerView: RecyclerView.ViewHolder
    private lateinit var sectionbinding: SectionItemBinding
    private val sectionAdapter = SectionAdapter()
    private var draggedItemIndex: Int = 0
    private val viewModel: TaskViewModel by viewModels()


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        binding = MainScreenBinding.inflate(inflater, container, false)
        sectionbinding = SectionItemBinding.inflate(inflater, container, false)
        binding.rcView.layoutManager = LinearLayoutManager(activity)
        binding.rcView.adapter = sectionAdapter

        viewModel.readAllData.observe(viewLifecycleOwner, Observer { task ->
            sectionAdapter.setData(task as MutableList<Task>)
        })

        binding.btAdd.setOnClickListener {
            findNavController().navigate(R.id.action_mainFragment_to_createTask)
        }

        binding.btProfile.setOnClickListener {
            findNavController().navigate(R.id.action_mainFragment_to_profileFragment)
        }



        return binding.root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        val swipeToDeleteCallBack = object : ItemTouchHelper.Callback() {
            override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
                viewModel.deleteTask(sectionAdapter.getTaskId(viewHolder.layoutPosition))
                //Смена имени
//                viewModel.id.value = (sectionAdapter.getTaskId(viewHolder.layoutPosition).idTask)
//                findNavController().navigate(R.id.action_mainFragment_to_updateSectionFragment)

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

                Collections.swap(sectionAdapter.task, draggedItemIndex, targetIndex)
                sectionAdapter.notifyItemMoved(draggedItemIndex, targetIndex)

                return false
            }
        }

        val itemTouchHelper = ItemTouchHelper(swipeToDeleteCallBack)
        itemTouchHelper.attachToRecyclerView(binding.rcView)
    }
}
