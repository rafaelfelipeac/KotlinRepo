package com.rafaelfelipeac.marvelapp.features.favorites

import androidx.fragment.app.testing.FragmentScenario
import androidx.fragment.app.testing.launchFragmentInContainer
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.assertion.ViewAssertions
import androidx.test.espresso.matcher.ViewMatchers
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.rafaelfelipeac.marvelapp.R
import com.rafaelfelipeac.marvelapp.base.FakeFavoriteViewModel
import com.rafaelfelipeac.marvelapp.features.favorites.domain.usecase.DeleteFavoriteUseCase
import com.rafaelfelipeac.marvelapp.features.favorites.domain.usecase.GetFavoritesUseCase
import com.rafaelfelipeac.marvelapp.features.favorites.domain.usecase.GetListModeUseCase
import com.rafaelfelipeac.marvelapp.features.favorites.domain.usecase.SaveListModeUseCase
import com.rafaelfelipeac.marvelapp.features.favorites.presentation.FavoriteFragment
import io.mockk.mockk
import org.hamcrest.CoreMatchers
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class FavoriteFragmentTest {

    private lateinit var scenario: FragmentScenario<FavoriteFragment>

    private val mockGetFavoritesUseCase = mockk<GetFavoritesUseCase>()
    private val mockDeleteFavoriteUseCase = mockk<DeleteFavoriteUseCase>()
    private val mockSaveListModeUseCase = mockk<SaveListModeUseCase>()
    private val mockGetListModeUseCase = mockk<GetListModeUseCase>()

    @Test
    fun launchFavoriteFragmentAndVerifyUI() {
        launchFragmentInContainer<FavoriteFragment>()

        onView(withId(R.id.favoriteTitle))
            .check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
    }

    @Test
    fun responseIsSuccessThenJustListAndTitleAreVisible() {
        scenarioSuccess()
    }

    @Test
    fun responseIsNetworkErrorThenJustPlaceholderIsVisible() {
        scenarioError()
    }

    // region Scenarios
    private fun scenarioSuccess() {
        scenario = launchFragmentInContainer() {
            FavoriteFragment().also { fragment ->
                fragment.viewModel = FakeFavoriteViewModel(
                    mockGetFavoritesUseCase,
                    mockDeleteFavoriteUseCase,
                    mockSaveListModeUseCase,
                    mockGetListModeUseCase,
                    FakeFavoriteViewModel.Result.SUCCESS
                )
            }
        }

        onView(withId(R.id.favoriteList))
            .check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
        onView(withId(R.id.favoriteTitle))
            .check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
        onView(withId(R.id.favoritePlaceholder))
            .check(ViewAssertions.matches(CoreMatchers.not(ViewMatchers.isDisplayed())))
        onView(withId(R.id.favorite_list_loader))
            .check(ViewAssertions.matches(CoreMatchers.not(ViewMatchers.isDisplayed())))
    }

    private fun scenarioError() {
        scenario = launchFragmentInContainer() {
            FavoriteFragment().also { fragment ->
                fragment.viewModel = FakeFavoriteViewModel(
                    mockGetFavoritesUseCase,
                    mockDeleteFavoriteUseCase,
                    mockSaveListModeUseCase,
                    mockGetListModeUseCase,
                    FakeFavoriteViewModel.Result.ERROR
                )
            }
        }

        onView(withId(R.id.favoriteList))
            .check(ViewAssertions.matches(CoreMatchers.not(ViewMatchers.isDisplayed())))
        onView(withId(R.id.favoritePlaceholder))
            .check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
        onView(withId(R.id.favorite_list_loader))
            .check(ViewAssertions.matches(CoreMatchers.not(ViewMatchers.isDisplayed())))
    }
    // endregion
}
