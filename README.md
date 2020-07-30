# Willkommen zu AUI-K

[![License](https://img.shields.io/badge/License-LGPL%20v2.1-blue.svg)][license]
[![Build](https://github.com/stadt-bielefeld/auik/workflows/Java%20CI/badge.svg)](https://github.com/stadt-bielefeld/auik/actions)
[![GitHub release (latest by date including pre-releases)](https://img.shields.io/github/v/release/stadt-bielefeld/auik?include_prereleases)](https://github.com/stadt-bielefeld/auik/releases)
[![Gitpod ready-to-code](https://img.shields.io/badge/Gitpod-ready--to--code-blue?logo=gitpod)](https://gitpod.io/#https://github.com/stadt-bielefeld/auik)

[license]:  https://tldrlegal.com/license/gnu-lesser-general-public-license-v2.1-(lgpl-2.1)

- [Willkommen zu AUI-K](#willkommen-zu-aui-k)
  - [Das freie Anlagen- und Indirekteinleiterkataster](#das-freie-anlagen--und-indirekteinleiterkataster)
  - [AUIK bauen](#auik-bauen)
  - [Einen Release erstellen](#einen-release-erstellen)
  - [Weitere Dokumentation](#weitere-dokumentation)

## Das freie Anlagen- und Indirekteinleiterkataster

Das freie Anlagen- und Indirekteinleiterkataster (AUI-K) erfasst
standortbezogene Informationen zu Anlagen zum Umgang mit wassergefährdenden
Stoffen (VAwS) und Indirekteinleitern. Durch die gemeinsame Datenbasis mit
geografischen Daten können Sachdaten mit den erfassten Informationen
verschnitten werden. Darüber hinaus können automatisch Einträge in Datensätze
aufgrund ihrer räumlichen Zugehörigkeit zu beispielsweise
Entwässerungsgebieten, Wasserschutzgebieten etc. generiert werden.

## AUIK bauen

```shell
git clone https://github.com/stadt-bielefeld/auik
cd auik
mvn validate
mvn -B package --file pom.xml
```

## Einen Release erstellen

```shell
git tag v1.0.0
git push --tags
```

Das AUIK JAVA Archive (`auik-v1.0.0.jar`) und die SHA-256 Prüfsumme
(`auik-v1.0.0.jar.sha256sum`) wird dabei automatisch an den Release gehängt.
Die Quelltexte werden nach dem Veröffentlichen des Release beigefügt.

Anschließend den Release auf der Seite
<https://github.com/stadt-bielefeld/auik/releases> auswählen, bearbeiten
(`Edit`) und veröffentlichen (`Publish release`). Hinweis: Hierzu auf GitHub
anmelden!

## Weitere Dokumentation

Weitere Dokumentation ist zu finden unter <https://stadt-bielefeld.github.io/auik/>.

Und in dem Verzeichnis [doc](/doc).
