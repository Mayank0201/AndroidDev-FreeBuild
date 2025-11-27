# 📱 Android Free Build Course Projects

This repository contains all Android Studio projects that I built independently as part of the **Android Developers Course** and through **self-practice**.  
Each project folder includes source code and the **APK file** inside the `apk/` directory.

---

## 🚀 Projects Overview

### 🍋 **Lemonade App**

A simple interactive app where users progress through lemonade-making steps by tapping.

- Demonstrates **basic Jetpack Compose state management**
- Uses **state hoisting** and basic UI interactions

---

### 🖼 **Art Space**

Image gallery-style app that displays different artworks with Next/Previous navigation buttons.

- Focused on **image rendering**, **Compose layout structure**, and **button interaction**

---

### 📅 **31 Days App**

An inspirational message app that displays a different message for each of the 31 days.

- Explores **Lists**, **Text styling**, and **UI organization using Column and Row**

---

### 🌍 **Guess the Country**

A country-guessing game where scrambled country names must be guessed correctly.

- Built using **ViewModel + State**, scoring logic, and UI updates
- Includes **end screen**, **hint usage**, and **random string scrambler**

---

### 📚 **Bookshelf App**

A book listing app that displays book titles (from API or mock dataset depending on setup).

- Demonstrates **network calls with Retrofit (optional)**, **LazyColumn rendering**, and **loading states**

---

### 📝 **Report Maker**

A multi-screen report card creation app where users enter subject marks and view a formatted report.

- Uses **Navigation in Jetpack Compose**
- Multi-screen input handling + validation
- Example of **state passing between composables**

---

### ✈ **Flight Search App**

A mini-flight search app featuring airports, search functionality, and favorite route selection.

- Built with **Room Database**, **DAO queries**, **Flow**, and **lazy list jetpack compose UI**
- Uses **join queries** for combining Favorite + Airport tables
- ✨ Key concepts covered:
  - **Room database preloading** using `createFromAsset()` for initial airport data
  - **Favorite routes persistence**
  - **Database Migrations** (to keep user favorites safe instead of resetting DB)
  - `addMigrations` usage instead of `fallbackToDestructiveMigration()` for production safety

---

### 🗒 **Notes App**

A CRUD-based notes manager app using Jetpack Compose and Room.

- Features **add, delete, edit**, and **expand note card**
- Persistent stored data using **Room + Flow + MVVM architecture**
- Uses **Card expansion UI** and clean architecture separation

---

## 📦 APK Downloads

All APK files are available in each project folder under the **`apk/` directory**.

---

## 🧰 Tech Stack & Concepts Used

| Concept                   | Projects                                              |
| ------------------------- | ----------------------------------------------------- |
| Jetpack Compose           | All                                                   |
| MVVM + State              | Notes, Guess the Country, Flight Search, Report Maker |
| Room Database             | Notes, Flight Search                                  |
| Navigation                | Report Maker                                          |
| Coroutines & Flow         | Flight Search, Notes                                  |
| Pre-Loaded DB + Migration | Flight Search                                         |
| UI Lists / LazyColumn     | Multiple Projects                                     |

---

## 🎯 Purpose

These projects helped strengthen practical knowledge of **Compose UI, Room persistence, Navigation, MVVM, Coroutines, and Kotlin problem-solving**, moving from beginner to intermediate Android development.

---

## 📌 Future Improvements

- Add screenshots preview section
- Add UI animations
- Firebase-enabled version for Notes app
- Some basic XML Projects to accompany these

---
