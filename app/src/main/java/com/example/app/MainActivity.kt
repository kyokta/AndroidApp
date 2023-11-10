package com.example.app

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.app.adapter.ItemAdapter
import com.example.app.data.Datasource
import com.example.app.databinding.ActivityMainBinding
import com.example.app.model.Iron
import com.example.app.model.IronModel
import com.example.app.network.ApiClient
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val client = ApiClient.getInstance()
        val response = client.getData()
        val listdata = ArrayList<String>()

        response.enqueue(object : Callback<IronModel> {
            override fun onResponse(call: Call<IronModel>, response: Response<IronModel>) {
                val thisResult = response.body()
                val datas = thisResult?.result ?: emptyList()
                if (datas.isEmpty()) {
                    for (i in datas) {
                        listdata.add(i.title)
                    }
                }

                binding.listData.adapter = ItemAdapter(this@MainActivity, listdata)
                binding.listData.setHasFixedSize(true)
            }

            override fun onFailure(call: Call<IronModel>, t: Throwable) {
                Toast.makeText(this@MainActivity, "Connection Error", Toast.LENGTH_SHORT).show()
            }

        })
    }
}