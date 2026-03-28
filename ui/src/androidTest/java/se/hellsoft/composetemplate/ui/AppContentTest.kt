package se.hellsoft.composetemplate.ui

import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.assertIsSelected
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performClick
import org.junit.Rule
import org.junit.Test

class AppContentTest {

    @get:Rule
    val composeTestRule = createComposeRule()

    @Test
    fun appContent_startsOnHomeScreen() {
        composeTestRule.setContent {
            AppContent()
        }

        composeTestRule.onNodeWithText("Home").assertIsDisplayed()
        composeTestRule.onNodeWithText("View Detail").assertIsDisplayed()
        composeTestRule.onNodeWithText("Settings").assertIsDisplayed()
    }

    @Test
    fun appContent_navigatesToDetailScreen() {
        composeTestRule.setContent {
            AppContent()
        }

        composeTestRule.onNodeWithText("View Detail").performClick()

        composeTestRule.onNodeWithText("Detail").assertIsDisplayed()
        composeTestRule.onNodeWithText("Item ID: sample-123").assertIsDisplayed()
    }

    @Test
    fun appContent_navigatesToSettingsScreen() {
        composeTestRule.setContent {
            AppContent()
        }

        composeTestRule.onNodeWithText("Settings").performClick()

        // "Settings" is both the button text on Home and the title on Settings screen.
        // After navigation, the Home screen is gone, so "Theme" section confirms we're on Settings.
        composeTestRule.onNodeWithText("Theme").assertIsDisplayed()
        composeTestRule.onNodeWithText("System").assertIsDisplayed()
    }

    @Test
    fun appContent_navigatesBackFromDetail() {
        composeTestRule.setContent {
            AppContent()
        }

        composeTestRule.onNodeWithText("View Detail").performClick()
        composeTestRule.onNodeWithText("Back").performClick()

        composeTestRule.onNodeWithText("Home").assertIsDisplayed()
    }

    @Test
    fun appContent_navigatesBackFromSettings() {
        composeTestRule.setContent {
            AppContent()
        }

        composeTestRule.onNodeWithText("Settings").performClick()
        composeTestRule.onNodeWithText("Back").performClick()

        composeTestRule.onNodeWithText("Home").assertIsDisplayed()
    }

    @Test
    fun appContent_themeToggle_selectsDarkMode() {
        composeTestRule.setContent {
            AppContent()
        }

        composeTestRule.onNodeWithText("Settings").performClick()
        composeTestRule.onNodeWithText("Dark").performClick()

        composeTestRule.onNodeWithText("Dark").assertIsSelected()
    }

    @Test
    fun appContent_themeToggle_selectsLightMode() {
        composeTestRule.setContent {
            AppContent()
        }

        composeTestRule.onNodeWithText("Settings").performClick()
        composeTestRule.onNodeWithText("Light").performClick()

        composeTestRule.onNodeWithText("Light").assertIsSelected()
    }

    @Test
    fun appContent_themeToggle_persistsAcrossNavigation() {
        composeTestRule.setContent {
            AppContent()
        }

        // Go to settings, change to Dark
        composeTestRule.onNodeWithText("Settings").performClick()
        composeTestRule.onNodeWithText("Dark").performClick()

        // Navigate back and return to settings
        composeTestRule.onNodeWithText("Back").performClick()
        composeTestRule.onNodeWithText("Settings").performClick()

        // Dark should still be selected
        composeTestRule.onNodeWithText("Dark").assertIsSelected()
    }
}
