package com.example.todo.view

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.navigation.Navigation.findNavController
import androidx.fragment.app.ListFragment
import androidx.recyclerview.widget.RecyclerView
import com.example.todo.databinding.FragmentUpdateSectionBinding
import com.example.todo.databinding.SectionItemBinding
import com.example.todo.model.Folder


class SectionAdapter: RecyclerView.Adapter<SectionAdapter.FolderViewHolder>(){
    var folders = mutableListOf<Folder>()
    class FolderViewHolder(val binding: SectionItemBinding): RecyclerView.ViewHolder(binding.root){}

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FolderViewHolder {
        val view = LayoutInflater.from(parent.context)
        val binding = SectionItemBinding.inflate(view, parent, false)
        return FolderViewHolder(binding)
    }
    override fun onBindViewHolder(holder: FolderViewHolder, position: Int) {
        val currentItem = folders[position]
        holder.binding.name.text = currentItem.nameFolder

//        holder.binding.rowLayout.setOnClickListener {
//            val action = MainFragmentViewDirections.actionMainFragmentToUpdateSectionFragment(currentItem)
//        }
    }

    override fun getItemCount() = folders.size

    fun setData(folder: MutableList<Folder>){
        this.folders = folder
        notifyDataSetChanged()
    }


}