package com.example.todo

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.todo.databinding.SectionItemBinding


class SectionAdapter: RecyclerView.Adapter<SectionAdapter.SectionViewHolder>() {
    private val sections = mutableListOf<Section>()
//  var currentPosition = -1

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SectionViewHolder {
        val view = LayoutInflater.from(parent.context)
        val binding = SectionItemBinding.inflate(view, parent, false)
        return SectionViewHolder(binding)
    }


    override fun onBindViewHolder(holder: SectionViewHolder, position: Int) {
        val section = sections[position]
        holder.bind(section)

//        holder.itemView.setOnClickListener{
//            setCurrentPosition(position)
//        }
    }

    override fun getItemCount() = sections.size

    fun addSection(section: Section){
        sections.add(section)
        notifyItemInserted(sections.size - 1)
    }

    fun removeSection(position: Int){
        sections.removeAt(position)
        notifyItemRemoved(position)
    }
    fun moveSectionUp(position: Int){
        if (position > 0){
            val section = sections.removeAt(position)
            sections.add(position,section)
            notifyItemMoved(position, position - 1)
        }
    }

    fun moveSectionDown(position: Int){
        if(position < sections.size){
            val section = sections.removeAt(position)
            sections.add(position, section)
            notifyItemMoved(position , position + 1)
        }
    }
//    private fun setCurrentPosition(position: Int){
//        currentPosition = position
//    }
    inner class SectionViewHolder(private val binding: SectionItemBinding): RecyclerView.ViewHolder(binding.root){
        fun bind(section: Section){
            binding.name.text= section.section_name
        }
    }
}