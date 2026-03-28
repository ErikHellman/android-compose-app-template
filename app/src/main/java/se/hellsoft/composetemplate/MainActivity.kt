package se.hellsoft.composetemplate

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.navigation3.runtime.NavEntry
import androidx.navigation3.ui.NavDisplay
import dagger.hilt.android.AndroidEntryPoint
import se.hellsoft.composetemplate.ui.navigation.DetailRoute
import se.hellsoft.composetemplate.ui.navigation.HomeRoute
import se.hellsoft.composetemplate.ui.navigation.SettingsRoute
import se.hellsoft.composetemplate.ui.screens.DetailScreen
import se.hellsoft.composetemplate.ui.screens.HomeScreen
import se.hellsoft.composetemplate.ui.screens.SettingsScreen
import se.hellsoft.composetemplate.ui.theme.ComposeTemplateTheme

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            ComposeTemplateTheme {
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
    }
}
