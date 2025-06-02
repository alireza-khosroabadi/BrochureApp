# BrochureApp

A modern Android application built with Jetpack Compose, following clean architecture principles and MVVM pattern. This app provides a platform for browsing and managing brochures with a focus on user experience and performance.

## 🚀 Features

- **Modern UI**: Built with Jetpack Compose and Material 3
- **Clean Architecture**: Following SOLID principles and separation of concerns
- **MVVM Pattern**: For better separation of UI and business logic
- **Dependency Injection**: Using Hilt for efficient dependency management
- **Modular Structure**: Feature-based modularization for better scalability
- **Offline Support**: Caching mechanism for offline access
- **Error Handling**: Graceful error handling and user feedback
- **Testing**: Comprehensive test coverage including unit and UI tests

## 🛠 Tech Stack

- **Language**: Kotlin
- **UI Framework**: Jetpack Compose
- **Architecture Pattern**: MVVM
- **Dependency Injection**: Hilt
- **Minimum SDK**: 24
- **Target SDK**: 35
- **Build System**: Gradle (Kotlin DSL)

## 📦 Project Structure

The project follows a modular architecture with the following modules:

- `app`: Main application module
- `core`: Core functionality modules
  - `ui`: Common UI components
  - `designSystem`: Design system components
  - `network`: Network handling and API clients
  - `model`: Common data models and entities
- `feature-brochure`: Brochure feature module
- `data`: Data layer implementation
- `domain`: Domain layer with business logic

## 🔄 Module Dependencies

See the detailed module dependencies in [docs/module-dependencies.md](docs/module-dependencies.md)

## 🏗 Architecture

The project follows Clean Architecture principles with the following layers:

- **Presentation Layer**: UI components and ViewModels
- **Domain Layer**: Business logic and use cases
- **Data Layer**: Repository implementations and data sources

### Key Components

- **Use Cases**: Business logic implementation
- **Repositories**: Data access abstraction
- **ViewModels**: UI state management
- **Compose UI**: Modern UI implementation
- **DI Modules**: Dependency injection setup
- **Network**: API clients and network handling
- **Models**: Common data structures


## 🚀 Getting Started

### Prerequisites

- Android Studio Hedgehog (2023.1.1) or later
- JDK 11 or later
- Android SDK 35
- Kotlin 1.9.0 or later

### Installation

1. Clone the repository:
```bash
git clone https://github.com/yourusername/BrochureApp.git
```

2. Open the project in Android Studio

3. Sync the project with Gradle files

4. Run the application on an emulator or physical device

## 🧪 Testing

The project includes comprehensive testing:

- **Unit Tests**: Testing business logic and ViewModels
- **UI Tests**: Testing Compose UI components
- **Integration Tests**: Testing module interactions
- **Test Coverage**: Aiming for high test coverage

### Running Tests

```bash
# Run all tests
./gradlew test

# Run specific test module
./gradlew :feature-brochure:test
```

## 📱 Screenshots

[Add screenshots of your app here]

## 🔧 Configuration

The app can be configured through:

- `local.properties`: Local development settings
- `gradle.properties`: Build configuration
- Environment variables for API endpoints

## 🙏 Acknowledgments

- [Jetpack Compose](https://developer.android.com/jetpack/compose)
- [Material 3](https://m3.material.io/)
- [Hilt](https://developer.android.com/training/dependency-injection/hilt-android)
- [Clean Architecture](https://blog.cleancoder.com/uncle-bob/2012/08/13/the-clean-architecture.html) 