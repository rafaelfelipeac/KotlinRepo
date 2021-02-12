package com.rafaelfelipeac.marvelapp.core.di.modules

import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModel
import com.rafaelfelipeac.marvelapp.core.di.key.FragmentKey
import com.rafaelfelipeac.marvelapp.core.di.key.ViewModelKey
import com.rafaelfelipeac.marvelapp.features.details.data.DetailsDataSource
import com.rafaelfelipeac.marvelapp.features.details.domain.repository.DetailsRepository
import com.rafaelfelipeac.marvelapp.features.details.presentation.DetailsFragment
import com.rafaelfelipeac.marvelapp.features.details.presentation.DetailsViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
abstract class DetailsModule {

    @Binds
    abstract fun detailsRepository(detailsDataSource: DetailsDataSource): DetailsRepository

    @Binds
    @IntoMap
    @FragmentKey(DetailsFragment::class)
    abstract fun bindDetailsFragment(detailsFragment: DetailsFragment): Fragment

    @Binds
    @IntoMap
    @ViewModelKey(DetailsViewModel::class)
    abstract fun bindDetailsViewModel(detailsViewModel: DetailsViewModel): ViewModel
}
