package com.example.honkaihelper.heroes

import androidx.fragment.app.testing.launchFragmentInContainer
import androidx.lifecycle.ViewModel
import androidx.test.espresso.action.ViewActions.replaceText
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.hasDescendant
import androidx.test.espresso.matcher.ViewMatchers.isDisplayed
import androidx.test.espresso.matcher.ViewMatchers.withText
import com.example.honkaihelper.base.BaseView
import com.example.honkaihelper.data.NetworkResult
import com.example.honkaihelper.heroes.adapter.HeroesListAdapter
import com.example.honkaihelper.heroes.data.FakeHeroesListRepository
import com.example.honkaihelper.utils.atPosition
import org.junit.Before
import org.junit.Rule
import org.junit.Test

class HeroesListFragmentTest : BaseView() {

    @get:Rule
    val fragmentScenario = launchFragmentInContainer<HeroesListFragment>()

    @Test
    fun testOpenProfileFragmentFromHeroesListFragment() {
        heroesListFragmentView.run {
            profileIcon.click()
            profileFragmentView.buttonGoToAuthorization.check(viewIsDisplayed())
        }
    }

    @Test
    fun testSearchHero() {
        val searchString = "арлан"
        val assertString = "Арлан"
        heroesListFragmentView.run {
            searchItemMenu.click()
            searchItemText.perform(replaceText(searchString))
            recyclerViewHeroesList.check(
                matches(
                    atPosition(
                        0,
                        hasDescendant(withText(assertString))
                    )
                )
            )
        }
    }

    @Test
    fun clickItemHeroesListWithPosition() {
        heroesListFragmentView.run {
            recyclerViewHeroesList.clickOnItem<HeroesListAdapter.HeroesViewHolder>(4)
            teamsListFragmentView.recyclerViewTeamsList.check(viewIsDisplayed())
        }
    }

    @Test
    fun checkStateNoInternet() {
        fragmentScenario.onFragment { it.view }
        heroesListFragmentView.run {
            groupRetry.check(viewIsDisplayed())
        }
    }
}