package com.example.projekt

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query

@Dao
interface UporabnikDao {
    @Query("SELECT * FROM uporabnik")
    suspend fun getAll(): List<Uporabnik>

    @Query("SELECT * FROM uporabnik WHERE uporabnikID IN (:ajdis)")
    suspend fun loadAllByIds(ajdis: IntArray): List<Uporabnik>

    @Query("SELECT * FROM uporabnik WHERE ime LIKE :ime AND priimek LIKE :priimek LIMIT 1")
    suspend fun findByName(ime: String, priimek: String): Uporabnik

    @Insert
    suspend fun insertAll(vararg uporabniki: Uporabnik)

    @Delete
    suspend fun delete(uporabnik: Uporabnik)
}