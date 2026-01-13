# <img src="https://github.com/AndroidGameDevelopment/F1RaceHUD/blob/main/app/src/main/ic_launcher-playstore.png?raw=true" width="64" alt="Icon"> F1 Race HUD 
F1RaceHUD is a modern, real‑time telemetry companion for F1 23, F1 24, and F1 25 games.  
Attach your phone to your wheel or cockpit and get a clean, fast, and responsive HUD while driving. The app receives telemetry directly from the game using the official F1 UDP stream and displays detailed, motorsport‑authentic dashboards with all available data.
Designed for sim racers enthusiasts F1RaceHUD delivers a precise, minimal‑latency view of race information — without clutter, gimmicks, or distractions. It’s built for performance and accuracy making it ideal for racing.

# 🚀 Features

## Real‑time Telemetry  
- Live data from the official F1 game UDP stream
- Explicit, version‑specific packet parsing (F1 23 → F1 25)

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
   git clone https://github.com/AndroidGameDevelopment/F1RaceHUD.git
   cd F1RaceHUD
3. Open Android Studio and select File → Open…, then choose the cloned project folder.
4. Allow Android Studio to download and sync all Gradle dependencies.
5. After the sync completes, select a device or emulator.
6. Click Run to build and launch the app using the default debug configuration.

# 📦 Installation

### From Google Play (recommended)
Download the latest version of F1 Race HUB directly from the [Google Play Store](https://play.google.com/store/apps/details?id=com.codaers.f1racehub).
    
