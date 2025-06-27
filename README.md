# BrochureApp

A modern, modular Android application built with Jetpack Compose, Clean Architecture, and MVVM. BrochureApp provides a platform for browsing and managing brochures, with a focus on user experience, scalability, and robust testing.

---

## 🚀 Features

- **Modern UI:** Built with Jetpack Compose and Material 3
- **Clean Architecture:** SOLID principles, separation of concerns, and modularization
- **MVVM Pattern:** Clear separation of UI and business logic
- **Dependency Injection:** Powered by Hilt
- **Offline Support:** Caching for offline access
- **Error Handling:** Graceful error handling and user feedback
- **Comprehensive Testing:** Unit, integration, and UI/Compose tests
- **Preview-Driven Development:** Parameterized Compose previews for all major screens

---

## 🛠 Tech Stack

- **Language:** Kotlin
- **UI Framework:** Jetpack Compose
- **Architecture:** MVVM, Clean Architecture
- **Dependency Injection:** Hilt
- **Networking:** Retrofit, OkHttp, MockWebServer
- **Database:** Room
- **Testing:** JUnit, MockK, Turbine, MockWebServer, Compose UI Test
- **Build System:** Gradle (Kotlin DSL)
- **Minimum SDK:** 24
- **Target SDK:** 35

---

## 📦 Project Structure

The project is organized into feature and core modules for maximum scalability and maintainability:

- `app`: Main application entry point
- `core`:
  - `ui`: Common Compose UI components
  - `designSystem`: Theming, typography, and design tokens
  - `network`: API clients, data sources, and network models
  - `database`: Room database, DAOs, and entities
  - `model`: Shared data models and entities
- `domain`: Business logic, use cases, and repository interfaces
- `data`: Data layer, mappers, repository implementations, and data sources
- `feature-brochure`: Brochure listing and filtering feature
- `feature-brochureDetail`: Brochure detail and related screens

---

## 🔄 Module Dependencies

See [docs/module-dependencies.md](docs/module-dependencies.md) for a detailed module dependency graph.

---

## 🏗 Architecture

BrochureApp follows Clean Architecture:

- **Presentation Layer:** Compose UI, ViewModels, and navigation
- **Domain Layer:** Use cases, business logic, and repository interfaces
- **Data Layer:** Repository implementations, data sources, mappers, and cache

---

## 🧪 Testing

BrochureApp is built with testing in mind:

- **Unit Tests:** For business logic, ViewModels, repositories, mappers, and error handling
- **UI/Compose Tests:** For all major screens, user interactions, and state rendering
- **Integration Tests:** For network layer (using MockWebServer) and module interactions
- **Test Coverage:** High coverage across all layers

### Running Tests

```bash
# Run all unit tests
./gradlew test

# Run instrumented UI tests
./gradlew connectedAndroidTest

# Run tests for a specific module
./gradlew :feature-brochure:test
```

---

## 🖼️ Compose Previews & UI Testing

- **PreviewParameterProvider** is used for parameterized Compose previews, enabling design-time inspection and robust UI/Compose tests for multiple states and data variations.
- All major screens have previews for both normal and edge cases (e.g., empty, error, loading).
- UI tests cover loading, success, error, empty, and user interaction states.

---

## 📱 Screenshots

[Add screenshots of your app here]

---

## 🔧 Configuration

- `local.properties`: Local development settings
- `gradle.properties`: Build configuration
- Environment variables for API endpoints

---

## 🙏 Acknowledgments

- [Jetpack Compose](https://developer.android.com/jetpack/compose)
- [Material 3](https://m3.material.io/)
- [Hilt](https://developer.android.com/training/dependency-injection/hilt-android)
- [Room](https://developer.android.com/training/data-storage/room)
- [MockWebServer](https://square.github.io/okhttp/features/mockwebserver/)
- [Clean Architecture](https://blog.cleancoder.com/uncle-bob/2012/08/13/the-clean-architecture.html)

---

**Feel free to further customize this README with badges, contribution guidelines, or more screenshots as your project evolves!** 