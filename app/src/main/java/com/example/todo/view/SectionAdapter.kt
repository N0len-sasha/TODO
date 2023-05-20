package com.example.todo.view

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.todo.databinding.SectionItemBinding
import com.example.todo.model.Task


class SectionAdapter : RecyclerView.Adapter<SectionAdapter.FolderViewHolder>() {
    var task = mutableListOf<Task>()

    inner class FolderViewHolder(val binding: SectionItemBinding) :
        RecyclerView.ViewHolder(binding.root) {
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FolderViewHolder {
        val view = LayoutInflater.from(parent.context)
        val binding = SectionItemBinding.inflate(view, parent, false)
        return FolderViewHolder(binding)
    }

    override fun onBindViewHolder(holder: FolderViewHolder, position: Int) {
        val currentItem = task[position]
        holder.binding.name.text = currentItem.name
//        holder.binding.editFolder.setOnClickListener(){
//        }
    }

    override fun getItemCount() = task.size


    fun setData(task: MutableList<Task>) {
        this.task = task
        notifyDataSetChanged()
    }

    fun getTaskId(position: Int): Task {
        return task.get(position)
    }
}