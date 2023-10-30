package com.example.app

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.app.databinding.ActivityDetailPageBinding

class DetailPage : AppCompatActivity() {
    private lateinit var binding: ActivityDetailPageBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailPageBinding.inflate(layoutInflater)
        setContentView(binding.root)

        with(binding){
            btnBack.setOnClickListener{
                val intent = Intent(this@DetailPage, LandingPage::class.java)
                startActivity(intent)
            }

            val id = intent.getStringExtra(HomeFragment.EXTRA_ID)
            val key = "movie$id"
            val arrayId = resources.getIdentifier(key, "array", packageName)

            if (arrayId != 0) {
                val movies = resources.getStringArray(arrayId)
                titleMovies.text = movies[0]
                durationMovies.text = movies[1]
                btnGenreMovies.text = movies[2]
                storylineMovie.text = movies[3]
                if (id == "1") {
                    imageMovies.setImageResource(R.drawable.di_ambang_kematian)
                } else if (id == "2") {
                    imageMovies.setImageResource(R.drawable.pamali)
                } else if (id == "3") {
                    imageMovies.setImageResource(R.drawable.petualangan_sherina)
                } else if (id == "4") {
                    imageMovies.setImageResource(R.drawable.saw_x)
                } else {
                    imageMovies.setImageResource(R.drawable.outline_home_selected_24)
                }
            }
        }
    }
}