package com.example.todo

import android.os.Bundle
import android.view.LayoutInflater
import android.view.TextureView
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.todo.databinding.AddSectionActivityBinding
import com.example.todo.databinding.MainScreenBinding
import com.example.todo.databinding.SectionItemBinding
import java.util.*


class MainFragment : Fragment() {
    private lateinit var binding: MainScreenBinding
    private val sectionAdapter = SectionAdapter()
    private var draggedItemIndex: Int = 0
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = MainScreenBinding.inflate(inflater, container, false)

        binding.rcView.layoutManager = LinearLayoutManager(activity)
        binding.rcView.adapter = sectionAdapter
        binding.btProfile.setOnClickListener{
            findNavController().navigate(R.id.action_mainFragment_to_profileFragment)
        }

        binding.btAdd.setOnClickListener {
//            findNavController().navigate(R.id.action_mainFragment_to_addSectionFragment)
            sectionAdapter.addSection(Section("fdfdf"))
        }
        val swipeToDeleteCallBack = object : ItemTouchHelper.Callback() {
            override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
                val position = viewHolder.absoluteAdapterPosition
                sectionAdapter.sections.removeAt(position)
                sectionAdapter.notifyItemRemoved(position)
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

                Collections.swap(sectionAdapter.sections, draggedItemIndex, targetIndex)
                sectionAdapter.notifyItemMoved(draggedItemIndex, targetIndex)

                return false
            }
        }

        val itemTouchHelper = ItemTouchHelper(swipeToDeleteCallBack)
        itemTouchHelper.attachToRecyclerView(binding.rcView)
        return binding.root
    }

//    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
//        super.onViewCreated(view, savedInstanceState)
//        val name = arguments?.getString("Name")
//        name?.let { Section(it) }?.let { sectionAdapter.addSection(it) }
//    }
}
