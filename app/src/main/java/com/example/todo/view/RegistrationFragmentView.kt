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
    public override fun onStart() {
        super.onStart()
        // Check if user is signed in (non-null) and update UI accordingly.
        val currentUser = auth.currentUser
        if (currentUser != null) {
            findNavController().navigate(R.id.action_registrationFragment2_to_mainFragment)
        }
    }

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
                        findNavController().navigate(R.id.action_registrationFragment2_to_authorizationFragment2)
                    }else{
                        Toast.makeText(activity, it.exception.toString(), Toast.LENGTH_SHORT).show()
                    }
                }
            } else
                Toast.makeText(activity, "Empty fields are prohibited", Toast.LENGTH_SHORT).show()
        }
        binding.logIn.setOnClickListener {
            findNavController().navigate(R.id.action_registrationFragment2_to_authorizationFragment2)
        }
        return binding.root
    }
}