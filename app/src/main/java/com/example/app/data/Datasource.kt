package com.example.app.data

import com.example.app.R
import com.example.app.model.Movie

class Datasource {
    fun loadMovies() : List<Movie> {
        return listOf<Movie>(
            Movie(1, "Di Ambang Kematian", R.drawable.di_ambang_kematian, "1 Jam 37 Menit"),
            Movie(2, "Pamali : Dusun Pocong", R.drawable.pamali, "1 Jam 37 Menit"),
            Movie(3, "Petualangan Sherina", R.drawable.petualangan_sherina, "2 Jam 6 Menit"),
            Movie(4, "Saw X", R.drawable.saw_x, "1 Jam 58 Menit")
        )
    }
}