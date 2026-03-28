package se.hellsoft.composetemplate.ui.screens

import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performClick
import org.junit.Assert.assertTrue
import org.junit.Rule
import org.junit.Test

class DetailScreenTest {

    @get:Rule
    val composeTestRule = createComposeRule()

    @Test
    fun detailScreen_displaysTitle() {
        composeTestRule.setContent {
            DetailScreen(itemId = "test-id", onBack = {})
        }

        composeTestRule.onNodeWithText("Detail").assertIsDisplayed()
    }

    @Test
    fun detailScreen_displaysItemId() {
        composeTestRule.setContent {
            DetailScreen(itemId = "abc-123", onBack = {})
        }

        composeTestRule.onNodeWithText("Item ID: abc-123").assertIsDisplayed()
    }

    @Test
    fun detailScreen_displaysBackButton() {
        composeTestRule.setContent {
            DetailScreen(itemId = "test-id", onBack = {})
        }

        composeTestRule.onNodeWithText("Back").assertIsDisplayed()
    }

    @Test
    fun detailScreen_backButton_invokesCallback() {
        var clicked = false
        composeTestRule.setContent {
            DetailScreen(itemId = "test-id", onBack = { clicked = true })
        }

        composeTestRule.onNodeWithText("Back").performClick()
        assertTrue(clicked)
    }

    @Test
    fun detailScreen_displaysDifferentItemIds() {
        composeTestRule.setContent {
            DetailScreen(itemId = "xyz-789", onBack = {})
        }

        composeTestRule.onNodeWithText("Item ID: xyz-789").assertIsDisplayed()
    }
}
