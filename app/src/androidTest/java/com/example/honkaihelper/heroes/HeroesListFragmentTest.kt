package com.example.honkaihelper.heroes

import androidx.test.espresso.action.ViewActions.replaceText
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.hasDescendant
import androidx.test.espresso.matcher.ViewMatchers.withText
import com.example.honkaihelper.base.BaseView
import com.example.honkaihelper.heroes.adapter.HeroesListAdapter
import com.example.honkaihelper.utils.atPosition
import org.junit.Test

class HeroesListFragmentTest : BaseView() {

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
}