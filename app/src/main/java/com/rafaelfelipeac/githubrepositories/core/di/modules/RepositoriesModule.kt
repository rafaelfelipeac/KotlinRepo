package com.rafaelfelipeac.githubrepositories.core.di.modules

import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModel
import com.rafaelfelipeac.githubrepositories.core.di.key.FragmentKey
import com.rafaelfelipeac.githubrepositories.core.di.key.ViewModelKey
import com.rafaelfelipeac.githubrepositories.features.repositories.RepositoriesService
import com.rafaelfelipeac.githubrepositories.features.repositories.data.RepositoriesDataSource
import com.rafaelfelipeac.githubrepositories.features.repositories.domain.repository.RepositoriesRepository
import com.rafaelfelipeac.githubrepositories.features.repositories.presentation.RepositoriesFragment
import com.rafaelfelipeac.githubrepositories.features.repositories.presentation.RepositoriesViewModel
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.multibindings.IntoMap

@Module
abstract class RepositoriesModule {

    companion object {

        @Provides
        fun repositoriesService(): RepositoriesService = RepositoriesService()
    }

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
