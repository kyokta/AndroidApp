package com.example.app

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.app.databinding.ActivityHomePageBinding

class HomePage : AppCompatActivity() {
    private lateinit var binding: ActivityHomePageBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityHomePageBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val username = intent.getStringExtra("username")

        with (binding) {
            txtUsername.text = "Username Anda adalah $username"
        }
    }
}