package se.hellsoft.composetemplate.ui.screens

import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.assertIsSelected
import androidx.compose.ui.test.assertIsNotSelected
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performClick
import org.junit.Assert.assertEquals
import org.junit.Assert.assertTrue
import org.junit.Rule
import org.junit.Test
import se.hellsoft.composetemplate.ui.theme.ThemeMode

class SettingsScreenTest {

    @get:Rule
    val composeTestRule = createComposeRule()

    @Test
    fun settingsScreen_displaysTitle() {
        composeTestRule.setContent {
            SettingsScreen(
                themeMode = ThemeMode.System,
                onThemeModeChanged = {},
                onBack = {},
            )
        }

        composeTestRule.onNodeWithText("Settings").assertIsDisplayed()
    }

    @Test
    fun settingsScreen_displaysThemeSection() {
        composeTestRule.setContent {
            SettingsScreen(
                themeMode = ThemeMode.System,
                onThemeModeChanged = {},
                onBack = {},
            )
        }

        composeTestRule.onNodeWithText("Theme").assertIsDisplayed()
        composeTestRule.onNodeWithText("System").assertIsDisplayed()
        composeTestRule.onNodeWithText("Light").assertIsDisplayed()
        composeTestRule.onNodeWithText("Dark").assertIsDisplayed()
    }

    @Test
    fun settingsScreen_systemChipIsSelected_whenThemeModeIsSystem() {
        composeTestRule.setContent {
            SettingsScreen(
                themeMode = ThemeMode.System,
                onThemeModeChanged = {},
                onBack = {},
            )
        }

        composeTestRule.onNodeWithText("System").assertIsSelected()
        composeTestRule.onNodeWithText("Light").assertIsNotSelected()
        composeTestRule.onNodeWithText("Dark").assertIsNotSelected()
    }

    @Test
    fun settingsScreen_darkChipIsSelected_whenThemeModeIsDark() {
        composeTestRule.setContent {
            SettingsScreen(
                themeMode = ThemeMode.Dark,
                onThemeModeChanged = {},
                onBack = {},
            )
        }

        composeTestRule.onNodeWithText("Dark").assertIsSelected()
        composeTestRule.onNodeWithText("System").assertIsNotSelected()
        composeTestRule.onNodeWithText("Light").assertIsNotSelected()
    }

    @Test
    fun settingsScreen_clickingLightChip_invokesCallbackWithLight() {
        var selectedMode: ThemeMode? = null
        composeTestRule.setContent {
            SettingsScreen(
                themeMode = ThemeMode.System,
                onThemeModeChanged = { selectedMode = it },
                onBack = {},
            )
        }

        composeTestRule.onNodeWithText("Light").performClick()
        assertEquals(ThemeMode.Light, selectedMode)
    }

    @Test
    fun settingsScreen_clickingDarkChip_invokesCallbackWithDark() {
        var selectedMode: ThemeMode? = null
        composeTestRule.setContent {
            SettingsScreen(
                themeMode = ThemeMode.System,
                onThemeModeChanged = { selectedMode = it },
                onBack = {},
            )
        }

        composeTestRule.onNodeWithText("Dark").performClick()
        assertEquals(ThemeMode.Dark, selectedMode)
    }

    @Test
    fun settingsScreen_displaysPermissionsSection() {
        composeTestRule.setContent {
            SettingsScreen(
                themeMode = ThemeMode.System,
                onThemeModeChanged = {},
                onBack = {},
            )
        }

        composeTestRule.onNodeWithText("Permissions").assertIsDisplayed()
    }

    @Test
    fun settingsScreen_displaysBackButton() {
        composeTestRule.setContent {
            SettingsScreen(
                themeMode = ThemeMode.System,
                onThemeModeChanged = {},
                onBack = {},
            )
        }

        composeTestRule.onNodeWithText("Back").assertIsDisplayed()
    }

    @Test
    fun settingsScreen_backButton_invokesCallback() {
        var clicked = false
        composeTestRule.setContent {
            SettingsScreen(
                themeMode = ThemeMode.System,
                onThemeModeChanged = {},
                onBack = { clicked = true },
            )
        }

        composeTestRule.onNodeWithText("Back").performClick()
        assertTrue(clicked)
    }
}
