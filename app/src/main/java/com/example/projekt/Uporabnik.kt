package com.example.projekt

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Uporabnik(
    @PrimaryKey(autoGenerate = true)
    val uporabnikID: Int,
    @ColumnInfo(name = "ime")
    val ime: String?,
    @ColumnInfo(name = "priimek")
    val priimek: String?
)