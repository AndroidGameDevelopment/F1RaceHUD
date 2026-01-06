# 🏎️ F1 Race HUD

A modern, real‑time Formula 1 telemetry HUD for Android
F1 Race HUD is a high‑performance Android application that displays live Formula 1 telemetry in a clean, motorsport‑authentic interface.
It is designed for enthusiasts, sim racers, engineers, and anyone who wants a precise, minimal‑latency view of race data — without clutter, gimmicks, or distractions.
This project focuses on accuracy, explicit packet handling, and pixel‑perfect UI refinement, following the official F1 game UDP telemetry specifications.

# 🚀 Features

## ✅ Real‑time Telemetry
- Live data from the official F1 game UDP stream
- Explicit, version‑specific packet parsing (F1 2021 → F1 25)
- Zero renaming, zero abstraction leaks — 1:1 field mapping
- Unified internal models for stable app‑side consumption

## ✅ Motorsport‑Authentic HUD
- Clean, readable, race‑inspired design
- High‑contrast color palette optimized for track visibility
- Dynamic widgets: speed, gear, ERS, DRS, RPM, throttle, brake, tyre data, and more
- Smooth animations with Compose UI

## ✅ Version‑Aware Architecture
- Each F1 game version has its own explicit packet classes
- No guessing, no magic, no hidden conversions
- Easy to update when Codemasters releases new specs

## ✅ Built for Performance
- Kotlin + Jetpack Compose
- Coroutines for efficient streaming
- Minimal allocations during packet parsing
- Optimized for 60–120Hz displays

📸 Screenshots
(Add your images here once the repo is public)
![HUD Screenshot](docs/screenshot_1.jpg)
![Telemetry View](docs/screenshot_2.jpg)



# 🤝 Contributing
Contributions are welcome — especially:
- Packet updates for new F1 game versions
- UI improvements
- Performance optimizations
- Bug fixes
  Please open an issue before submitting a PR so we can align on the approach.

# 📄 License
Link to license

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
