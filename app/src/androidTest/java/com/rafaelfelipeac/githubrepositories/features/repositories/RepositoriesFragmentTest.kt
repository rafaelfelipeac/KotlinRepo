package com.rafaelfelipeac.githubrepositories.features.repositories

import androidx.fragment.app.testing.FragmentScenario
import androidx.fragment.app.testing.launchFragmentInContainer
import androidx.recyclerview.widget.RecyclerView
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.Espresso.openActionBarOverflowOrOptionsMenu
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.contrib.RecyclerViewActions
import androidx.test.espresso.matcher.RootMatchers.withDecorView
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.platform.app.InstrumentationRegistry.getInstrumentation
import com.rafaelfelipeac.githubrepositories.R
import com.rafaelfelipeac.githubrepositories.base.DataProviderAndroidTest.mockRepositoryName
import com.rafaelfelipeac.githubrepositories.base.FakeRepositoriesViewMode
import com.rafaelfelipeac.githubrepositories.base.EspressoHelper
import com.rafaelfelipeac.githubrepositories.features.repositories.domain.usecase.GetRepositoriesUseCase
import com.rafaelfelipeac.githubrepositories.features.repositories.presentation.RepositoriesFragment
import io.mockk.mockk
import org.hamcrest.CoreMatchers.`is`
import org.hamcrest.CoreMatchers.not
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class RepositoriesFragmentTest {

    private lateinit var scenario: FragmentScenario<RepositoriesFragment>

    private val mockRepositoriesUseCase = mockk<GetRepositoriesUseCase>()

    @Test
    fun launchRepositoriesFragmentAndVerifyUI() {
        launchFragmentInContainer<RepositoriesFragment>()

        onView(withId(R.id.repositoriesTitle))
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
    fun clickOnFirstRepositoryThenToastIsShownWithTheRepositoryName() {
        scenario = launchFragmentInContainer() {
            RepositoriesFragment().also { fragment ->
                fragment.viewModel = FakeRepositoriesViewMode(
                    mockRepositoriesUseCase,
                    FakeRepositoriesViewMode.Result.SUCCESS
                )
            }
        }

        onView(withId(R.id.repositoriesList)).perform(
            RecyclerViewActions.actionOnItemAtPosition<RecyclerView.ViewHolder>(0, click())
        )

        onView(withText(mockRepositoryName))
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
            RepositoriesFragment().also { fragment ->
                fragment.viewModel = FakeRepositoriesViewMode(
                    mockRepositoriesUseCase,
                    FakeRepositoriesViewMode.Result.SUCCESS
                )
            }
        }

        onView(withId(R.id.repositoriesList))
            .check(matches(isDisplayed()))
        onView(withId(R.id.repositoriesPlaceholder))
            .check(matches(not(isDisplayed())))
        onView(withId(R.id.repositoriesListLoader))
            .check(matches(not(isDisplayed())))
        onView(withId(R.id.repositoriesProgressBar))
            .check(matches(not(isDisplayed())))
    }

    private fun scenarioNetworkError() {
        scenario = launchFragmentInContainer() {
            RepositoriesFragment().also { fragment ->
                fragment.viewModel = FakeRepositoriesViewMode(
                    mockRepositoriesUseCase,
                    FakeRepositoriesViewMode.Result.NETWORK_ERROR
                )
            }
        }

        onView(withId(R.id.repositoriesList))
            .check(matches(not(isDisplayed())))
        onView(withId(R.id.repositoriesPlaceholder))
            .check(matches(isDisplayed()))
        onView(withId(R.id.repositoriesListLoader))
            .check(matches(not(isDisplayed())))
        onView(withId(R.id.repositoriesProgressBar))
            .check(matches(not(isDisplayed())))
    }

    private fun scenarioGenericError() {
        scenario = launchFragmentInContainer() {
            RepositoriesFragment().also { fragment ->
                fragment.viewModel = FakeRepositoriesViewMode(
                    mockRepositoriesUseCase,
                    FakeRepositoriesViewMode.Result.GENERIC_ERROR
                )
            }
        }

        onView(withId(R.id.repositoriesList))
            .check(matches(not(isDisplayed())))
        onView(withId(R.id.repositoriesPlaceholder))
            .check(matches(isDisplayed()))
        onView(withId(R.id.repositoriesListLoader))
            .check(matches(not(isDisplayed())))
        onView(withId(R.id.repositoriesProgressBar))
            .check(matches(not(isDisplayed())))
    }
    // endregion
}
