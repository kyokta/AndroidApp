package com.example.app

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.lifecycle.MutableLiveData
import com.example.app.databinding.ActivityMainBinding
import com.example.app.models.Complain
import com.google.firebase.firestore.FirebaseFirestore

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    private val firebase = FirebaseFirestore.getInstance()
    private val complainCollection = firebase.collection("complains")
    private var idComplain = ""
    private val complainListLiveData : MutableLiveData<List<Complain>> by lazy {
        MutableLiveData<List<Complain>>()
    }

    companion object {
        const val EXTRA_ID = "extra_id";
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        with(binding) {
            btnAdd.setOnClickListener {
                val intent = Intent(this@MainActivity, AddComplain::class.java)
                startActivity(intent)
            }

            listItem.setOnItemClickListener { adapterView, view, i, l ->
                val item = adapterView.adapter.getItem(i) as Complain
                idComplain = item.id
//                Toast.makeText(this@MainActivity, "id: " + idComplain, Toast.LENGTH_SHORT).show()
                val intent = Intent(this@MainActivity, AddComplain::class.java)
                intent.putExtra(EXTRA_ID, idComplain)
                startActivity(intent)
            }

            observeData()
            getData()
        }
    }

    private fun getData() {
        observeDataChange()
    }

    private fun observeData() {
        complainListLiveData.observe(this) { complains ->
            val adapter = ArrayAdapter(
                this,
                android.R.layout.simple_list_item_1,
                complains.toMutableList()
            )
            binding.listItem.adapter = adapter
        }
    }

    private fun observeDataChange() {
        complainCollection.addSnapshotListener { value, error ->
            if (error != null){
                Log.d("MainActivity", "Error listening for complain changes: ", error)
                return@addSnapshotListener
            }
            val complain = value?.toObjects(Complain::class.java)
            if (value != null){
                complainListLiveData.postValue(complain)
            }
        }
    }
}