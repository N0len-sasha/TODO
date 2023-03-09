package com.example.todo
import android.app.Activity
import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.example.todo.databinding.AuthorizationActivityBinding
import com.example.todo.databinding.RegistrationActivityBinding

class AuthorizationActivity : AppCompatActivity() {

    private lateinit var binding: AuthorizationActivityBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = AuthorizationActivityBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.confirm.setOnClickListener {
            val clogin: String = "1"
            val cpassword: String = "1"
            val nothing: String = ""
            val login = binding.enterLogin
            val password = binding.enterPassword
            val check = binding.Check
            if ((login.text.toString() == clogin) && (password.text.toString() == cpassword)) {
                check.setText("«Верно»")
                check.setTextColor(Color.parseColor("#adff2f"))
            } else {
                login.setText(nothing)
                password.setText(nothing)
                check.setText("«Вы ошиблись в логине или пароле»")
                check.setTextColor(Color.parseColor("#960018"))
            }
        }
        binding.registerNow.setOnClickListener {
            val intent = Intent(this, RegistrationActivity::class.java)
            startActivity(intent)
        }
        }
}