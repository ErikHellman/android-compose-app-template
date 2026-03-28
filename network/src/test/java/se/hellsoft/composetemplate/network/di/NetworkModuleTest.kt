package se.hellsoft.composetemplate.network.di

import org.junit.Assert.assertEquals
import org.junit.Test

class NetworkModuleTest {

    @Test
    fun networkModule_isSingletonObject() {
        // Verify NetworkModule is an object (singleton) as required by Hilt
        val module = NetworkModule
        val sameModule = NetworkModule
        assertEquals(module, sameModule)
    }
}
