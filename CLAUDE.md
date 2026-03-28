# CLAUDE.md

This file provides guidance to Claude Code (claude.ai/code) when working with code in this repository.

## Build Commands

```bash
./gradlew assembleDebug          # Build debug APK
./gradlew test                   # Run all unit tests
./gradlew connectedAndroidTest   # Run instrumented tests (requires device/emulator)
./gradlew check                  # Compile + lint + unit tests
./gradlew clean                  # Clean build outputs
```

Run tests for a single module:
```bash
./gradlew :network:test
./gradlew :ui:connectedDebugAndroidTest
```

## Architecture

Three-module Kotlin/Compose Android app (`minSdk 31`, `compileSdk 36`):

- **app** — Entry point (`MainActivity`, `ComposeTemplateApplication`). Depends on `network` and `ui`. Uses App Startup framework (`HiltInitializer`) to eagerly create HttpClient and TokenRepository singletons.
- **network** — Hilt-provided singletons: `HttpClient` (Ktor/OkHttp with bearer auth) and `TokenRepository` (DataStore-backed token storage). `NetworkModule` wires these as `@Singleton` in Hilt's `SingletonComponent`.
- **ui** — All Compose UI. Navigation uses `androidx.navigation3` with `@Serializable` route objects (`HomeRoute`, `DetailRoute`, `SettingsRoute`). `AppContent` manages the back stack and theme mode state. Supports Material3 dynamic colors on Android 12+.

Package: `se.hellsoft.composetemplate` (with `.network` and `.ui` subpackages for library modules).

## Key Dependencies

- **DI**: Hilt 2.59.2 (with KSP)
- **Network**: Ktor 3.4.2 (OkHttp engine, content negotiation, bearer auth)
- **Navigation**: androidx.navigation3 with kotlinx.serialization for type-safe routes
- **Compose**: BOM 2026.03.01, Material3
- **Permissions**: Accompanist Permissions 0.37.3

## Testing

Unit tests use JUnit 4. Compose UI tests use `createComposeRule()` with `onNodeWithText()` selectors and `performClick()`/`assertIsDisplayed()` assertions. Async tests use `kotlinx.coroutines.test.runTest`. Instrumented tests (TokenRepository, navigation, screen tests) require a connected device or emulator.
