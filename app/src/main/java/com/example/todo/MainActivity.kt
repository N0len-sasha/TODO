package com.example.todo

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.todo.databinding.MainScreenBinding
import java.util.*


class MainActivity : AppCompatActivity() {

    private lateinit var binding: MainScreenBinding
    private val sectionAdapter = SectionAdapter()
    private var draggedItemIndex: Int = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = MainScreenBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.rcView.layoutManager = LinearLayoutManager(this)
        binding.rcView.adapter = sectionAdapter
        binding.btAdd.setOnClickListener {
            sectionAdapter.addSection(Section("Новая папка"))
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

    }
    fun showProfile(view: View) {
        val intent = Intent(this, ProfileActivity::class.java)
        startActivity(intent)
    }
    fun editSection(view: View){
        val intent = Intent(this, AddSectionActivity::class.java)
        startActivity(intent)
    }

}