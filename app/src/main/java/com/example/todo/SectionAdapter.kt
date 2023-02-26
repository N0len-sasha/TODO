package com.example.todo

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.todo.databinding.SectionItemBinding

class SectionAdapter: RecyclerView.Adapter<SectionAdapter.SectionViewHolder>() {

    private val sections = mutableListOf<Section>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SectionViewHolder {
        val view = LayoutInflater.from(parent.context)
        val binding = SectionItemBinding.inflate(view, parent, false)
        return SectionViewHolder(binding)
    }


    override fun onBindViewHolder(holder: SectionViewHolder, position: Int) {
        val section = sections[position]
        holder.bind(section)
    }

    override fun getItemCount() = sections.size

    fun addSection(section: Section){
        sections.add(section)
        notifyItemInserted(sections.size - 1)
    }

    inner class SectionViewHolder(private val binding: SectionItemBinding): RecyclerView.ViewHolder(binding.root){
        fun bind(section: Section){
            binding.name.text= section.section_name
        }
    }
}