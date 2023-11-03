package com.example.app

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.app.adapter.ItemAdapter
import com.example.app.data.Datasource
import com.example.app.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        with(binding) {
            val data = Datasource().loadData()

            listPahlawan.adapter = ItemAdapter(this@MainActivity, data)

            listPahlawan.setHasFixedSize(true)
        }
    }
}