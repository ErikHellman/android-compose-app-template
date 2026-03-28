package se.hellsoft.composetemplate.ui.theme

import org.junit.Assert.assertEquals
import org.junit.Test

class ThemeModeTest {

    @Test
    fun themeMode_hasThreeEntries() {
        assertEquals(3, ThemeMode.entries.size)
    }

    @Test
    fun themeMode_entriesAreInCorrectOrder() {
        val entries = ThemeMode.entries
        assertEquals(ThemeMode.System, entries[0])
        assertEquals(ThemeMode.Light, entries[1])
        assertEquals(ThemeMode.Dark, entries[2])
    }

    @Test
    fun themeMode_namesAreCorrect() {
        assertEquals("System", ThemeMode.System.name)
        assertEquals("Light", ThemeMode.Light.name)
        assertEquals("Dark", ThemeMode.Dark.name)
    }
}
