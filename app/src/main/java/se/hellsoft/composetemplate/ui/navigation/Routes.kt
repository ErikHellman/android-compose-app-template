package se.hellsoft.composetemplate.ui.navigation

import kotlinx.serialization.Serializable

@Serializable
data object HomeRoute

@Serializable
data class DetailRoute(val itemId: String)

@Serializable
data object SettingsRoute
