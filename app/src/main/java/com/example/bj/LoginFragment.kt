package com.example.bj

import android.app.Activity
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.navigation.fragment.findNavController
import com.example.bj.databinding.FragmentLoginBinding
import com.example.bj.model.User
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase


class LoginFragment : Fragment() {

    private lateinit var binding : FragmentLoginBinding
    private lateinit var btnLogin : Button
    private lateinit var etRegister : TextView
    private lateinit var database : DatabaseReference

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
        etRegister = binding.tvRegister


        btnLogin.setOnClickListener {

            database = FirebaseDatabase.getInstance().getReference("Users")

            if (etUserName.text.isNotEmpty()) {
                database.child(etUserName.text.toString()).get().addOnSuccessListener {

                    if (it.exists()) {
                        val user = it.child("userName").value as String
                        val amount = it.child("score").value as Long
                        Toast.makeText(activity, "Read Successful", Toast.LENGTH_SHORT).show()
                        binding.etUserName.text.clear()
                        val action = LoginFragmentDirections.Companion.actionLoginFragmentToGameFragment(user, amount)
                        findNavController().navigate(action)
                    }
                    else
                    {
                        Toast.makeText(activity, "No user with that username click register in red below to register username", Toast.LENGTH_LONG).show()
                    }

                }.addOnFailureListener {
                    Toast.makeText(activity, "READ FAILED", Toast.LENGTH_SHORT).show()
                }
            }else
            {
                Toast.makeText(activity,"PLease enter the Username",Toast.LENGTH_SHORT).show()
            }








            //val action = LoginFragmentDirections.Companion.actionLoginFragmentToGameFragment()
           // findNavController().navigate(action)


        }

        etRegister.setOnClickListener {

            val userName : String = binding.etUserName.text.toString()
            val score : Long = 1000


            database = FirebaseDatabase.getInstance().getReference("Users")
            val user = User(userName, score)
            database.child(userName).setValue(user).addOnSuccessListener {

                binding.etUserName.text.clear()



                Toast.makeText(activity,"Successfully Saved",Toast.LENGTH_SHORT).show()

            }.addOnFailureListener{

                Toast.makeText(activity,"Failed",Toast.LENGTH_SHORT).show()


            }


        }
    }

   




}