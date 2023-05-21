package com.example.todo.view

import android.content.Context
import android.graphics.Paint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.todo.databinding.SectionItemBinding
import com.example.todo.model.Task


class SectionAdapter : RecyclerView.Adapter<SectionAdapter.SectionViewHolder>() {
    var task = mutableListOf<Task>()

    inner class SectionViewHolder(val binding: SectionItemBinding ) :
        RecyclerView.ViewHolder(binding.root) {
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SectionViewHolder {
        val view = LayoutInflater.from(parent.context)
        val binding = SectionItemBinding.inflate(view, parent, false)
        return SectionViewHolder(binding)
    }

    override fun onBindViewHolder(holder: SectionViewHolder, position: Int) {
        val currentItem = task[position]
        holder.binding.name.text = currentItem.name

    }

    override fun getItemCount() = task.size


    fun setData(task: MutableList<Task>) {
        this.task = task
        notifyDataSetChanged()
    }

    fun getTaskId(position: Int): Task {
        return task[position]
    }
}