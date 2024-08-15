# Willkommen zu AUI-K

[![License](https://img.shields.io/badge/License-LGPL%20v2.1-blue.svg)][license]
![Build](https://github.com/stadt-bielefeld/auik/workflows/Java%20CI/badge.svg)
![GitHub release (latest by date including pre-releases)](https://img.shields.io/github/v/release/stadt-bielefeld/auik?include_prereleases)

[license]:  https://tldrlegal.com/license/gnu-lesser-general-public-license-v2.1-(lgpl-2.1)
[sonarcube]: https://sonarcloud.io/dashboard?id=auik_prod%3Aauik_prod

## Das freie Anlagen- und Indirekteinleiterkataster

Das freie Anlagen- und Indirekteinleiterkataster (AUI-K) erfasst
standortbezogene Informationen zu Anlagen zum Umgang mit wassergefährdenden
Stoffen (AwsV) und Indirekteinleitern. Durch die gemeinsame Datenbasis mit
geografischen Daten können Sachdaten mit den erfassten Informationen
verschnitten werden. Darüber hinaus können automatisch Einträge in Datensätze
aufgrund ihrer räumlichen Zugehörigkeit zu beispielsweise
Entwässerungsgebieten, Wasserschutzgebieten etc. generiert werden.

## Entwicklungsumgebung

### Aufsetzen der Datenbank

Für das Aufsetzen einer Entwicklungsumgebung wird zuerst eine PostgreSQL-Datenbank benötigt.
Diese kann über das vorhandene Dockerfile erstellt werden:

```bash
docker build -t auik/db -f docker/Dockerfile .
docker run --name auik_db -p15432:5432 -v $PWD/data/db:/opt/auik_db -d auik/db
```

Die Datenbank ist dann unter Port `15432` mit dem Datenbanknamen `fisumwelt` erreichbar. Ein Login ist möglich als Nutzer `auikadmin` mit dem Passwort `secret`.

Alternativ kann die Datenbank über die Skripte in `data/db` manuell erstellt werden.

```bash
cd data/db
psql --command "CREATE DATABASE fisumwelt;"
psql -d fisumwelt --command "CREATE EXTENSION postgis;"
psql -d fisumwelt --command "CREATE USER auikadmin with password 'secret';"
psql -d fisumwelt -f version1_0_schema.sql
psql -d fisumwelt -f updateTo_1_1_0.sql
```

Optional können Beispiel-Daten [importiert](#datenimport) werden.
Im Docker-Setup erfolgt das automatisch.

### Konfigurieren der Anwendung

Vor dem Starten der Anwendung muss in der `auik.properties` der Eintrag `auik.system.dburl` gesetzt werden um die Verbindung mit der Datenbank zu ermöglichen.

Beispielweise mit einer Datenbank erreichbar unter `localhost:15432` mit dem Datenbank-Namen `fisumwelt`:

```
auik.system.dburl=jdbc\:postgresql\://localhost\:15432/fisumwelt
```

### Starten der Anwendung

Für die Anwendung selbst müssen `maven` und ein passendes JDK ab Version 11 (bspw. OpenJDK 11) installiert sein.

Kompilieren und Starten der Anwendung:

```bash
mvn clean compile exec:java
```

Erstellen eines Jar-Archivs:

```bash
mvn clean package
```
Das Archiv ist im Ordner `target` zu finden.

Für die Anmeldung kann der Datenbankbenutzer verwendet werden:

`auikadmin`, Passwort: `secret`

## AUI-K Handbuch

Das AUI-K Handbuch ist als pdf-Datei unter [doc/AUI-K_Handbuch.pdf](doc/AUI-K_Handbuch.pdf) zu finden.

## Datenimport

Mithilfe des mitgelieferten [SQL-Skripts](data/db/import_csv.sql) können Adressdaten als CSV in die Datenbank importiert werden.
Die entsprechende CSV-Datei muss eine Titel-Zeile und folgende Spalten enthalten:

```csv
namezus,wirtschaftszweig,name,namebetrbeauf,vornamebetrbeauf,email,telefon,telefax,plz,ort,strasse,hausnr,hausnrzus,bemerkungen
```

Tatsächlich wird die erste Zeile der CSV-Datei ignoriert,
aber der Inhalt der Spalten muss den genannten Spalten im Datenbank-Schema
entsprechen.

Die [CSV Beispieldatei](data/db/import.csv) kann hier als Vorlage dienen.

Der Import selbst lässt sich bspw. über eine Kommandozeile auslösen:

```bash
cd data/db
psql -d fisumwelt -f import_csv.sql <import.csv
```

`fisumwelt` ist hier der Datenbankname, je nach Datenbank-Setup müssen ggf. weitere Parameter ergänzt werden.

## Anpassen von Feldbeschriftungen

Mithilfe von `.properties` Dateien können die Beschriftungen von Feldern angepasst werden. Die verfügbaren Einträge finden sich in der [auik_de_DE.properties](src/de/bielefeld/umweltamt/aui/resources/auik_de_DE.properties).
Eine Anpassung kann entweder in der genannten Datei erfolgen oder über eine separate Datei. Letztere kann dann in der `auik.properties` ohne Sprachkürzel und Dateiendung angegeben werden.

Bspw. wäre der Eintrag in der `auik.properties` für eine Datei `exampleFile_de_DE.properties`:

```properties
auik.i18n_file=exampleFile
```
