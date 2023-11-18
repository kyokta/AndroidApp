package com.example.app

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.Toast
import com.example.app.database.Note
import com.example.app.database.NoteDao
import com.example.app.database.NoteRoomDatabase
import com.example.app.databinding.ActivityMainBinding
import java.util.concurrent.ExecutorService
import java.util.concurrent.Executors

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private lateinit var mNoteDao: NoteDao
    private lateinit var executorService: ExecutorService
    private var updatedId: Int = 0

    companion object {
        const val EXTRA_ID = "extra_id";
        const val EXTRA_TITLE = "extra_title";
        const val EXTRA_DESCRIPTION = "extra_description";
        const val EXTRA_DAY = "extra_day";
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        executorService = Executors.newSingleThreadExecutor()
        val db = NoteRoomDatabase.getDatabase(this)
        mNoteDao = db!!.noteDao()!!
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        with(binding){
            btnAdd.setOnClickListener{
                val intent = Intent(this@MainActivity, AddData::class.java)
                startActivity(intent)
            }

            listView.setOnItemClickListener { adapterView, view, position, id ->
                val item = adapterView.adapter.getItem(position) as Note
                val intent = Intent(this@MainActivity, AddData::class.java)
                intent.putExtra(EXTRA_ID, item.id.toString())
                intent.putExtra(EXTRA_TITLE, item.title)
                intent.putExtra(EXTRA_DESCRIPTION, item.description)
                intent.putExtra(EXTRA_DAY, item.day)
                startActivity(intent)
            }
        }
    }

    override fun onResume() {
        super.onResume()
        getNotes()
    }

    private fun getNotes(){
        mNoteDao.allNotes.observe(this) {notes ->
            val adapter: ArrayAdapter<Note> = ArrayAdapter<Note>(this,
                android.R.layout.simple_list_item_1, notes)
            binding.listView.adapter = adapter
        }
    }
}