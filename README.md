<img width="1080" height="1240" alt="Screenshot_20261009_095314" src="https://github.com/user-attachments/assets/cb2ef41b-323a-49f3-b0ea-d4cb83db7700" />
<img width="1080" height="1240" alt="Screenshot_20261009_095237" src="https://github.com/user-attachments/assets/a4b5d564-4158-40e0-9c6e-bb064282425c" />
<img width="1080" height="1240" alt="Screenshot_20261009_095224" src="https://github.com/user-attachments/assets/f405f835-878f-43ba-8b67-66d310cd942c" />
<img width="1080" height="1240" alt="Screenshot_20261009_095210" src="https://github.com/user-attachments/assets/b0af383f-1c90-42d2-bf6d-5ca3da800879" />



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
