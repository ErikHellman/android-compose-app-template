package se.hellsoft.composetemplate.network.data

import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.platform.app.InstrumentationRegistry
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Assert.assertNull
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class TokenRepositoryTest {

    private lateinit var tokenRepository: TokenRepository

    @Before
    fun setup() {
        val context = InstrumentationRegistry.getInstrumentation().targetContext
        tokenRepository = TokenRepository(context)
    }

    @Test
    fun getTokens_returnsNull_whenNoTokensSaved() = runTest {
        tokenRepository.clearTokens()

        assertNull(tokenRepository.getAccessToken())
        assertNull(tokenRepository.getRefreshToken())
    }

    @Test
    fun saveTokens_thenGetTokens_returnsSavedValues() = runTest {
        tokenRepository.saveTokens("access_123", "refresh_456")

        assertEquals("access_123", tokenRepository.getAccessToken())
        assertEquals("refresh_456", tokenRepository.getRefreshToken())
    }

    @Test
    fun saveTokens_overwritesPreviousValues() = runTest {
        tokenRepository.saveTokens("old_access", "old_refresh")
        tokenRepository.saveTokens("new_access", "new_refresh")

        assertEquals("new_access", tokenRepository.getAccessToken())
        assertEquals("new_refresh", tokenRepository.getRefreshToken())
    }

    @Test
    fun clearTokens_removesAllTokens() = runTest {
        tokenRepository.saveTokens("access_123", "refresh_456")
        tokenRepository.clearTokens()

        assertNull(tokenRepository.getAccessToken())
        assertNull(tokenRepository.getRefreshToken())
    }
}
