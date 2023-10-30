package com.example.app

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import com.example.app.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private val SPLASH_TIME_OUT: Long = 2000;
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        with(binding){
            Handler().postDelayed({
                val intent = Intent(this@MainActivity, LandingPage::class.java)
                startActivity(intent)
                finish()
            }, SPLASH_TIME_OUT)
        }
    }
}