# Modular Android Application

This is a modular Android application. It displays a list of brochures with support for filtering based on distance, offline cache, and clean error handling using Jetpack Compose and Clean Architecture principles.

---

## 💡 Features

- 📍 Filter brochures within 5km
- 🔌 Offline support using in-memory cache
- 💥 Graceful error handling (e.g. no internet, server error)
- 🎨 Built fully with Jetpack Compose
- 🧪 Unit tested ViewModel and UseCases

---

## 🧱 Architecture

The project follows **Clean Architecture**:
- **Domain layer** is platform-agnostic and pure Kotlin
- **Data layer** handles API calls and local cache
- **Feature module** contains UI + ViewModel
- **Core module** provides reusable UI and utilities

Each layer depends only on the layer directly below it.

---

## 🧰 Tech Stack

- **Kotlin**
- **Jetpack Compose**
- **Hilt (Dependency Injection)**
- **Coroutines + Flow**
- **Retrofit**
- **MockK + JUnit + Turbine** (for unit testing)

---

## 🔧 Content Parsing Strategy

To support multiple `contentType` values from the API (e.g., `"brochure"`, `"brochurePremium"`, `"superBannerCarousel"`, etc.), the `content` field is stored as a raw `JsonElement`. A simple **factory pattern** is used to parse this into the correct data model based on its `contentType`.

This design allows:

- 🧩 Easy extension for new content types in the future
- 🔄 Loose coupling between API response and app logic
- 🧼 Clean separation of parsing logic per content type

---

## 💡 API Notes

### 🔐 API Configuration

For simplicity and clarity during the coding challenge, the **API base URL is currently hardcoded** in the Retrofit setup.

In a real-world project, I would follow best practices by externalizing sensitive or environment-specific configuration values (such as base URLs, API keys, etc.) using a `secrets.defaults.properties` file or managing them via **Gradle build config** and **CI/CD secrets**.

This approach was chosen intentionally to make the project easy to run and review, without requiring extra configuration steps.

---

## 🚀 Run the App

1. Open in Android Studio (Giraffe or newer)
2. Build and run the app module (configured by default)
3. Requires network on first launch to fetch brochures

---

## 🧪 Tests

- ViewModel unit tests for:
    - Loading, success, empty, and error states
    - Filter toggle and offline fallback
- UseCase logic fully testable
- Uses `StandardTestDispatcher`, `MockK`, and `Turbine`

---

## 📝 Assumptions

- `distance` is already provided by the API
- Only content types `"brochure"` and `"brochurePremium"` are considered valid
- Cache is kept in-memory and is cleared when the app process ends

---

## 🚀 Future Improvements

- [ ] Replace in-memory cache with Room or DataStore for persistence
- [ ] Add pagination for large brochure lists
- [ ] Add more Compose UI tests
- [ ] Implement better offline-first UX (e.g., last updated label)
- [ ] Cache raw DTOs or use local entities for versioning/offline filtering

> 🧠 Due to time constraints and to keep the implementation lightweight, I used in-memory caching of domain models. For production, I would introduce persistent storage (Room or DataStore), potentially with a local-first strategy and pagination support.

---

## 👨‍💻 Author

**Alireza Khosrowabadi**  
Senior Android Engineer

