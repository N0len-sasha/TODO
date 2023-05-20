package com.example.todo.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.navigation.fragment.findNavController
import com.example.todo.R
import com.example.todo.databinding.RegistrationActivityBinding
import com.google.firebase.auth.FirebaseAuth


class RegistrationFragmentView : Fragment() {
    private lateinit var auth: FirebaseAuth
    private lateinit var binding: RegistrationActivityBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = RegistrationActivityBinding.inflate(inflater, container, false)
        auth = FirebaseAuth.getInstance()

        binding.confirm.setOnClickListener {
            val email: String = binding.regLogin.text.toString().trim()
            val password:String = binding.regPassword.text.toString().trim()
            if (email.isNotEmpty() && password.isNotEmpty()){
                auth.createUserWithEmailAndPassword(email, password).addOnCompleteListener {
                    if (it.isSuccessful){
                        auth.currentUser?.sendEmailVerification()
                            ?.addOnSuccessListener {
                                Toast.makeText(context, "Пожалуйста подтвердите адрес электронной почты", Toast.LENGTH_LONG).show()
                            }
                            ?.addOnFailureListener{
                                Toast.makeText(context, it.toString(), Toast.LENGTH_LONG).show()
                            }
                        findNavController().navigate(R.id.action_registrationFragment2_to_authorizationFragment2)
                    }
                    else{
                        Toast.makeText(activity, it.exception.toString(), Toast.LENGTH_SHORT).show()
                    }
                }
            }
            else {
                if (password.isNotEmpty() && email.length <= 6){
                    Toast.makeText(activity, "Длина пароля должна быть не меньше 6 знаков", Toast.LENGTH_SHORT).show()
                }else {
                    Toast.makeText(activity, "Заполните пустые поля", Toast.LENGTH_SHORT).show()
                }
            }
        }
        binding.logIn.setOnClickListener {
            findNavController().navigate(R.id.action_registrationFragment2_to_authorizationFragment2)
        }
        return binding.root
    }
}