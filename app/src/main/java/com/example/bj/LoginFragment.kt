package com.example.bj

import android.app.Activity
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.navigation.fragment.findNavController
import com.example.bj.databinding.FragmentLoginBinding



class LoginFragment : Fragment() {

    private lateinit var binding : FragmentLoginBinding
    private lateinit var btnLogin : Button

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
      binding = FragmentLoginBinding.inflate(layoutInflater)
      return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        btnLogin = binding.buttonLogin
        val etUserName = binding.etUserName

        btnLogin.setOnClickListener {



            val action = LoginFragmentDirections.Companion.actionLoginFragmentToGameFragment()
            findNavController().navigate(action)


        }
    }


}