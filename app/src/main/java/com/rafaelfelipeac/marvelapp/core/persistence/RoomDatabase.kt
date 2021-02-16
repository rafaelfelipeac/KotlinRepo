package com.rafaelfelipeac.marvelapp.core.persistence

import androidx.room.Database
import androidx.room.RoomDatabase
import com.rafaelfelipeac.marvelapp.features.commons.data.FavoriteDao
import com.rafaelfelipeac.marvelapp.features.commons.data.model.FavoriteDto

@Database(
    entities = [FavoriteDto::class],
    version = 1,
    exportSchema = false
)
abstract class RoomDatabase : RoomDatabase() {
    abstract fun favoriteDao(): FavoriteDao
}
