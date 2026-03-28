package se.hellsoft.composetemplate.startup

import android.content.Context
import androidx.startup.Initializer
import dagger.hilt.EntryPoint
import dagger.hilt.InstallIn
import dagger.hilt.android.EntryPointAccessors
import dagger.hilt.components.SingletonComponent
import io.ktor.client.HttpClient
import se.hellsoft.composetemplate.network.data.TokenRepository

class HiltInitializer : Initializer<Unit> {

    @EntryPoint
    @InstallIn(SingletonComponent::class)
    interface HiltInitializerEntryPoint {
        fun httpClient(): HttpClient
        fun tokenRepository(): TokenRepository
    }

    override fun create(context: Context) {
        val entryPoint = EntryPointAccessors.fromApplication(
            context.applicationContext,
            HiltInitializerEntryPoint::class.java
        )
        // Eagerly initialize Hilt-provided singletons
        entryPoint.httpClient()
        entryPoint.tokenRepository()
    }

    override fun dependencies(): List<Class<out Initializer<*>>> = emptyList()
}
