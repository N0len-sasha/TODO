package com.example.todo
import android.app.Activity
import android.content.ContentValues.TAG
import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import android.text.TextUtils
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.todo.databinding.AuthorizationActivityBinding
import com.example.todo.databinding.RegistrationActivityBinding
import com.google.firebase.auth.FirebaseAuth

class AuthorizationActivity : AppCompatActivity() {

    private lateinit var binding: AuthorizationActivityBinding
    private lateinit var auth: FirebaseAuth

    public override fun onStart() {
        super.onStart()
        val currentUser = auth.currentUser
        if(currentUser != null){
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        }
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = AuthorizationActivityBinding.inflate(layoutInflater)
        setContentView(binding.root)
        auth = FirebaseAuth.getInstance()

        binding.confirm.setOnClickListener {
            val intent = Intent(this, MainActivity::class.java)
            val email: String = binding.enterLogin.text.toString().trim()
            val password:String = binding.enterPassword.text.toString().trim()
            if (TextUtils.isEmpty(email) || (email==null)){
                Toast.makeText(this, "Enter Email", Toast.LENGTH_SHORT).show()
            }
            if (TextUtils.isEmpty(password) || (email==null)){
                Toast.makeText(this, "Enter Password", Toast.LENGTH_SHORT).show()
            }
            auth.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener(this) { task ->
                    if (task.isSuccessful) {
                        Toast.makeText(applicationContext, "Login Successful",
                            Toast.LENGTH_SHORT).show()
                            startActivity(intent)
                    } else {
                        Toast.makeText(this, "Authentication failed",
                            Toast.LENGTH_SHORT).show()
                    }
                }

        }
        binding.registerNow.setOnClickListener {
            val intent = Intent(this, RegistrationActivity::class.java)
            startActivity(intent)
        }
        }
}