package se.hellsoft.composetemplate.di

import android.content.Context
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import io.ktor.client.HttpClient
import io.ktor.client.engine.okhttp.OkHttp
import io.ktor.client.plugins.auth.Auth
import io.ktor.client.plugins.auth.providers.BearerTokens
import io.ktor.client.plugins.auth.providers.bearer
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.serialization.kotlinx.json.json
import se.hellsoft.composetemplate.data.TokenRepository
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideTokenRepository(
        @ApplicationContext context: Context,
    ): TokenRepository = TokenRepository(context)

    @Provides
    @Singleton
    fun provideHttpClient(
        tokenRepository: TokenRepository,
    ): HttpClient = HttpClient(OkHttp) {
        install(ContentNegotiation) {
            json()
        }
        install(Auth) {
            bearer {
                loadTokens {
                    val access = tokenRepository.getAccessToken()
                    val refresh = tokenRepository.getRefreshToken()
                    if (access != null && refresh != null) {
                        BearerTokens(access, refresh)
                    } else {
                        null
                    }
                }
                refreshTokens {
                    // TODO: Call your token refresh endpoint here
                    // Example:
                    // val response = client.post("https://api.example.com/auth/refresh") {
                    //     setBody(mapOf("refresh_token" to oldTokens?.refreshToken))
                    // }
                    // val newTokens = response.body<TokenResponse>()
                    // tokenRepository.saveTokens(newTokens.accessToken, newTokens.refreshToken)
                    // BearerTokens(newTokens.accessToken, newTokens.refreshToken)
                    null
                }
            }
        }
    }
}
