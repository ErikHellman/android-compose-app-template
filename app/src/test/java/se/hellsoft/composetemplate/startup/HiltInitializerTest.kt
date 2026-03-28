package se.hellsoft.composetemplate.startup

import org.junit.Assert.assertTrue
import org.junit.Test

class HiltInitializerTest {

    @Test
    fun dependencies_returnsEmptyList() {
        val initializer = HiltInitializer()
        assertTrue(initializer.dependencies().isEmpty())
    }
}
