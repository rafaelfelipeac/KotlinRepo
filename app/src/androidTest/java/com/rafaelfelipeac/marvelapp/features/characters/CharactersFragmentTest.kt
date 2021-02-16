package com.rafaelfelipeac.marvelapp.features.characters

import androidx.fragment.app.testing.FragmentScenario
import androidx.fragment.app.testing.launchFragmentInContainer
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.swipeDown
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.isDisplayed
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.rafaelfelipeac.marvelapp.R
import com.rafaelfelipeac.marvelapp.base.FakeCharactersViewModel
import com.rafaelfelipeac.marvelapp.features.characters.domain.usecase.GetCharactersUseCase
import com.rafaelfelipeac.marvelapp.features.characters.domain.usecase.GetListModeUseCase
import com.rafaelfelipeac.marvelapp.features.characters.domain.usecase.SaveFavoriteUseCase
import com.rafaelfelipeac.marvelapp.features.characters.domain.usecase.SaveListModeUseCase
import com.rafaelfelipeac.marvelapp.features.characters.presentation.CharactersFragment
import io.mockk.mockk
import org.hamcrest.CoreMatchers.not
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class CharactersFragmentTest {

    private lateinit var scenario: FragmentScenario<CharactersFragment>

    private val mockGetCharactersUseCase = mockk<GetCharactersUseCase>()
    private val mockSaveFavoriteUseCase = mockk<SaveFavoriteUseCase>()
    private val mockListModeUseCase = mockk<SaveListModeUseCase>()
    private val mockGetListModeUseCase = mockk<GetListModeUseCase>()

    @Test
    fun launchCharactersFragmentAndVerifyUI() {
        launchFragmentInContainer<CharactersFragment>()

        onView(withId(R.id.characters_refresh))
            .check(matches(isDisplayed()))
    }

    @Test
    fun responseIsSuccessThenJustListIsVisible() {
        scenarioSuccess()
    }

    @Test
    fun responseIsNetworkErrorThenJustPlaceholderIsVisible() {
        scenarioNetworkError()
    }

    @Test
    fun responseIsGenericErrorThenJustPlaceholderIsVisible() {
        scenarioGenericError()
    }

    @Test
    fun responseIsSuccessAndUserSwipeDownWithSuccessThenListAreRefreshed() {
        scenarioSuccess()

        onView(withId(R.id.characters_refresh)).perform(swipeDown())

        scenarioSuccess()
    }

    @Test
    fun responseIsSuccessAndUserSwipeDownWithNetworkErrorThenPlaceholderIsShown() {
        scenarioSuccess()

        onView(withId(R.id.characters_refresh)).perform(swipeDown())

        scenarioNetworkError()
    }

    @Test
    fun responseIsSuccessAndUserSwipeDownWithGenericErrorThenPlaceholderIsShown() {
        scenarioSuccess()

        onView(withId(R.id.characters_refresh)).perform(swipeDown())

        scenarioGenericError()
    }

    @Test
    fun responseIsNetworkErrorAndUserSwipeDownWithSuccessThenListAreRefreshed() {
        scenarioNetworkError()

        onView(withId(R.id.characters_refresh)).perform(swipeDown())

        scenarioSuccess()
    }

    @Test
    fun responseIsNetworkErrorAndUserSwipeDownWithNetworkErrorAndPlaceholderIsShown() {
        scenarioNetworkError()

        onView(withId(R.id.characters_refresh)).perform(swipeDown())

        scenarioNetworkError()
    }

    // region Scenarios
    private fun scenarioSuccess() {
        scenario = launchFragmentInContainer() {
            CharactersFragment().also { fragment ->
                fragment.viewModel = FakeCharactersViewModel(
                    mockGetCharactersUseCase,
                    mockSaveFavoriteUseCase,
                    mockListModeUseCase,
                    mockGetListModeUseCase,
                    FakeCharactersViewModel.Result.SUCCESS
                )
            }
        }

        onView(withId(R.id.characters_list))
            .check(matches(isDisplayed()))
        onView(withId(R.id.charactersPlaceholder))
            .check(matches(not(isDisplayed())))
        onView(withId(R.id.characters_list_loader))
            .check(matches(not(isDisplayed())))
        onView(withId(R.id.charactersProgressBar))
            .check(matches(not(isDisplayed())))
    }

    private fun scenarioNetworkError() {
        scenario = launchFragmentInContainer {
            CharactersFragment().also { fragment ->
                fragment.viewModel = FakeCharactersViewModel(
                    mockGetCharactersUseCase,
                    mockSaveFavoriteUseCase,
                    mockListModeUseCase,
                    mockGetListModeUseCase,
                    FakeCharactersViewModel.Result.NETWORK_ERROR
                )
            }
        }

        onView(withId(R.id.characters_list))
            .check(matches(not(isDisplayed())))
        onView(withId(R.id.charactersPlaceholder))
            .check(matches(isDisplayed()))
        onView(withId(R.id.characters_list_loader))
            .check(matches(not(isDisplayed())))
        onView(withId(R.id.charactersProgressBar))
            .check(matches(not(isDisplayed())))
    }

    private fun scenarioGenericError() {
        scenario = launchFragmentInContainer {
            CharactersFragment().also { fragment ->
                fragment.viewModel = FakeCharactersViewModel(
                    mockGetCharactersUseCase,
                    mockSaveFavoriteUseCase,
                    mockListModeUseCase,
                    mockGetListModeUseCase,
                    FakeCharactersViewModel.Result.GENERIC_ERROR
                )
            }
        }

        onView(withId(R.id.characters_list))
            .check(matches(not(isDisplayed())))
        onView(withId(R.id.charactersPlaceholder))
            .check(matches(isDisplayed()))
        onView(withId(R.id.characters_list_loader))
            .check(matches(not(isDisplayed())))
        onView(withId(R.id.charactersProgressBar))
            .check(matches(not(isDisplayed())))
    }
    // endregion
}
