![unip](https://i.loli.net/2021/04/14/o4Lcn5jEw71GT9f.png)

# ***Softwarearchitektur***





# Inhaltsverzeichnis

- [Einführung und Ziele](#1-einführung-und-ziele)
  - [Aufgabenstellung]()
  - [Qualitätsziele]()
  - [Stakeholder]()
- [Randbedingungen](#hauptteil)
  - [Technische Randbedingungen]()
  - [Organisatorische Randbedingungen]()
  - [Konventionen Randbedingungen]()
- [Kontextabgrenzung](#thema1)
  - [Fachlicher Kontext]()
  - [Technischer Kontext]()
- [Lösungsstrategie](#schluss)
- [Bausteinsicht]()
  - [Übersichtsdiagramm]()
  - [Begründung]()
  - [Enthaltene Bausteine]()
    - [Ebene 1]()
      - [Blackbox "View"]()
      - [Blackbox "Controller"]()
      - [Blackbox "Model"]()
    - [Ebene 2]()
      - [Whitebox "View"]()
      - [Blackbox "Navigation"]()
      - [Blackbox "Spezifische GUI"]()
      - [Whitebox "Model"]()
      - [Blackbox "Datenmanager"]()
      - [Blackbox "Speicherklassen"]()
      - [Blackbox "Spezifischer Controller"]()
      - [Blackbox "Listener"]()
      - [Blackbox "Verwaltung"]()
  - [Wichtige Schnittstellen]()
- [Verteilungssicht]()
- [Typische Muster, Strukturen und Abläufe]()
  - [Typische statische Strukturen]()
  - [Typischer Ablauf]()
- [Technische Konzepte]()
  - [Persistenz]()
  - [Benutzungsoberfläche]()
  - [Ablauf Dialog oder Workflow Steuerung]()
- [Entwurfsentscheidungen]()
- [Abbildungsverzeichnis]()
- [Glossar]()



# 1. Einführung und Ziele 

## 1.1 Aufgabenstellung

**Was ist UniP und welche wesentlichen Features sind vorhanden?**

 

Mit Uni-Planer wurde ein Programm für Studenten realisiert, welches einige Features besitzt, um das Studium und die zu absolvierenden Module zu strukturieren und zu planen. Die Kalendererstellung, mit Monats- und Wochenansicht ermöglicht es verschiedene Arten von Termine einzustellen. UniP bietet eine Funktion an, um den Überblick über die noch zu erledigenden Testate zu haben und die Abgaben mit einem Timer zu versehen. Studenten können UniP zur Erstellung des Stundenplans nutzen und so ihre Stundenpläne auch mit dem Kalender verknüpfen. 

Zudem besitzt das Programm eine Semester/-Modulübersicht und essenzielle Informationen, wie zum Beispiel, um welche Art von Prüfung und Testate es sich handelt, können zu den Modulen eingesehen werden. 

Im Kalender werden persönliche Termine, Testate, Fristen und Module angezeigt. Dies dient zur Erkennung von Überschreitungen und ebenfalls für einen besseren Überblick. So werden Informationen zu den Modulen angezeigt, die im jeweiligen Semester zu absolvieren sind. Zusätzlich besteht die Möglichkeit die Module in andere Semester zu verschieben, wenn sie erst später absolviert werden sollen oder bei nicht-bestehen. Dies verstärkt die Funktion einen besseren Überblick über den Studienverlauf zu haben und das Studium besser zu strukturieren und zu planen. 

Die Noten, bzw. ob bestanden/ nicht bestanden wurde, sind ebenfalls einzusehen. Die Note der Notenübersicht ist durch manuelle Eingabe möglich. 

Mit UniP können To-Do Listen selbsterklärend erstellt werden und die To-Do-Listen werden genutzt um Aufgaben festzulegen, die bis zu einem bestimmten Datum erledigt sein müssen. Wenn die Aufgaben erledigt sind, werden sie abgehackt und aus der Liste entfernt. Dies erleichtert einen Überblick über noch alle zu erledigenden Aufgaben zu erhalten. 

 

## 1.2 Qualitätsziele

| Nr. /  Priorität | Qualitätsziel                               | Kurzbeschreibung/Verweis                                     |
| ---------------- | ------------------------------------------- | ------------------------------------------------------------ |
| //QZ01           | Übersichtlichkeit                           | Die  Übersichtlichkeit spielt eine große Rolle. Die verschiedenen Reiter sollten  alle einfach gehalten sein und nur das notwendigste beinhalten.  Um einen  guten Überblick zu gewährleisten ist der Aufbau von allen Reitern ähnlich  gehalten. Die Navigation erfolgt über eine Reiter-Zeile welche auf allen  Seiten gleich angezeigt wird.  Um es  übersichtlich zu halten, wird darauf geachtet, dass Buttons oder Fenster zum  Anhaken klar abgetrennt an der Seite oder unterhalb der Funktionen stehen. |
| //QZ02           | Benutzbarkeit                               | Das  Programm sollte minimalistisch gehalten werden, sodass die Benutzung der  verschiedenen Funktionen intuitiv erfolgen kann und somit kein Tutorial o.Ä.  notwendig ist. Hinzu kommt das einige Aufgabenbereiche dem Benutzer schon  anderweitig bekannt sind, beispielsweise der Kalender.  Die  Funktion von Buttons (z.B. “Löschen”) sollten auch ohne Erklärung  verständlich sein. Für unklare Funktionen wird eine kurze Erklärung durch ein  Fragezeichen-Symbol direkt dahinter angeboten.  Bei der  Eingabe falscher Daten wird das System entsprechende Hinweistexte ausgeben. |
| //QZ03           | Zuverlässigkeit                             | Das  System implementiert eine grundlegende Fehlervermeidung welche z.B. eine  Eingabe von Buchstaben im Noten-Feld. Es wird dem Nutzer z.B. ebenfalls nicht  erlaubt, einen leeren Termin- sowie Moduleintrag hinzuzufügen.   Da die  Zuverlässigkeit in dem Programm keine allzu große Rolle spielt, wird diese  als “normal” eingestuft. |
| //QZ04           | Jederzeit  vom Studenten an  (Änderbarkeit) | Änderungen  am Programm und dessen Standard-Werten sollten ohne großen Aufwand möglich  sein, damit im Falle eines PO-Wechsels die Standard-Module angepasst werden  können.  Die  anderen Funktionen werden in Zukunft mit sehr geringer Wahrscheinlichkeit  verändert werden müssen. |
| //QZ05           | Übertragbarkeit                             | Die  Übertragbarkeit des Systems ist irrelevant, da die Benutzung nur offline zur  Verfügung steht und nicht auf einer großen Palette von Endgeräten  funktionieren muss. |
| //QZ06           | Effizienz                                   | Funktionen  wie das Einblenden des Stundenplans im Kalender sollte innerhalb weniger  Millisekunden erfolgen. Dies gilt ebenfalls für das Eintragen von Terminen im  Kalender, der Wechsel von der Monats- zur Wochenansicht, das Einfügen eines  Moduls oder das Löschen der To-Do Punkte, usw.   Dies sind  alles Funktionen, die sehr schnell erfolgen müssen, um die  Benutzerfreundlichkeit zu erhöhen. Durch das Arbeiten mit einer größtenteils  geringen Datenmenge kann die Effizienz trotzdem mit normal bis gut für /LF20/  eingestuft werden. |

## 1.3 Stakeholder

| Name/Rolle                                                | Ziel/Berührungspunkt                                         | Notwendige  Beteiligung                                      |
| --------------------------------------------------------- | ------------------------------------------------------------ | ------------------------------------------------------------ |
| MI  Student 1-2 Semester  MI  Student 3-6 Semester MP/WMA | **Allgemeine  Ziele:**  <br />- Bietet einfache Möglichkeit um die Zeit zu planen <br />- Verschafft Überblick über das gesamte Semester<br />- Möglich eigene Termine eintragen<br />**Spezifische  Ziele (Student 1-2)**   <br />- Möglichkeit für Abgaben eine Priorität zu setzen<br />- Hilfe im Gestalten eines persönlichen Lernplans<br />**Spezifische  Ziele (Student 3-6)**<br />- Möglichkeit Wahlpflichtmodule zu Planen<br />- Prüft, ob die CrP reichen, Wahlpflichtmodule zu belegen | Stakeholder  möchte die Anwendung nutzen, um besser in der Uni mitzumachen. Konzentration  spielt hierbei eine sehr große Rolle. |

 

# 2. Randbedingungen

## 2.1 Technische Randbedingungen

| ID       | Randbedingung                  | Erläuterungen,  Hintergrund                                  |
| -------- | ------------------------------ | ------------------------------------------------------------ |
| //TECH01 | Minimale  Hardware Ausstattung | Es  reicht ein üblicher Standard-Laptop, um sich den Uni Planner anzuschauen und  zu bearbeiten. Oder einen Computer mit Hardware wie Maus und Tastatur. |
| //TECH02 | Implementierung  in Java       | Die  Software wird hauptsächlich auf dem Computer ausgeführt, hierbei ist die  Entwicklung auf der Java Engine am einfachsten. |
| //TECH03 | Betriebssystem  (Windows)      | Um von  überall auf seine Daten gelangen zu können, ist notwendig auf Windows  Betriebssystem zugreifen zu können. |
| //TECH04 | Zugriff                        | Der  Zugriff sollte hauptsächlich von den Medieninformatik Studenten der THM  möglich sein. Ist nur auf einem Computer zugreifbar. |

## 2.2 Organisatorische Randbedingungen

| ID      | Randbedingung              | Erläuterungen,  Hintergrund                                  |
| ------- | -------------------------- | ------------------------------------------------------------ |
| //ORG01 | Zeitplan                   | Die  Entwicklung beginnt ab dem 05. März 2021. Der erste Prototyp sollte Mitte  März fertig sein. Fertigstellung bis April. |
| //ORG02 | Vorgehensmodell            | Die  arc42-Dokumentation dient als Template für die Architektur und ist die  Vorlage für das Projekt. In diesem Zeitraum müssen Zwischenstände im  Entwicklungsteam abgesprochen werden. |
| //ORG03 | Nutzen                     | Termine,  Stundenpläne, To-do-Liste und der Notenübersicht sind manuell eintragbar |
| //ORG04 | Source  Code               | Der  Source Code wird für das Team in Github veröffentlicht. |
| //ORG05 | Entwicklungswerkzeuge      | Die  Wireframes werden auf draw.io skiziert. Für die Entwicklung wird in Eclipse  gearbeitet. |
| //ORG06 | Projekt-  und Teamstruktur | Das  ganze Projekt wird über die Online-Anwendung “Discord” organisiert und  geleitet. |

## 2.3 Konventionen Randbedingungen

| ID       | Randbedingung              | Erläuterungen,  Hintergrund                                  |
| -------- | -------------------------- | ------------------------------------------------------------ |
| //KONV01 | Architektur  Dokumentation | Gliederung  mithilfe des arc42-Templates und dem Buch “Software-Architektur kompakt” von  Gernot Starke und Peter Hruschka |
| //KONV02 | Java  Code Richtlinien     | Die Java  Coding Conventions werden eingehalten.             |

# 3. Kontextabgrenzung

## 3.1Fachlicher Kontext

![img](https://i.loli.net/2021/04/14/5VuBZCfk3Qa9eOM.png)

​             Abbildung 1: Fachlicher Kontext

 

| Schnittstelle/  Nachbarsysteme | Ausgetauschte  Daten   (Datenformate,  Medien) | Technologie/Protokoll                                      |
| ------------------------------ | ---------------------------------------------- | ---------------------------------------------------------- |
| Student  (Benutzer)            | Datenformate:     JSON                         | manuelle  Eingabe mittels Entnehmen von Modulinformationen |

 

**Student (Benutzer)**

Benutzt wird die Software ausschließlich von MI Bachelor Studenten nach der PO aus dem Jahr 2018. Dabei unterscheidet die Software nicht zwischen Erstsemester und weiteren Studenten, ist jedoch so aufgebaut, um sich den Bedürfnissen aller Zielgruppen anpassen zu können.

Die Daten wie Moodle, Online Dienste und Webmail werden vom Student direkt in den Uni-Planer eingetragen

 

## 3.2 Technischer Kontext

 

![img](https://i.loli.net/2021/04/14/BOvt6ucoqdAK4y3.png)

Abbildung 2: Technische Kontextabgrenzung

 

# 4. Lösungsstrategie 

 

Das Programm wird in Java implementiert, damit die Studenten jederzeit von Windows auf das System zugreifen können. damit die Studenten jederzeit auf dem Windows System zugreifen können.

UniP wird mit einer grafischen und strukturierten Benutzeroberfläche (GUI) bedient. Hierfür wird das Framework „JavaFX“ benutzt.

Mittels CSS wird das sonst standardisierte Design verschönert und Benutzerfreundlich gemacht. Auch der SceneBuilder hilft hierbei und der Benutzer hat ein deutlich besseres Nutzererlebnis. Zusätzlich wird das Framework Bootstrap verwendet um das Design ebenfalls zu verschönern. Dieses Framework besitzt vorgegebene Styles für zum Beispiel einen Button.

Es wird keine Übertragbarkeit benötigt, da die Daten offline zu Verfügung stehen. Der Student muss hauptsächlich die Daten eigenständig von den jeweiligen Plattformen einfügen.

Der filebasierte Storage ist auf Basis von JSON. Es werden Schnittstellen benötigt, damit die Daten sich jederzeit austauschen können. Hierbei hilft die Blackbox “Datenmanager”, damit diese jede Datenabfrage und Datenänderung abarbeiten kann. GSON wird verwendet, damit Java Objekte in JSON Objekte umgewandelt werden können und andersrum. 

Um das Programm effizient zu halten wird mit Hilfe der Blackbox “Speicherklassen” schnelle Aktionen durchgeführt, die das Nutzererlebnis ebenso fordern.

 

# 5. Bausteinsicht

## 5.1 Übersichtsdiagramm

 

![img](https://i.loli.net/2021/04/14/SmHPOvu5CwIGlY7.jpg)

Abbildung 3: Übersichtsdiagramm



## 5.2 Begründung 

Für die Uni-Planer Anwendung wurde zur Einhaltung der Funktions- und Randbedingungen ein Reiter Layout gewählt. Das Layout besteht dabei immer aus einer für alle Reiter gleich bleibende Navigationsbar und der GUI des gewählten Reiters. Um das Wechseln des Layouts zwischen den Reitern so einfach und übersichtlich wie möglich zu halten wurde eine Spezialisierung des “Model-View-Controller” Prinzips gewählt. Ein Controller (s. 5.1.2) bildet die Schnittstelle zwischen View (s. 5.1.1) und Model (s. 5.1.3). Dieser Aufbau ermöglicht es die Daten von der angezeigten GUI abzukoppeln. Beim Wechseln in eine andere spezifische GUI (s.5.2.1.2) wechselt auch der spezifische Controller (s.5.2.2.1). Dadurch ist der GUI und Verwaltungs-Code (s.5.3.1.2) der Anwendung klar strukturiert und das Management der GUI Listener (s.5.3.1.1) ist einfacher da jeder Controller nur für genau eine GUI Oberfläche zugeschnitten ist. Um den Zugriff auf die benötigten Daten zu vereinfachen werden diese im Datenmanager (s. 5.2.3.1) gebündelt und dann auch dort angefragt. Um eine Trennung der User-Inputs und der eigentlichen Bearbeitung der Anfragen zu gewährleisten wird eine funktionale Trennung im Controller vorgenommen (s. 5.3.1).

 

## 5.3 Enthaltene Bausteine

### 5.3.1 Ebene 1

#### 5.3.1.1 Blackbox “View”

| **Bezeichnung**             | **Erklärung**                                                |
| --------------------------- | ------------------------------------------------------------ |
| Name                        | View                                                         |
| Zweck  & Verantwortlichkeit | Dieser  Baustein übernimmt das Anzeigen der grafischen Oberfläche des gesamten  Systems. |
| Schnittstelle(n)            | View  besitzt eine ein- sowie ausgehende Schnittstelle zum Controller. |
| Abhängigkeiten              | View ist  abhängig vom Framework JavaFX.                     |
| Code-Artefakte              | Package:  unip.view  Klassen:  MainController  Stylesheets:  Navigation, KalenderGUI, ModulGUI, StundenplanGUI, ToDoGUI, NotenGUI |

#### 5.3.1.2 Blackbox “Controller”

| **Bezeichnung**             | **Erklärung**                                                |
| --------------------------- | ------------------------------------------------------------ |
| Name                        | Controller                                                   |
| Zweck  & Verantwortlichkeit | Der  Baustein stellt sicher, dass eine Kommunikation zwischen View und Model  entsteht und die Inputs der View als Änderung in Model und View umgesetzt  werden. |
| Schnittstelle(n)            | Der  Baustein verfügt über eine ein- sowie ausgehende Schnittstelle zu View, sowie  noch eine ausgehende zu Model.  Der  Controller bildet damit das Zwischenglied zwischen View und Model. Er  interpretiert die Eingaben des Views und nimmt dann Änderungen an View und  Model vor. |
| Code-Artefakte              | Package:  unip.controller   Klassen:  <abstract> Reiter, KalenderReiter, ModulReiter, StundenplanReiter,  ToDoReiter, NotenReiter |
| Variabilität                | Bietet  durch die abstrakte Controller Klasse “Reiter” die Möglichkeit einfach  weitere Controller für neu eingefügte Reiter zu erstellen. |

 

#### 5.3.1.3 Blackbox “Model”

| **Bezeichnung**             | **Erklärung**                                                |
| --------------------------- | ------------------------------------------------------------ |
| Name                        | Model                                                        |
| Zweck  & Verantwortlichkeit | Dieser  Baustein pflegt und verwaltet eigenständig alle Daten. Er ist die “Datenbank”  des Systems. |
| Schnittstelle(n)            | Der  Baustein verfügt über eine eingehende Schnittstelle vom Controller.   Er  übernimmt Änderung die von Controller angefragt werden und gibt angeforderte  Daten zurück. |
| Code-Artefakte              | Package:  unip.model  Klassen:  Datenmanager, Termin, Modul, Stundenplaneintrag, ToDoEintrag  Dateien:  JSON Speicherdateien |
| Variabilität                | Durch  die Bündelung der Daten im Datenmanager und die Speicherung in einzelnen  Dateien und Speicherklassen ist der Ausbau der Entitätstypen ohne großen  Aufwand möglich. |



### 5.3.2 Ebene 2

#### 5.3.2.1 Whitebox “View”

 

| **Bezeichnung**                      | **Erklärung**                                                |
| ------------------------------------ | ------------------------------------------------------------ |
| Übersichtsdiagramm                   | ![img](https://i.loli.net/2021/04/14/dvGyZlCJkVPRImE.jpg)    |
| Lokale  Bausteine                    | Navigation   Spezifische GUI                                 |
| Lokale  Beziehung und Abhängigkeiten | Die  spezifische GUI wird indirekt von der Navigation aktiv geschaltet. Es gibt  aber keine lokale Schnittstelle. |



#### 5.3.2.2 Blackbox “Navigation”

| **Bezeichnung**             | **Erklärung**                                                |
| --------------------------- | ------------------------------------------------------------ |
| Name                        | Navigation                                                   |
| Zweck  & Verantwortlichkeit | Dieser  Baustein sorgt für die korrekte Anzeige der Navigationsbar und das auswählen  des angezeigten Reiters. |
| Schnittstelle(n)            | Navigation  besitzt eine ausgehende Schnittstelle zu den spezifischen Controllern um  diese zu aktivieren und deaktivieren. |
| Code-Artefakte              | Klassen:  MainGUI  Stylesheet:  Navigation                   |



#### 5.3.2.3 Blackbox “Spezifische GUI”

| **Bezeichnung**             | **Erklärung**                                                |
| --------------------------- | ------------------------------------------------------------ |
| Name                        | Spezifische  GUI                                             |
| Zweck  & Verantwortlichkeit | Die  spezifische GUI stellt sicher, dass das richtige Layout angezeigt wird.  Für jede  Kernfunktion gibt es jeweils eine spez. GUI. |
| Schnittstelle(n)            | S. “Wichtige  Schnittstellen”                                |
| Code-Artefakte              | Stylesheet:  KalenderGUI, ModulGUI, StundenplanGUI, ToDoGUI, NotenGUI |

 

#### 5.3.2.4 Whitebox “Model” 

| **Bezeichnung**                      | **Bedeutung**                                                |
| ------------------------------------ | ------------------------------------------------------------ |
| Übersichtsdiagramm                   | ![img](https://i.loli.net/2021/04/14/IKN7VsrGow6nCqF.jpg)    |
| Lokale  Bausteine                    | Datenmanager   Speicherklassen                               |
| Lokale  Beziehung und Abhängigkeiten | Die  Blackbox Speicherklasse enthält eine eingehende Schnittstelle zu  Datenmanager. Sie werden vom Datenmanager erstellt und gemanaged.      S.  “wichtige Schnittstellen” für spezifischer Controller und Datenmanager. |



#### 5.3.2.5 Blackbox “Datenmanager”

| **Bezeichnung**             | **Erklärung**                                                |
| --------------------------- | ------------------------------------------------------------ |
| Name                        | Datenmanager                                                 |
| Zweck  & Verantwortlichkeit | Der  Baustein verwaltet alle Speicherklassen in dafür angelegten Listen. Außerdem  kümmert er sich um die permanente Speicherung in Dateien. |
| Schnittstelle(n)            | S.  wichtige Schnittstellen                                  |
| Code-Artefakte              | Methoden:  Getter/Setter für Speicherklassen, Lesen und Schreiben Methoden für  Speicherdateien. |

 

#### 5.3.2.6 Blackbox “Speicherklassen”

| **Bezeichnung**             | **Erklärung**                                                |
| --------------------------- | ------------------------------------------------------------ |
| Name                        | Speicherklassen                                              |
| Zweck  & Verantwortlichkeit | Jede  Instanz dieses Bausteins kümmert sich um eine Instanz eines Entitätstyps. Zu  jedem Entitätstypen gibt es eine Speicherklasse in der er sich ablegen lässt. |
| Schnittstelle(n)            | Speicherklassen  werden von Datenmanager erstellt und gemanaged. |
| Code-Artefakte              | Methoden:  Getter und Setter                                 |

 

#### 5.3.2.7 Blackbox “Spezifischer Controller”

| **Bezeichnung**             | **Erklärung**                                                |
| --------------------------- | ------------------------------------------------------------ |
| Name                        | Spezifischer  Controller                                     |
| Zweck  & Verantwortlichkeit | Für  jeden Reiter bzw. Kernfunktion gibt es einen jeweiligen spezifischen  Controller. Er verarbeitet die Inputs seiner zugehörigen GUI und kümmert sich  um die Änderung und das Weiterleiten der Daten hinter seiner Kernfunktion. |
| Schnittstelle(n)            | S.  Wichtige Schnittstellen                                  |
| Code-Artefakte              | Klassen:  KalenderReiter, ModulReiter, StundenplanReiter, ToDoReiter, NotenReiter,  Reiter |

 

#### 5.3.2.8 Blackbox “Listener”

| **Bezeichnung**             | **Erklärung**                                                |
| --------------------------- | ------------------------------------------------------------ |
| Name                        | Listener                                                     |
| Zweck  & Verantwortlichkeit | Diese  Blackbox kümmert sich um das Empfangen der User-Inputs bzw. Events. |
| Schnittstelle(n)            | Listener  übernimmt die eingehende Schnittstelle des spezifischen Controllers von  spezifischer GUI (s.5.2.2.1).  Listener  hat eine ausgehende Schnittstelle zur Verwaltung. Er liefert dieser  Komponente die ausgelösten Events. |



#### 5.3.2.9 Blackbox “Verwaltung”

| **Bezeichnung**             | **Erklärung**                                                |
| --------------------------- | ------------------------------------------------------------ |
| Name                        | Verwaltung                                                   |
| Zweck  & Verantwortlichkeit | Der  Baustein verwaltet die Events vom Listener und sorgt dafür, dass eine  korrekte Bearbeitung mit diesen geschieht. |
| Schnittstelle(n)            | Verwaltung  hat eine eingehende Schnittstelle von Listener und erhält die ausgelösten  Events.  Verwaltung  übernimmt die ausgehenden Schnittstellen von spezifischer Adapter in Richtung  Datenmanager und spezifischer GUI (s.5.2.2.1). |

 

## 5.4 Wichtige Schnittstellen

| **Name**                          | **Verantwortung**                                            |
| --------------------------------- | ------------------------------------------------------------ |
| Blackbox  Spezifische GUI         | Er  verfügt über eine aus-, sowie eingehende Schnittstelle zum spez.  Controller.   Schickt  User Inputs an den zugehörigen Spezifischen Controller. |
| Blackbox  Spezifischer Controller | Der  Baustein hat eine ein-, sowie ausgehende Schnittstelle zu der spezifischen  GUI, eine ausgehende zum Datenmanager und eine eingehende von  Navigation.   Der  spezifische Controller verarbeitet den Input und veranlasst Änderung an der  GUI. |



| **Name**                          | **Verantwortung**                                            |
| --------------------------------- | ------------------------------------------------------------ |
| Blackbox  Spezifischer Controller | Der  spez. Controller hat eine ausgehende Schnittstelle zum Datenmanager. Er  schickt Datenanfragen an den Datenmanager und veranlasst Änderungen. |
| Blackbox  Datenmanager            | Der  Datenmanager hat eine eingehende Schnittstelle zum Spez. Controller. Er  erwartet Datenanfragen und Datenänderungen. |



# 6. Laufzeitsicht 

**Laufzeitszenario**

![img](https://i.loli.net/2021/04/14/zfZP3VImXFQq6Hi.jpg)

Abbildung 4: Laufzeitszenario

 

**Die Szenarien laufen immer wie folgt ab**

Der Benutzer hat die Möglichkeit eine bestimmte Interaktion mit dem Programm durchzuführen und etwas auszuwählen. Die spezifische GUI meldet dem Controller dann das entsprechende Event. Dieser reagiert demgemäß und gibt dem Benutzer eine weitere Interaktionsmöglichkeit (beispielsweise ein Pop-Up Fenster). 

Der User kann seine Daten sinngemäß eintragen und wenn erforderlich mit Speichern oder Abbruch betätigen. Dadurch sendet die spezifische GUI ein Event an den Controller. Dieser holt sich die für ihn relevanten Informationen heraus und schließt das PopUp. 

Der Datenmanager erhält vom Controller die Daten, um dann ein Speicherklassen Objekt mit ihnen zu Erstellen. Der Controller aktualisiert dann die GUI mit den neuen Daten.

 

# 7. Verteilungssicht

**Übersicht Umgebung**

Das Verteilungsdiagramm zeigt den Einsatz von StudiP unter Windows.Als Frontend dient die Main GUI ist das was man direkt sieht. 

Softwarevoraussetzungen auf dem PC sind Java Runtime Environment SE 11 (oder höher) und Windows.

Der kompilierte Java-Quelltext mit den sämtlichen Modulen enthält die UniP.jar.

![img](https://i.loli.net/2021/04/14/t3JlYjK9vnhZDEA.png)

Abbildung 5: Verteilungssicht

# 8. Typische Muster, Strukturen und Abläufe

##  8.1 Typische statische Strukturen

Als Lösungsstrategie für das Projekt “UniP” wird das Prinzip “Model-View-Controller” verwendet.

“Model” ist für die Speicherung von Daten und deren Verwaltung zuständig. Es ist der Datenmanager des Systems, der geeignete Speicherklassen enthält. Die Aufgaben des Controllers sind das Einlesen von Benutzerinteraktionen und die Vornahme entsprechender Änderungen. Aufgabe des Bausteins “View” ist die Beschreibung und Bereitstellung der Benutzeroberfläche.

![img](https://i.loli.net/2021/04/14/apxj1di6WfOUvu7.jpg)

Abbildung 6: Speicherklassen

 

Ein wesentlicher Unterschied des für den UniP verwendeten Konzeptes zum Normalen Model-View-Controller sind fehlende Verbindungen zwischen “View” und “Model” (s.5 Übersichtsdiagramm), was den Controller zur einzigen Schnittstelle zwischen dem “View” und “Model” macht. Diese Änderung ist durch die Ziele, Randbedingungen und Qualitätsanforderungen des Projektes bedingt.

Da der Controller die einzige Schicht für Interaktionen zwischen der Benutzeroberfläche und Datenmanager ist, wird die Ausarbeitung und Programmierung dieser Interaktionen für den Entwickler übersichtlicher da es keine sich Überschneidende Zuständigkeiten gibt.

Da keine Verbindungen zwischen View und Model vorhanden sind, wird die Benutzeroberfläche zu einem leicht austauschbaren Element. Die Gestaltung und Strukturierung kann unabhängig erfolgen. Das erleichtert dem Entwickler das Gestalten einer übersichtlichen und klar strukturierten Benutzeroberfläche, was das Erfüllen der Qualitätsziele vereinfacht (s. //QZ01, //QZ02).

 

## 8.2 Typischer Ablauf

 Ein typischer Ablauf in der UniP Anwendung lässt sich in zwei Hauptschritte untergliedern. 

Im ersten Schritt wird sich dabei um das Laden der GUI und dessen Verknüpfung mit dem Controller gekümmert. Im JavaFx Framework ist es möglich GUI Oberflächen in Form von “.fxml” Dateien zu beschreiben. Hierbei wird eine Container Struktur gebildet die das Layout der einzelnen darin befindlichen Elemente definiert. Außerdem kann durch das Attribut “fx:controller” im root Element des fxml files eine Controller-Klasse hinterlegt werden. Durch das Laden der “.fxml” Datei wird dadurch dann auch eine Controller Instanz erstellt. Durch das Hinterlegen eines Controller lassen sich durch attribute wie z.B. “onMouseClicked” auch direkt eine Listener Methode hinzufügen. Es sollte dabei darauf geachtet werden das jeder Listener für eine bestimmte Aktion oder ein bestimmtes Element verwendet wird um die verschiedenen Funktionen des Controller in verschiedene Methoden auszulagern.

 

![img](https://i.loli.net/2021/04/14/avPZoXebD8khIEl.jpg)

Abbildung 7: Klassen

 

Bei einem Klick auf das GUI Element wird nun die Listener Methode aufgerufen welche durch das übergebene “Event” Informationen über das Ereignis erhält wie z.B. welches Element betroffen ist. Daraufhin kann im Controller die GUI Baumstruktur mithilfe von “getParent()” und “getChildren” durchlaufen und verändert werden. Dabei werden die neuen angefragten 

Änderungen in die GUI übertragen. Die Controller registrieren sich außerdem per “registerController()” beim MainController welcher bei jedem ReiterWechsel “update()” bei allen registrierten Controllern ausführt. Dadurch können voneinander abhängige Reiter auf Änderungen während der Laufzeit reagieren. Wenn das Öffnen eines PopUp-Fensters zur Dateneingabe nötig ist wird dies über die ein neues “Stage” JavaFx-Objekt umgesetzt um den Input der Hauptanwendung zu blockieren während das Fenster geöffnet ist.

Um die Änderungen während der Laufzeit auch anderen Reitern zur Verfügung zu stellen und auch über die Laufzeit hinweg permanent zu Speichern kommt der zweite wichtige Schritt ins Spiel. Über das statische Klassenattribut “datenmanager” in der Main-Klasse “UniP” haben die Controller eine Referenz auf den Datenmanager. Dieser bietet alle nötigen Methoden um die eingegebenen Daten in “SpeicherKlassen” abzulegen.

 

![img](https://i.loli.net/2021/04/14/i8RsmAazFYChWyj.png)

 

Abbildung 8: Main Klasse


 ![img](https://i.loli.net/2021/04/14/mLAlfUzbTuFoZk5.jpg)

Nach dem die Änderungen über den Datenmanager vorgenommen wurden stehen diese dann auch den anderen Controllern zur Verfügung. Um die Daten nun auch nach dem Schließen der Anwendung zu Speichern wird der Datenmanager durch den MainController beim Schließen der Anwendung zum Speichern angewiesen. 

 

Dazu werden alle SpeicherKlassen-Listen mit der Bibliothek Gson zu Json Strings konvertiert und in Dateien geschrieben. Diese Dateien werden dann beim Start der Anwendung im Konstruktor des Datenmanagers auch wieder ausgelesen.

 

# 9. Technische Konzepte

## 9.1 Persistenz

Für die Speicherung und Verwaltung von Daten wird ein Baustein namens „Model“ angelegt. Er dient als Datenbank des Systems und verfügt über eine eingehende Schnittstelle zum Adapter. Von dem Adapter übernimmt das Modell Änderungen und gibt an ihn angeforderte Daten zurück.

Zugriff auf die Daten erfolgt immer auf die gleiche Art. Aktionen des Benutzers werden von dem GUI-Listener überwacht. Bei der Durchführung einer Aktion wird das Event dem Adapter mitgeteilt. Der Adapter liest die Anfrage und leitet sie an den Datenmanager weiter. Der Datenmanager sendet die angeforderten Daten an den Adapter, welcher diese an die GUI weiter leitet. Zum Schluss werden die Daten dem Benutzer angezeigt.

Lebensdauer von Daten sowie deren Aktualisierung sind in vollständig dem Benutzer überlassen und erfolgen über manuelle Eingaben. Ausnahme sind grundlegende vorprogrammierte Informationen, wie die Wochentage, Monate, usw.

Daten werden in JSON Format gespeichert.

 

## 9.2 Benutzungsoberfläche

Der Aufbau der Benutzeroberfläche erfolgt mittels GUI. 

Oben im Reiter Menü navigiert der Nutzer zwischen unterschiedlichen Reitern, von denen jeder eine separate Einheit ist und um eine unabhängige Benutzeroberfläche verfügt.

Das Weiterleiten von Daten aus dem Datenmanager zur einer bestimmten Benutzeroberfläche ist die Aufgabe des jeweiligen spezifischen Adapters. Er nimmt die Anfragen aus der Benutzeroberfläche und führt Änderungen im Dateimanager und Benutzeroberfläche durch.

Durch Aktionen und Eingaben des Benutzers kommen in das System ebenso über einen der spezifischen Adapter zurück und werden anschließend auf der richtigen Speicherklasse im Datenmanager gespeichert. 

Der Einsatz des modifizierten Model View Control Musters erleichtert spätere Änderungen und Erweiterungen. In dem Projekt UniP kommt eine Variation des Model View Controller Musters zum Einsatz, bei der keine direkte Kommunikation zwischen der Graphischen Oberfläche und der Datenbank stattfindet (siehe 4. Lösungsstrategie)

Als Framework zur Gestaltung, Entwicklung und Ausführung der Benutzeroberfläche wird in dem Projekt JavaFX eingesetzt.

 

## 9.3 Ablauf Dialog oder Workflow Steuerung

Sowohl fachliche als auch technische Abläufe werden innerhalb des Adapters verwaltet, koordiniert und gesteuert.

Jede einzelne GUI verfügt über einen spezifischen Adapter, der die Aktionen des Benutzers und die von Ihm eingegebenen Daten einliest und verarbeitet.

Beim Anliegen eines neuen Termins leitet der Adapter die Eingaben an die Software interne Datenbank, welche dann einen Konstruktor aufruft und eine neue Instanz einer entsprechenden Klasse erstellt. (s 6.1, 6.2)

Um eine Trennung der User-Inputs und der eigentlichen Bearbeitung der Anfragen zu gewährleisten wird eine Funktionale Trennung im Adapter vorgenommen. (s.6.Laufzeitschicht)



# 10. Entwurfsentscheidungen

 

| Entscheidung                        | Gründe,  Konsequenzen, Alternativen                          |
| ----------------------------------- | ------------------------------------------------------------ |
| Programmiersprache:  Java           | Bietet  gute Grundlage aller Arten von Software und umfangreiche Bibliotheken. Hilft  Entwicklungszeit kurz zu halten.  Vorkenntnisse  der Programmierer sind gegeben. |
| Bibliotheken:  Bootstrapfx und Gson | Bootstrapfx  ermöglicht mittels CSS Vorlagen schönere Designs.   Gson ermöglicht einfacher mit JSON und Java zusammenzuarbeiten. |
| Framework:  Javafx                  | Alternative:  Swing  JavaFX  ermöglicht das Darstellen einer grafischen Oberfläche und lässt diese wahlweise  verändern. |
| Filebasierte  Storage               | Anstatt  eine Datenbank zu verwenden, werden die Dateien mittels JSON gespeichert und  hinterlegt. |



# 11. Abbildungsverzeichnis 

Abbildung 1: Fachlicher Kontext

Abbildung 2: Technische Kontextabgrenzung

Abbildung 3: Übersichtsdiagramm

Abbildung 4: Laufzeitszenario

Abbildung 5: Verteilungssicht

Abbildung 6: Speicherklassen

Abbildung 7: Klassen

Abbildung 8: Main Klasse



 

# 12. Glossar

**Modul**     

Eine abgeschlossene Lerneinheit, die sich aus verschiedenen Lehrveranstaltungen zu einem gemeinsamen Teilgebiet zusammensetzen kann

 
