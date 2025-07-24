# Emart

![Status](https://img.shields.io/badge/status-active-brightgreen)
![License](https://img.shields.io/badge/license-MIT-blue)

---

## Table of Contents

* [Overview](#overview)
* [Features](#features)
* [Screenshots](#screenshots)
* [Getting Started](#getting-started)

  * [Prerequisites](#prerequisites)
  * [Installation](#installation)
* [Usage](#usage)
* [Project Structure](#project-structure)
* [Available Scripts](#available-scripts)
* [Contributing](#contributing)
* [License](#license)
* [Contact](#contact)

---

## Overview

Emart is a modern Android application delivering a seamless shopping experience. Users can browse, search, and purchase products with a clean, performant interface. Built with Kotlin and Gradle, Emart follows best practices in modularization and clean architecture.

**Key Goals:**

* Fast, intuitive UI
* Scalable, maintainable codebase
* Easy integration with backend APIs

---

## Features

* **Product Catalog**: Browse items grouped by category
* **Search & Filters**: Real-time search and dynamic filters
* **Shopping Cart**: Add, remove, and update quantities
* **Authentication**: Secure sign-up, login, and profile management
* **Order History**: View past orders and reorder with one tap
* **Offline Mode**: Cached data ensures uninterrupted browsing

---

## Screenshots

> *Coming soon!* Place your screenshots in `docs/screenshots/` and update the paths below.

<p align="center">
  <img src="docs/screenshots/home.png" alt="Home Screen" width="200" />
  <img src="docs/screenshots/cart.png" alt="Shopping Cart" width="200" />
</p>

---

## Getting Started

Follow these steps to get a local copy up and running.

### Prerequisites

* [Android Studio](https://developer.android.com/studio) (Arctic Fox or later)
* Java Development Kit (JDK) 11 or higher
* Internet connection for downloading dependencies

### Installation

1. **Clone the repository**

   ```bash
   git clone https://github.com/<your-username>/Emart.git
   cd Emart
   ```
2. **Open in Android Studio**

   * Select **File → Open...** and choose the project directory.
3. **Build the app**

   * Click **Run** (Shift+F10) to compile and install on your emulator or device.

---

## Usage

* Launch the app to view the home screen with featured categories.
* Tap a product to view details and add it to your cart.
* Access your cart via the top-right icon to review and checkout.
* Navigate to the Profile tab to sign up, log in, or view order history.

---

## Project Structure

```
Emart/
├── app/                    # Android app module
│   ├── src/main/
│   │   ├── java/           # Kotlin source files (com.emart)
│   │   └── res/            # Layouts, drawables, strings
│   └── build.gradle        # Module-level Gradle config
├── docs/                   # Documentation and assets
│   └── screenshots/        # App screenshots
├── gradle/                 # Gradle wrapper
├── .gitignore              # Git ignore rules
├── build.gradle            # Project-level Gradle config
├── gradlew, gradlew.bat    # Gradle wrapper executables
├── gradle.properties       # Global Gradle settings
└── settings.gradle         # Repo settings
```

---

## Available Scripts

Run these commands from the project root:

| Command                     | Description              |
| --------------------------- | ------------------------ |
| `./gradlew clean`           | Remove build directories |
| `./gradlew assembleDebug`   | Compile debug APK        |
| `./gradlew assembleRelease` | Compile release APK      |
| `./gradlew test`            | Execute unit tests       |

---

## Contributing

Contributions are welcome! Please follow these steps:

1. Fork the repository
2. Create a branch: `git checkout -b feature/YourFeature`
3. Make your changes and commit: `git commit -m 'Add feature'`
4. Push to your branch: `git push origin feature/YourFeature`
5. Open a pull request describing your changes

See [CONTRIBUTING.md](CONTRIBUTING.md) for detailed guidelines.

---

## License

This project is licensed under the MIT License. See the [LICENSE](LICENSE) file for details.

---

## Contact

**Ben Robinson**

* LinkedIn: [ben-robinson-211470194](https://www.linkedin.com/in/ben-robinson-211470194/)
* Email: [benjrobinson1998@gmail.com](mailto:benjrobinson1998@gmail.com)

Project Link: [https://github.com/](https://github.com/)<brobinson147>/Emart
