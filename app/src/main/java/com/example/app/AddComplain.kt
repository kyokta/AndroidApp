package com.example.app

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.example.app.databinding.ActivityAddComplainBinding
import com.example.app.models.Complain
import com.google.firebase.firestore.FirebaseFirestore

class AddComplain : AppCompatActivity() {
    private lateinit var binding: ActivityAddComplainBinding

    private val firebase = FirebaseFirestore.getInstance()
    private val complainCollection = firebase.collection("complains")
    private var updatedId = ""
    private val complainListLiveData : MutableLiveData<List<Complain>> by lazy {
        MutableLiveData<List<Complain>>()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAddComplainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val id = intent.getStringExtra(MainActivity.EXTRA_ID)

        with(binding) {
            btnBack.setOnClickListener {
                startActivity(Intent(this@AddComplain, MainActivity::class.java))
            }

            if (id != null) {
                header.text = "UBAH ADUAN"
                btnAddData.text = "Hapus"
                fetchData(id)

                btnUpdateData.setOnClickListener {
                    val nama = txtNama.text.toString()
                    val judul = txtJudul.text.toString()
                    val aduan = txtAduan.text.toString()

                    val UpdateAduan = Complain(
                        name = nama,
                        title = judul,
                        content = aduan
                    )
                    updateData(UpdateAduan)
                    updatedId = ""
                    startActivity(Intent(this@AddComplain, MainActivity::class.java))
                    resetForm()
                }

                // Hapus data
                btnAddData.setOnClickListener {
                    val nama = txtNama.text.toString()
                    val judul = txtJudul.text.toString()
                    val aduan = txtAduan.text.toString()

                    val HapusAduan = Complain(
                        name = nama,
                        title = judul,
                        content = aduan
                    )
                    deleteData(HapusAduan)
                    startActivity(Intent(this@AddComplain, MainActivity::class.java))
                }
            } else {
                btnUpdateData.isEnabled = false
                btnAddData.setOnClickListener {
                    val nama = txtNama.text.toString()
                    val judul = txtJudul.text.toString()
                    val aduan = txtAduan.text.toString()

                    val AduanBaru = Complain(
                        name = nama,
                        title = judul,
                        content = aduan
                    )
                    addData(AduanBaru)
                    startActivity(Intent(this@AddComplain, MainActivity::class.java))
                }
            }
        }
    }

    private fun fetchData (id: String) {
        complainCollection.document(id).get()
            .addOnSuccessListener { doc ->
                if (doc.exists()) {
                    val complain = doc.toObject(Complain::class.java)

                    complain?.let {
                        updatedId = it.id
                        binding.txtNama.setText(it.name)
                        binding.txtJudul.setText(it.title)
                        binding.txtAduan.setText(it.content)
                    }
                } else {
                    Log.d("AddComplain", "Data doesn't match")
                }
            }
            .addOnFailureListener { exception ->
                Log.d("AddComplain", "Error fetching data: $exception")
            }
    }

    private fun resetForm () {
        with(binding) {
            txtNama.setText("")
            txtJudul.setText("")
            txtAduan.setText("")
        }
    }

    private fun addData (complain: Complain) {
        complainCollection.add(complain)
            .addOnSuccessListener { docRef ->
                val createdComplainId = docRef.id
                complain.id = createdComplainId
                docRef.set(complain)
                    .addOnFailureListener {
                        Log.d("AddComplain", "Error update budget id: ", it)
                    }
                resetForm()
            }
            .addOnFailureListener {
                Log.d("AddComplain", "Error add complain: ", it)
            }
    }

    private fun updateData (complain: Complain) {
        complain.id = updatedId
        complainCollection.document(updatedId).set(complain)
            .addOnFailureListener {
                Log.d("AddComplain", "Error updating complain", it)
            }
    }

    private fun deleteData (complain: Complain) {
        complain.id = updatedId
        complainCollection.document(complain.id).delete()
            .addOnFailureListener {
                Log.d("AddComplain", "Error deleting complain", it)
            }
    }
}