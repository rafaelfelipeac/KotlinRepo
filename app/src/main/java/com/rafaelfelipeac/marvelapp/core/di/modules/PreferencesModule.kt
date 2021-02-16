package com.rafaelfelipeac.marvelapp.core.di.modules

import android.content.Context
import com.rafaelfelipeac.marvelapp.core.persistence.sharedpreferences.Preferences
import dagger.Module
import dagger.Provides

@Module
object PreferencesModule {

    @Provides
    fun provideSharedPreferences(context: Context): Preferences = Preferences(context)
}
