# 🏎️ F1 Race HUD

F1RaceHUD is a modern, real‑time telemetry companion for F1 23, F1 24, and F1 25.
Attach your phone to your wheel or cockpit and get a clean, fast, and responsive HUD while driving. The app receives telemetry directly from the game using the official F1 UDP specifications and displays detailed, motorsport‑authentic dashboards with all available data.
Designed for enthusiasts, sim racers, and engineers, F1RaceHUD delivers a precise, minimal‑latency view of race information — without clutter, gimmicks, or distractions. It’s built for performance, accuracy, and pixel‑perfect UI refinement, making it ideal for racing, practice sessions, and improving consistency.

# 🚀 Features

## Real‑time Telemetry
- Live data from the official F1 game UDP stream
- Explicit, version‑specific packet parsing (F1 2021 → F1 25)
- Zero renaming, zero abstraction leaks — 1:1 field mapping
- Unified internal models for stable app‑side consumption

## Motorsport‑Authentic HUD
- Clean, readable, race‑inspired design
- Dynamic widgets: speed, gear, ERS, DRS, RPM, throttle, brake, tyre data, and more
- Smooth animations with Compose UI

## Version‑Aware Architecture
- Each F1 game version has its own explicit packet classes
- Easy to update when Codemasters releases new specs

## Built for Performance
- Kotlin + Jetpack Compose
- Coroutines for efficient streaming
- Minimal allocations during packet parsing
- Optimized for 60–120Hz displays

## 📸 Screenshots

![F1 Race HUD](docs/screenshots/Screenshot_01.png)



# 🤝 Contributing
Contributions are welcome — especially:
- Packet updates for new F1 game versions
- UI improvements
- Performance optimizations
- Bug fixes
  Please open an issue before submitting a PR so we can align on the approach.


# 📄 License
This project is licensed under the terms of the [LICENSE](./LICENSE) file.

# ⭐ Support the Project
If you enjoy the app, consider starring the repository.
It helps visibility and encourages future development.

## Building From Source

To build the project from source, follow these steps:

1. Install the latest stable **Android Studio**.
2. Clone the repository:
   ```bash
   git clone https://github.com/yourname/f1-race-hud.git
   cd f1-race-hud
3. Open Android Studio and select File → Open…, then choose the cloned project folder.
4. Allow Android Studio to download and sync all Gradle dependencies.
5. After the sync completes, select a device or emulator.
6. Click Run to build and launch the app using the default debug configuration.

# 📦 Installation
From Release (recommended)
Download the latest APK from the Releases section.
From Source
git clone https://github.com/yourname/f1-race-hud.git
cd f1-race-hud
./gradlew assembleDebug
