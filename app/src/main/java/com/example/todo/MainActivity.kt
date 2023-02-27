package com.example.todo

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.MenuItem.OnMenuItemClickListener
import android.view.View
import android.widget.PopupMenu
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.todo.databinding.MainScreenBinding
import com.example.todo.databinding.SectionItemBinding

class MainActivity : AppCompatActivity(),PopupMenu.OnMenuItemClickListener {

    private lateinit var binding: MainScreenBinding
    private lateinit var item_binding: SectionItemBinding
    private val sectionAdapter = SectionAdapter()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = MainScreenBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.rcView.layoutManager = LinearLayoutManager(this)
        binding.rcView.adapter = sectionAdapter

        binding.btAdd.setOnClickListener {
            sectionAdapter.addSection(Section("Новая задача"))
        }
/*        val button = findViewById<Button>(R.id.Enter)
        button.setOnClickListener {
            val clogin: String = "1"
            val cpassword: String = "1"
            val nothing: String = ""
            val login = findViewById<EditText>(R.id.Login)
            val password = findViewById<EditText>(R.id.Password)
            val check = findViewById<TextView>(R.id.Check)
            if ((login.text.toString() == clogin) && (password.text.toString() == cpassword)) {
                check.setText("«Верно»")
                check.setTextColor(Color.parseColor("#adff2f"))
            } else {
                login.setText(nothing)
                password.setText(nothing)
                check.setText("«Вы ошиблись в логине или пароле»")
                check.setTextColor(Color.parseColor("#960018"))
            }
        }*/
    }
    public fun showMore(view: View){
        val popup = PopupMenu(this, view)
        popup.setOnMenuItemClickListener (this)
        popup.inflate(R.menu.more_menu)
        popup.show()
    }

    override fun onMenuItemClick(p0: MenuItem?): Boolean {
        return false
    }
}