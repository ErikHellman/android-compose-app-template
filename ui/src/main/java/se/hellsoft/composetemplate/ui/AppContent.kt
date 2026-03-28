package se.hellsoft.composetemplate.ui

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.navigation3.runtime.NavEntry
import androidx.navigation3.ui.NavDisplay
import se.hellsoft.composetemplate.ui.navigation.DetailRoute
import se.hellsoft.composetemplate.ui.navigation.HomeRoute
import se.hellsoft.composetemplate.ui.navigation.SettingsRoute
import se.hellsoft.composetemplate.ui.screens.DetailScreen
import se.hellsoft.composetemplate.ui.screens.HomeScreen
import se.hellsoft.composetemplate.ui.screens.SettingsScreen
import se.hellsoft.composetemplate.ui.theme.ComposeTemplateTheme
import se.hellsoft.composetemplate.ui.theme.ThemeMode

@Composable
fun AppContent() {
    var themeMode by remember { mutableStateOf(ThemeMode.System) }

    val darkTheme = when (themeMode) {
        ThemeMode.System -> isSystemInDarkTheme()
        ThemeMode.Light -> false
        ThemeMode.Dark -> true
    }

    ComposeTemplateTheme(darkTheme = darkTheme) {
        Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
            val backStack = remember { mutableStateListOf<Any>(HomeRoute) }

            NavDisplay(
                backStack = backStack,
                onBack = { backStack.removeLastOrNull() },
                modifier = Modifier.padding(innerPadding),
                entryProvider = { key ->
                    when (key) {
                        is HomeRoute -> NavEntry(key) {
                            HomeScreen(
                                onNavigateToDetail = {
                                    backStack.add(DetailRoute(itemId = "sample-123"))
                                },
                                onNavigateToSettings = {
                                    backStack.add(SettingsRoute)
                                },
                            )
                        }
                        is DetailRoute -> NavEntry(key) {
                            DetailScreen(
                                itemId = key.itemId,
                                onBack = { backStack.removeLastOrNull() },
                            )
                        }
                        is SettingsRoute -> NavEntry(key) {
                            SettingsScreen(
                                themeMode = themeMode,
                                onThemeModeChanged = { themeMode = it },
                                onBack = { backStack.removeLastOrNull() },
                            )
                        }
                        else -> NavEntry(Unit) {}
                    }
                },
            )
        }
    }
}
