# DiarySnap (Tagebuch-Schnappschuss)

DiarySnap ist eine einfache Tagebuch-App, bei der Einträge mit einem passenden Bild von **Unsplash** ergänzt werden können.  
Das Projekt wurde als Abschlussprojekt umgesetzt und erfüllt die geforderten Kriterien: **MVVM**, **Repository Pattern**, **Navigation (5 Screens)**, **API Call (Retrofit)**, **Datenspeicherung (Room/Firebase)** sowie **Fehlerhandling**.

---

## Features

- ✅ Login / Logout (Firebase Auth oder Alternative)
- ✅ Tagebuch-Einträge erstellen, anzeigen, löschen
- ✅ Unsplash API: Bild passend zu Mood/Keyword laden
- ✅ Lokale Datenspeicherung (Room) für Einträge (Offline-fähig)
- ✅ Navigation mit 5 Screens
- ✅ Fehler abfangen und im UI anzeigen (Loading/Error States)
- ✅ Components ausgelagert (wiederverwendbare Composables)

---

## Screens (Navigation)

1. **LoginScreen**  
   Anmeldung / Registrierung, Fehleranzeige

2. **HomeScreen (Feed)**  
   Liste aller Einträge (LazyColumn), Navigation zu Details

3. **CreateEntryScreen**  
   Eintrag anlegen (Titel, Text, Mood/Keyword) + Bild per Unsplash laden + Speichern

4. **EntryDetailScreen**  
   Detailansicht eines Eintrags inkl. Bild, Datum, Text, Delete

5. **Profile/SettingsScreen**  
   Profil/Account, Logout (optional: Theme Toggle)

---

## Tech Stack

- **Kotlin**
- **Jetpack Compose**
- **MVVM Architecture**
- **Repository Pattern**
- **Room** (lokale Datenbank)
- **Retrofit** (HTTP Client für Unsplash API)
- **Coroutines + Flow**
- Optional: **Firebase Auth** (Login)

---

## Architektur (MVVM + Repository)

**UI Layer**
- `ui/screens/` – Composable Screens
- `ui/components/` – wiederverwendbare UI-Bausteine (z. B. EntryCard, Loading, ErrorText)
- `ui/navigation/` – NavGraph / Routes

**Presentation Layer**
- `viewmodel/` – ViewModels pro Screen
- UI State: `Loading / Success / Error`

**Data Layer**
- `data/local/` – Room: Entity, DAO, Database
- `data/remote/` – Retrofit: API Interface, DTOs
- `data/repository/` – Implementierung der Repositories

**Domain Layer (optional)**
- `domain/model/` – Domain Modelle
- `domain/repository/` – Repository Interfaces

---

## Datenspeicherung

### Room (lokal)
Gespeichert werden z. B.:
- `id`
- `title`
- `text`
- `mood`
- `createdAt`
- `imageUrl` (+ optional Credits)

---

