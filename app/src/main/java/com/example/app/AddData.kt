package com.example.app

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import com.example.app.database.Note
import com.example.app.database.NoteDao
import com.example.app.database.NoteRoomDatabase
import com.example.app.databinding.ActivityAddDataBinding
import java.util.concurrent.ExecutorService
import java.util.concurrent.Executors

class AddData : AppCompatActivity() {
    private lateinit var binding: ActivityAddDataBinding
    private lateinit var mNoteDao: NoteDao
    private lateinit var executorService: ExecutorService
    private var updatedId: Int = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        executorService = Executors.newSingleThreadExecutor()
        val db = NoteRoomDatabase.getDatabase(this)
        mNoteDao = db!!.noteDao()!!
        super.onCreate(savedInstanceState)
        binding = ActivityAddDataBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val id = intent.getStringExtra(MainActivity.EXTRA_ID)
        val title = intent.getStringExtra(MainActivity.EXTRA_TITLE)
        val description = intent.getStringExtra(MainActivity.EXTRA_DESCRIPTION)
        val day = intent.getStringExtra(MainActivity.EXTRA_DAY)

        with(binding) {
            btnBack.setOnClickListener{
                val intent = Intent(this@AddData, MainActivity::class.java)
                startActivity(intent)
            }

            if (id != null) {
                header.text = "EDIT DATA"
                txtTitle.setText(title)
                txtDescription.setText(description)
                txtDay.setText(day)

                btnUpdateData.setOnClickListener{
                    update(
                        Note(
                            id = id.toInt(),
                            title = txtTitle.text.toString(),
                            description = txtDescription.text.toString(),
                            day = txtDay.text.toString()
                        )
                    )
                    updatedId = 0

                    val intent = Intent(this@AddData, MainActivity::class.java)
                    startActivity(intent)
                }

                btnAddData.text = "Hapus"
                btnAddData.setOnClickListener(View.OnClickListener {
                    delete(
                        Note(
                            id = id.toInt(),
                            title = txtTitle.text.toString(),
                            description = txtDescription.text.toString(),
                            day = txtDay.text.toString()
                        )
                    )

                    val intent = Intent(this@AddData, MainActivity::class.java)
                    startActivity(intent)
                })
            } else {
                btnUpdateData.isEnabled = false
                btnAddData.setOnClickListener(View.OnClickListener {
                    insert(
                        Note(
                            id = updatedId,
                            title = txtTitle.text.toString(),
                            description = txtDescription.text.toString(),
                            day = txtDay.text.toString()
                        )
                    )

                    val intent = Intent(this@AddData, MainActivity::class.java)
                    startActivity(intent)
                })
            }
        }
    }

    private fun insert(note: Note){
        executorService.execute { mNoteDao.insert(note) }
    }

    private fun update(note: Note){
        executorService.execute { mNoteDao.update(note) }
    }

    private fun delete(note: Note){
        executorService.execute { mNoteDao.delete(note) }
    }
}