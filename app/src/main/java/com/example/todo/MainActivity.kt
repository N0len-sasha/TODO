package com.example.todo

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.MenuItem.OnMenuItemClickListener
import android.view.View
import android.widget.PopupMenu
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.todo.databinding.AuthorizationActivityBinding
import com.example.todo.databinding.MainScreenBinding
import com.example.todo.databinding.SectionItemBinding

class MainActivity : AppCompatActivity(),PopupMenu.OnMenuItemClickListener {

    private lateinit var binding: MainScreenBinding
    private val sectionAdapter = SectionAdapter()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.main_screen)
        binding = MainScreenBinding.inflate(layoutInflater)
        binding.rcView.layoutManager = LinearLayoutManager(this)
        binding.rcView.adapter = sectionAdapter

        binding.btAdd.setOnClickListener {
            sectionAdapter.addSection(Section("Новая задача"))
        }


    }
    public fun showMore(view: View){
        val popup = PopupMenu(this, view)
        popup.setOnMenuItemClickListener (this)
        popup.inflate(R.menu.more_menu)
        popup.show()
    }

    override fun onMenuItemClick(item: MenuItem?): Boolean {
        when (item?.itemId) {
            R.id.up -> {
//                val currentPosition = sectionAdapter.currentPosition // получаем позицию текущего элемента
                sectionAdapter.moveSectionUp(3) // перемещаем элемент на одну строку вверх
            }
            R.id.down -> sectionAdapter.moveSectionDown(2)
            R.id.delete -> sectionAdapter.removeSection(1)
        }
        return false
    }

}