package com.example.todo

import android.graphics.Color
import android.graphics.PorterDuff
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.TextView

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.registration_activity)
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
}