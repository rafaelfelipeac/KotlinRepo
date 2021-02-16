package com.rafaelfelipeac.marvelapp.core.di.modules

import android.content.Context
import androidx.room.Room
import com.rafaelfelipeac.marvelapp.core.persistence.RoomDatabase
import com.rafaelfelipeac.marvelapp.features.commons.data.FavoriteDao
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
object PersistenceModule {

    @Provides
    @Singleton
    fun providesDatabase(context: Context): RoomDatabase =
        Room.databaseBuilder(context, RoomDatabase::class.java, "marvel-db")
            .allowMainThreadQueries()
            .fallbackToDestructiveMigration()
            .build()

    @Provides
    fun provideFavoriteDao(appDatabase: RoomDatabase): FavoriteDao = appDatabase.favoriteDao()
}
