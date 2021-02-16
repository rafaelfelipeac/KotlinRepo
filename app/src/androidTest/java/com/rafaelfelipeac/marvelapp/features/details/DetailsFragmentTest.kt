package com.rafaelfelipeac.marvelapp.features.details

import android.view.View
import androidx.fragment.app.testing.FragmentScenario
import androidx.fragment.app.testing.launchFragmentInContainer
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions
import androidx.test.espresso.assertion.ViewAssertions
import androidx.test.espresso.matcher.RootMatchers
import androidx.test.espresso.matcher.ViewMatchers
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.rafaelfelipeac.marvelapp.R
import com.rafaelfelipeac.marvelapp.base.FakeDetailsViewModel
import com.rafaelfelipeac.marvelapp.features.details.domain.usecase.GetDetailsComicsUseCase
import com.rafaelfelipeac.marvelapp.features.details.domain.usecase.GetDetailsSeriesUseCase
import com.rafaelfelipeac.marvelapp.features.details.domain.usecase.GetDetailsUseCase
import com.rafaelfelipeac.marvelapp.features.details.domain.usecase.SaveFavoriteUseCase
import com.rafaelfelipeac.marvelapp.features.details.presentation.DetailsFragment
import io.mockk.mockk
import org.hamcrest.CoreMatchers
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class DetailsFragmentTest {

    private lateinit var scenario: FragmentScenario<DetailsFragment>

    private val mockGetDetailsUseCase = mockk<GetDetailsUseCase>()
    private val mockGetDetailsComicsUseCase = mockk<GetDetailsComicsUseCase>()
    private val mockGetDetailsSeriesUseCase = mockk<GetDetailsSeriesUseCase>()
    private val mockSaveFavoriteUseCase = mockk<SaveFavoriteUseCase>()

    @Test
    fun launchDetailsFragmentAndVerifyUI() {
        launchFragmentInContainer<DetailsFragment>()

        onView(ViewMatchers.withId(R.id.detailsCharacterImage))
            .check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
    }

    @Test
    fun responseIsSuccessThenCharacterInformationAreVisible() {
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
    fun responseIsSuccessAndUserClickOnFavoriteButtonThenAToastIsShowed() {
        scenarioSuccess()

        onView(ViewMatchers.withId(R.id.detailsFavorite)).perform(ViewActions.click())

        onView(ViewMatchers.withText(R.string.details_added_favorites))
            .inRoot(
                RootMatchers.withDecorView(
                    CoreMatchers.not(
                        CoreMatchers.`is`(
                            getDecorView(
                                scenario
                            )
                        )
                    )
                )
            )
            .check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
    }

    // region Scenarios
    private fun scenarioSuccess() {
        scenario = launchFragmentInContainer() {
            DetailsFragment().also { fragment ->
                fragment.viewModel = FakeDetailsViewModel(
                    mockGetDetailsUseCase,
                    mockGetDetailsComicsUseCase,
                    mockGetDetailsSeriesUseCase,
                    mockSaveFavoriteUseCase,
                    FakeDetailsViewModel.Result.SUCCESS
                )
            }
        }

        onView(ViewMatchers.withId(R.id.detailsCharacterImage))
            .check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
        onView(ViewMatchers.withId(R.id.detailsCharacterDescription))
            .check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
        onView(ViewMatchers.withId(R.id.detailsFavorite))
            .check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
        onView(ViewMatchers.withId(R.id.detailsPlaceholder))
            .check(ViewAssertions.matches(CoreMatchers.not(ViewMatchers.isDisplayed())))
    }

    private fun scenarioNetworkError() {
        scenario = launchFragmentInContainer() {
            DetailsFragment().also { fragment ->
                fragment.viewModel = FakeDetailsViewModel(
                    mockGetDetailsUseCase,
                    mockGetDetailsComicsUseCase,
                    mockGetDetailsSeriesUseCase,
                    mockSaveFavoriteUseCase,
                    FakeDetailsViewModel.Result.NETWORK_ERROR
                )
            }
        }

        onView(ViewMatchers.withId(R.id.detailsPlaceholder))
            .check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
        onView(ViewMatchers.withId(R.id.detailsFavorite))
            .check(ViewAssertions.matches(CoreMatchers.not(ViewMatchers.isDisplayed())))
        onView(ViewMatchers.withId(R.id.detailsCharacterComicsListLoader))
            .check(ViewAssertions.matches(CoreMatchers.not(ViewMatchers.isDisplayed())))
        onView(ViewMatchers.withId(R.id.detailsCharacterSeriesListLoader))
            .check(ViewAssertions.matches(CoreMatchers.not(ViewMatchers.isDisplayed())))
    }

    private fun scenarioGenericError() {
        scenario = launchFragmentInContainer() {
            DetailsFragment().also { fragment ->
                fragment.viewModel = FakeDetailsViewModel(
                    mockGetDetailsUseCase,
                    mockGetDetailsComicsUseCase,
                    mockGetDetailsSeriesUseCase,
                    mockSaveFavoriteUseCase,
                    FakeDetailsViewModel.Result.GENERIC_ERROR
                )
            }
        }

        onView(ViewMatchers.withId(R.id.detailsPlaceholder))
            .check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
        onView(ViewMatchers.withId(R.id.detailsFavorite))
            .check(ViewAssertions.matches(CoreMatchers.not(ViewMatchers.isDisplayed())))
        onView(ViewMatchers.withId(R.id.detailsCharacterComicsListLoader))
            .check(ViewAssertions.matches(CoreMatchers.not(ViewMatchers.isDisplayed())))
        onView(ViewMatchers.withId(R.id.detailsCharacterSeriesListLoader))
            .check(ViewAssertions.matches(CoreMatchers.not(ViewMatchers.isDisplayed())))
    }
    // endregion

    private fun getDecorView(scenario: FragmentScenario<DetailsFragment>): View {
        var decorView: View? = null

        scenario.onFragment {
            decorView = it.activity?.window?.decorView
        }

        return decorView as View
    }
}
