package se.hellsoft.composetemplate.ui.screens

import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performClick
import org.junit.Assert.assertTrue
import org.junit.Rule
import org.junit.Test

class HomeScreenTest {

    @get:Rule
    val composeTestRule = createComposeRule()

    @Test
    fun homeScreen_displaysTitle() {
        composeTestRule.setContent {
            HomeScreen(onNavigateToDetail = {}, onNavigateToSettings = {})
        }

        composeTestRule.onNodeWithText("Home").assertIsDisplayed()
    }

    @Test
    fun homeScreen_displaysViewDetailButton() {
        composeTestRule.setContent {
            HomeScreen(onNavigateToDetail = {}, onNavigateToSettings = {})
        }

        composeTestRule.onNodeWithText("View Detail").assertIsDisplayed()
    }

    @Test
    fun homeScreen_displaysSettingsButton() {
        composeTestRule.setContent {
            HomeScreen(onNavigateToDetail = {}, onNavigateToSettings = {})
        }

        composeTestRule.onNodeWithText("Settings").assertIsDisplayed()
    }

    @Test
    fun homeScreen_viewDetailButton_invokesCallback() {
        var clicked = false
        composeTestRule.setContent {
            HomeScreen(onNavigateToDetail = { clicked = true }, onNavigateToSettings = {})
        }

        composeTestRule.onNodeWithText("View Detail").performClick()
        assertTrue(clicked)
    }

    @Test
    fun homeScreen_settingsButton_invokesCallback() {
        var clicked = false
        composeTestRule.setContent {
            HomeScreen(onNavigateToDetail = {}, onNavigateToSettings = { clicked = true })
        }

        composeTestRule.onNodeWithText("Settings").performClick()
        assertTrue(clicked)
    }
}
