<img width="1080" height="1240" alt="Screenshot_20261009_095210" src="https://github.com/user-attachments/assets/ff4e5ce6-7a8b-4936-b0b1-ca356e704337" />




# Fahrplan

Eine minimalistische Fahrplan-App für das Light Phone 3.

## Funktionen

- Verbindungssuche zwischen zwei Bahnhöfen mit Echtzeit-Verspätungen, Gleisen und Gleiswechseln
- Suche nach Abfahrts- oder Ankunftszeit, mit eigener 24-Stunden-Zeitauswahl
- Schalter „DE-Ticket“, der nur Verbindungen im Nahverkehr zeigt
- Verbindungen speichern und später mit aktuellen Verspätungen neu laden
- Häufige Strecken merken und mit einem Tipp suchen

## Installation

**Fertige APK:** Unter *Releases* die neueste `Fahrplan.apk` herunterladen und auf dem Gerät installieren, zum Beispiel per USB:

```
adb install app-debug.apk
```


## Aufbau

Die Oberfläche ist eine einzelne HTML-Datei (`app/src/main/assets/index.html`), die in einer WebView läuft. Die Fahrplandaten holt der Kotlin-Teil (`NativeBridge.kt`) und gibt sie an die Seite weiter. Das ist nötig, weil der Browser direkte Anfragen an die Bahn-Server blockieren würde.

```
app/src/main/
├── assets/index.html        Oberfläche und App-Logik
├── java/.../MainActivity.kt WebView, Zurück-Taste, Systemleisten
├── java/.../NativeBridge.kt Netzwerkanfragen für die WebView
└── AndroidManifest.xml      Internet-Berechtigung
```

Gespeicherte Verbindungen und Strecken liegen nur lokal auf dem Gerät.

## Datenquellen

- Bahnhofssuche: Schnittstelle von bahn.de
- Verbindungssuche: Schnittstelle der DB-Navigator-App

Beide Schnittstellen sind nicht offiziell dokumentiert. Der Aufbau der Anfragen orientiert sich am Open-Source-Projekt [db-vendo-client](https://github.com/public-transport/db-vendo-client). Wenn die Bahn ihre Schnittstellen ändert, kann die App ohne Vorwarnung aufhören zu funktionieren.

## Hinweis

Dies ist ein privates Hobbyprojekt ohne Verbindung zur Deutschen Bahn AG oder zu Light. Alle Angaben ohne Gewähr. Für Tickets und verbindliche Auskünfte bitte die offiziellen Angebote der Bahn nutzen.
