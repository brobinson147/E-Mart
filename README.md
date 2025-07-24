# Emart

![Project Badge](https://img.shields.io/badge/status-active-brightgreen) ![License](https://img.shields.io/badge/license-MIT-blue)

---

## Table of Contents

* [Overview](#overview)
* [Features](#features)
* [Screenshots](#screenshots)
* [Prerequisites](#prerequisites)
* [Usage](#usage)
* [Project Structure](#project-structure)
* [Available Scripts](#available-scripts)
* [License](#license)
* [Contact](#contact)

---

## Overview

Emart is a modern Android application that provides a seamless shopping experience for users to browse, search, and purchase products.
Built with Kotlin and Gradle, Emart features a clean architecture and modular codebase, making it easy to maintain and extend.

**Key goals:**

* Fast, intuitive UI
* Scalable code structure
* Easy integration with backend services

---

## Features

* **Product Catalog**: Browse products organized by category
* **Search & Filters**: Find items quickly with real-time search and filtering
* **Shopping Cart**: Add, remove, and update product quantities
* **User Authentication**: Secure sign-up, login, and profile management
* **Order History**: View past orders and reorder with one tap
* **Offline Support**: Cached data for uninterrupted browsing

---

## Screenshots

<p align="center">
  <img src="docs/screenshots/home.png" alt="Home Screen" width="200" />
  <img src="docs/screenshots/product.png" alt="Product Detail" width="200" />
  <img src="docs/screenshots/cart.png" alt="Shopping Cart" width="200" />
</p>

---

### Prerequisites

* [Android Studio](https://developer.android.com/studio) (Arctic Fox or later)
* Java Development Kit (JDK) 11+
* Internet connection (for dependencies)


## Usage

* Navigate through categories from the home screen.
* Tap a product to view details and add it to your cart.
* Access the cart icon to review items and proceed to checkout.
* Sign up or log in via the profile tab to view order history.

---

## Project Structure

```
Emart/
├── app/                # Android app module
│   ├── src/main/
│   │   ├── java/com/emart  # Kotlin source files
│   │   └── res/             # Layouts, drawables, strings
│   └── build.gradle        # Module-level Gradle config
├── gradle/             # Gradle wrapper files
├── .gitignore          # Files to ignore in Git
├── build.gradle        # Project-level Gradle config
├── gradlew, gradlew.bat# Wrapper executables
├── gradle.properties   # Global Gradle settings
└── settings.gradle     # Repository settings
```

---

## Available Scripts

In the project directory, you can run:

| Command                     | Description              |
| --------------------------- | ------------------------ |
| `./gradlew clean`           | Remove build directories |
| `./gradlew assembleDebug`   | Compile debug APK        |
| `./gradlew assembleRelease` | Compile release APK      |
| `./gradlew test`            | Run unit tests           |

---


## License

This project is licensed under the MIT License. See the [LICENSE](LICENSE) file for details.

---

## Contact

**Ben Robinson** – [@my Linkedin](https://www.linkedin.com/in/ben-robinson-211470194/) – [@my Email](benjrobinson1998@gmail.com)
