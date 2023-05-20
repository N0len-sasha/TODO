package com.example.todo.view

import android.app.Activity
import android.os.Bundle
import android.text.TextUtils
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.navigation.fragment.findNavController
import com.example.todo.R
import com.example.todo.databinding.AuthorizationActivityBinding
import com.google.firebase.auth.FirebaseAuth

class AuthorizationFragmentView : Fragment() {
    private lateinit var binding: AuthorizationActivityBinding
    private lateinit var auth: FirebaseAuth

    public override fun onStart() {
        super.onStart()
        val currentUser = auth.currentUser
        if(currentUser != null){
            findNavController().navigate(R.id.action_authorizationFragment2_to_mainFragment)
        }
    }
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = AuthorizationActivityBinding.inflate(inflater, container, false)
        auth = FirebaseAuth.getInstance()
        binding.confirm.setOnClickListener {
            val email: String = binding.enterLogin.text.toString().trim()
            val password:String = binding.enterPassword.text.toString().trim()

            if (TextUtils.isEmpty(email) || (email==null)){
                Toast.makeText(activity, "Введите логин", Toast.LENGTH_SHORT).show()
            } else
            if (TextUtils.isEmpty(password) || (email==null)){
                Toast.makeText(activity, "Введите пароль", Toast.LENGTH_SHORT).show()
            }
            else {
                auth.signInWithEmailAndPassword(email, password)
                    .addOnCompleteListener(Activity()) { task ->
                        if (task.isSuccessful) {
                            Toast.makeText(
                                requireContext().applicationContext, "Вы авторизированы",
                                Toast.LENGTH_SHORT
                            ).show()
                            findNavController().navigate(R.id.action_authorizationFragment2_to_mainFragment)
                        }
                        else {
                            Toast.makeText(
                                activity, "Ошибка авторизации",
                                Toast.LENGTH_SHORT
                            ).show()
                        }
                    }
            }
        }
        binding.forgot.setOnClickListener {
            val email: String = binding.enterLogin.text.toString().trim()
            if (TextUtils.isEmpty(email) || email == null){
                Toast.makeText(activity, "Пожалуйства, введите почту для восстановления", Toast.LENGTH_LONG).show()
            }else {
                auth.sendPasswordResetEmail(email)
                Toast.makeText(activity, "ссылка для восстановления пароля отправлена " +
                        "на вашу почту", Toast.LENGTH_LONG).show()
            }
        }
        binding.register.setOnClickListener {
            findNavController().navigate(R.id.action_authorizationFragment2_to_registrationFragment2)
        }
        return binding.root
    }
}