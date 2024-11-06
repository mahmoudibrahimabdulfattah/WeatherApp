# Weather App Now & Later App 🌤️

A modern Android weather application built with best practices and latest technologies. The app provides current weather information and 7-day forecast for any city worldwide.

![App Screenshot]

## Features ✨

- 🔍 Search weather by city name
- 🌡️ Current weather display (temperature, conditions, feels like)
- 📅 7-day weather forecast
- 💾 Auto-saves last searched city
- 🌓 Material Design 3 UI With Support Dark Mode
- 🚀 Fast and responsive

## Tech Stack 🛠️

- **Architecture Pattern**: MVVM + MVI
- **Clean Architecture** with Use Cases
- **Dependency Injection**: Dagger-Hilt
- **UI**: Jetpack Compose
- **Networking**: Retrofit
- **Local Storage**: Room Database
- **Concurrency**: Kotlin Coroutines
- **API**: OpenWeatherMap

## Architecture 🏗️

The app follows Clean Architecture principles and is organized into layers:

```
app/
├── presentation/
│   ├── screens/
│   ├── components/
│   └── theme/
├── usecases/
├── repository/
├── datasource/
│   ├── local/
│   └── remote/
└── di/
```

## Getting Started 🚀

### Installation
1. Clone the repository:
```bash
git clone https://github.com/mahmoudibrahimabdulfattah/WeatherApp.git
```

2. Open the project in Android Studio

3. Get an API key from [OpenWeatherMap](https://openweathermap.org/api)

4. Add your API key in `AppModule.kt`:
```kotlin
private const val API_KEY = "your_api_key_here"
```

5. Build and run the project

## Future Improvements 🎯

- [ ] Implement comprehensive Unit Testing
- [ ] Create custom weather formatting library
- [ ] Set up CI/CD pipeline
- [ ] Add dark mode support
- [ ] Add more weather details

## Contributing 🤝

Contributions are welcome! Please feel free to submit a Pull Request.

1. Fork the project
2. Create your feature branch (`git checkout -b feature/AmazingFeature`)
3. Commit your changes (`git commit -m 'Add some AmazingFeature'`)
4. Push to the branch (`git push origin feature/AmazingFeature`)
5. Open a Pull Request

## Acknowledgments 🙏

- OpenWeatherMap API for weather data
- Material Design 3 for UI guidelines
- Android Jetpack libraries
