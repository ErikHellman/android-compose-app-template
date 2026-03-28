# Compose Template

A template Android project for bootstrapping modern Kotlin/Compose apps. Designed for use with the [android CLI (`acli`)](https://github.com/ErikHellman/android-cli):

```bash
acli project init https://github.com/ErikHellman/ComposeTemplate \
  --output my-app \
  --package com.example.myapp
```

## Project Structure

The project is organized into three Gradle modules:

- **app** — Application entry point. Contains `MainActivity`, the Hilt application class, and an App Startup initializer that eagerly creates network singletons.
- **network** — HTTP client and token management. Provides a Ktor `HttpClient` (OkHttp engine) with bearer token authentication and a `TokenRepository` backed by DataStore for persisting auth tokens.
- **ui** — All Compose UI code. Screens, navigation, and theming live here. Navigation uses the new `androidx.navigation3` library with type-safe `@Serializable` route objects.

## Libraries

| Category | Library | Notes |
|---|---|---|
| UI | Jetpack Compose (Material 3) | Dynamic color support on Android 12+ |
| Navigation | androidx.navigation3 | Type-safe routes via kotlinx.serialization |
| Dependency Injection | Hilt | With KSP annotation processing |
| Networking | Ktor (OkHttp engine) | Content negotiation, bearer auth plugin |
| Token Storage | DataStore Preferences | Async, coroutine-based key-value store |
| Permissions | Accompanist Permissions | Declarative permission handling in Compose |

## Build & Test

Requires JDK 21 for the Gradle daemon.

```bash
# Build
./gradlew assembleDebug

# Unit tests (no device needed)
./gradlew test

# Instrumented tests (requires connected device or emulator)
./gradlew connectedAndroidTest

# All checks (compile + lint + unit tests)
./gradlew check
```

Or using `acli`:

```bash
acli build assemble
acli build test
```

## Tests

The template includes tests across all three modules to serve as examples:

**Unit tests** (`./gradlew test`)
- `HiltInitializerTest` — Verifies the App Startup initializer has no dependencies.
- `NetworkModuleTest` — Verifies the Hilt module is a singleton object.
- `ThemeModeTest` — Verifies theme mode enum entries.

**Instrumented tests** (`./gradlew connectedAndroidTest`)
- `TokenRepositoryTest` — Tests token save, retrieve, overwrite, and clear operations against a real DataStore.
- `AppContentTest` — Navigation flow and theme toggle integration tests.
- `HomeScreenTest` — Home screen button display and click callbacks.
- `DetailScreenTest` — Detail screen rendering with item ID parameter.
- `SettingsScreenTest` — Theme selection chips and permission request UI.

## License

```
Copyright 2025 Erik Hellman

Licensed under the Apache License, Version 2.0
```
