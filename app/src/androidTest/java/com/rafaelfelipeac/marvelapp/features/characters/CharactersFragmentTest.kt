package com.rafaelfelipeac.marvelapp.features.characters

import androidx.fragment.app.testing.FragmentScenario
import androidx.fragment.app.testing.launchFragmentInContainer
import androidx.recyclerview.widget.RecyclerView
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.Espresso.openActionBarOverflowOrOptionsMenu
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.contrib.RecyclerViewActions
import androidx.test.espresso.matcher.RootMatchers.withDecorView
import androidx.test.espresso.matcher.ViewMatchers.isDisplayed
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.espresso.matcher.ViewMatchers.withText
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.platform.app.InstrumentationRegistry.getInstrumentation
import com.rafaelfelipeac.marvelapp.R
import com.rafaelfelipeac.marvelapp.base.DataProviderAndroidTest.mockCharacterName
import com.rafaelfelipeac.marvelapp.base.FakeCharactersViewModel
import com.rafaelfelipeac.marvelapp.base.EspressoHelper
import com.rafaelfelipeac.marvelapp.features.characters.domain.usecase.GetCharactersUseCase
import com.rafaelfelipeac.marvelapp.features.characters.presentation.CharactersFragment
import io.mockk.mockk
import org.hamcrest.CoreMatchers.`is`
import org.hamcrest.CoreMatchers.not
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class CharactersFragmentTest {

    private lateinit var scenario: FragmentScenario<CharactersFragment>

    private val mockCharactersUseCase = mockk<GetCharactersUseCase>()

    @Test
    fun launchCharactersFragmentAndVerifyUI() {
        launchFragmentInContainer<CharactersFragment>()

        onView(withId(R.id.charactersTitle))
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
    fun clickOnFirstCharacterThenToastIsShownWithTheCharacterName() {
        scenario = launchFragmentInContainer() {
            CharactersFragment().also { fragment ->
                fragment.viewModel = FakeCharactersViewModel(
                    mockCharactersUseCase,
                    FakeCharactersViewModel.Result.SUCCESS
                )
            }
        }

        onView(withId(R.id.characters_list)).perform(
            RecyclerViewActions.actionOnItemAtPosition<RecyclerView.ViewHolder>(0, click())
        )

        onView(withText(mockCharacterName))
            .inRoot(withDecorView(not(`is`(EspressoHelper().getDecorView(scenario)))))
            .check(matches(isDisplayed()))
    }

    @Test
    fun responseIsSuccessAndUserClickOnRefreshButtonWithSuccessThenListAreRefreshed() {
        scenarioSuccess()

        openActionBarOverflowOrOptionsMenu(getInstrumentation().targetContext)
        onView(withText(R.string.menu_refresh)).perform(click())

        scenarioSuccess()
    }

    @Test
    fun responseIsSuccessAndUserClickOnRefreshButtonWithNetworkErrorThenPlaceholderIsShown() {
        scenarioSuccess()

        openActionBarOverflowOrOptionsMenu(getInstrumentation().targetContext)
        onView(withText(R.string.menu_refresh)).perform(click())

        scenarioNetworkError()
    }

    @Test
    fun responseIsSuccessAndUserClickOnRefreshButtonWithGenericErrorThenPlaceholderIsShown() {
        scenarioSuccess()

        openActionBarOverflowOrOptionsMenu(getInstrumentation().targetContext)
        onView(withText(R.string.menu_refresh)).perform(click())

        scenarioGenericError()
    }

    @Test
    fun responseIsNetworkErrorAndUserClickOnRefreshButtonWithSuccessThenListAreRefreshed() {
        scenarioNetworkError()

        openActionBarOverflowOrOptionsMenu(getInstrumentation().targetContext)
        onView(withText(R.string.menu_refresh)).perform(click())

        scenarioSuccess()
    }

    @Test
    fun responseIsNetworkErrorAndUserClickOnRefreshButtonWithNetworkErrorAndPlaceholderIsShown() {
        scenarioNetworkError()

        openActionBarOverflowOrOptionsMenu(getInstrumentation().targetContext)
        onView(withText(R.string.menu_refresh)).perform(click())

        scenarioNetworkError()
    }

    // region Scenarios
    private fun scenarioSuccess() {
        scenario = launchFragmentInContainer() {
            CharactersFragment().also { fragment ->
                fragment.viewModel = FakeCharactersViewModel(
                    mockCharactersUseCase,
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
        scenario = launchFragmentInContainer() {
            CharactersFragment().also { fragment ->
                fragment.viewModel = FakeCharactersViewModel(
                    mockCharactersUseCase,
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
        scenario = launchFragmentInContainer() {
            CharactersFragment().also { fragment ->
                fragment.viewModel = FakeCharactersViewModel(
                    mockCharactersUseCase,
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
