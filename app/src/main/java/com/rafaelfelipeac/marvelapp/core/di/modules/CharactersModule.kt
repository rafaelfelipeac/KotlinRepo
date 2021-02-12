package com.rafaelfelipeac.marvelapp.core.di.modules

import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModel
import com.rafaelfelipeac.marvelapp.core.di.key.FragmentKey
import com.rafaelfelipeac.marvelapp.core.di.key.ViewModelKey
import com.rafaelfelipeac.marvelapp.features.characters.data.CharactersDataSource
import com.rafaelfelipeac.marvelapp.features.characters.domain.repository.CharactersRepository
import com.rafaelfelipeac.marvelapp.features.characters.presentation.CharactersFragment
import com.rafaelfelipeac.marvelapp.features.characters.presentation.CharactersViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
abstract class CharactersModule {

    @Binds
    abstract fun charactersRepository(charactersDataSource: CharactersDataSource): CharactersRepository

    @Binds
    @IntoMap
    @FragmentKey(CharactersFragment::class)
    abstract fun bindBackupFragment(charactersFragment: CharactersFragment): Fragment

    @Binds
    @IntoMap
    @ViewModelKey(CharactersViewModel::class)
    abstract fun bindBackupViewModel(charactersViewModel: CharactersViewModel): ViewModel
}
