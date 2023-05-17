package com.example.todo.view

фвimport android.annotation.SuppressLint
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.todo.MainFragmentView
import com.example.todo.databinding.AddSectionActivityBinding
import com.example.todo.databinding.MainScreenBinding
import com.example.todo.databinding.SectionItemBinding
import com.example.todo.model.Folder


class SectionAdapter : RecyclerView.Adapter<SectionAdapter.MyViewHolder>() {
    val sections = mutableListOf<Section>()
    private var folderList = emptyList<Folder>()

    class MyViewHolder(val binding: MainScreenBinding) :
        RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        return MyViewHolder(
            binding = MainScreenBinding.inflate(
                LayoutInflater.from(parent.context), parent, false)
        )

//            val view = LayoutInflater.from(parent.context)
//        val binding = SectionItemBinding.inflate(view, parent, false)
//        return SectionViewHolder(binding)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val section = sections[position]
        val folder = folderList[position]
        Log.e("TAG", folder.nameFolder.toString())
        holder.binding.textView22.text = folder.nameFolder.toString()
    }

//    override fun onBindViewHolder(holder: SectionViewHolder, position: Int) {
//        val section = sections[position]
//        val folder = folderList[position]
//        holder.bind
//        holder.bind(section)
//    }

    override fun getItemCount() = sections.size

    fun addSection(section: Section) {
        sections.add(section)
        notifyItemInserted(sections.size - 1)
    }

    @SuppressLint("NotifyDataSetChanged")
    fun setData(folder: List<Folder>) {
        this.folderList = folder
        notifyDataSetChanged()
    }


    inner class SectionViewHolder(private val binding: SectionItemBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(section: Section) {
            binding.name.text = section.name
        }
    }
}