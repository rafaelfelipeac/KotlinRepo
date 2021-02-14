package com.rafaelfelipeac.marvelapp.features.commons.data

import androidx.room.*
import com.rafaelfelipeac.marvelapp.features.commons.data.model.FavoriteDto

@Dao
interface FavoriteDao {

    @Query("SELECT * FROM favorite")
    fun getAll(): List<FavoriteDto>

    @Query("SELECT * FROM favorite WHERE favoriteId = :favoriteId")
    fun get(favoriteId: Long): FavoriteDto

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun save(goalDataModel: FavoriteDto): Long

    @Delete
    fun delete(goalDataModel: FavoriteDto)
}
