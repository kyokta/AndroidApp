package com.example.app.data

import com.example.app.R
import com.example.app.model.Pahlawan

class Datasource {
    fun loadData(): List<Pahlawan> {
        return listOf<Pahlawan> (
            Pahlawan(R.drawable.amirsyarifudin, "Amir Sjarifoeddin Harahap", "Medan, Sumatera Utara", "24 Agustus 1903"),
            Pahlawan(R.drawable.soegondodjojopoespito, "Soegondo Djojopoespito", "Tuban, Jawa Timur", "22 Februari 1905"),
            Pahlawan(R.drawable.soenario, "Soenario Sastrowardoyo", "Madiun, Jawa Timur", "28 Agustus 1902"),
            Pahlawan(R.drawable.johannesleimena, "Johannes Leimena", "Ambon, Maluku", "1905"),
            Pahlawan(R.drawable.djokomarsaid, "Djoko Mardaid", "-", "-"),
            Pahlawan(R.drawable.mohammadyamin, "Mohammad Yamin", "Sawahlunto, Sumatera Barat", "24 Agustus 1903"),
            Pahlawan(R.drawable.wrsoepratman, "W.R. Soepratman", "Purworejo, Jawa Tengah", "9 Maret 1903")
        )
    }
}