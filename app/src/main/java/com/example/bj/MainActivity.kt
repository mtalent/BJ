package com.example.bj

import android.os.Bundle

import androidx.appcompat.app.AppCompatActivity
import com.example.bj.databinding.ActivityMainBinding



class MainActivity : AppCompatActivity() {
    private lateinit var binding : ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        binding = ActivityMainBinding.inflate(layoutInflater)



   }


}




