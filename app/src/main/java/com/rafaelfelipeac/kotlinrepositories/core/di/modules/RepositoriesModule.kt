package com.rafaelfelipeac.kotlinrepositories.core.di.modules

import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModel
import com.rafaelfelipeac.kotlinrepositories.core.di.key.FragmentKey
import com.rafaelfelipeac.kotlinrepositories.core.di.key.ViewModelKey
import com.rafaelfelipeac.kotlinrepositories.features.repositories.data.RepositoriesDataSource
import com.rafaelfelipeac.kotlinrepositories.features.repositories.domain.repository.RepositoriesRepository
import com.rafaelfelipeac.kotlinrepositories.features.repositories.presentation.RepositoriesFragment
import com.rafaelfelipeac.kotlinrepositories.features.repositories.presentation.RepositoriesViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
abstract class RepositoriesModule {

    @Binds
    abstract fun repositoriesRepository(repositoriesDataSource: RepositoriesDataSource): RepositoriesRepository

    @Binds
    @IntoMap
    @FragmentKey(RepositoriesFragment::class)
    abstract fun bindBackupFragment(repositoriesFragment: RepositoriesFragment): Fragment

    @Binds
    @IntoMap
    @ViewModelKey(RepositoriesViewModel::class)
    abstract fun bindBackupViewModel(repositoriesViewModel: RepositoriesViewModel): ViewModel
}
