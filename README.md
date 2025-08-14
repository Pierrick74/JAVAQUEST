# 🎮 JavaQuest - Jeu RPG en JavaFX

Un jeu d'aventure développé en Java avec interface graphique JavaFX et base de données H2 intégrée.

## 🚀 Installation rapide (2 étapes)

### Windows
1. **Installer les prérequis** : [JDK 17](https://www.azul.com/downloads/?package=jdk#zulu) + [Maven](https://maven.apache.org/download.cgi)
2. **Lancer le jeu** : Double-cliquer sur `JavaQuest.bat`

### macOS / Linux  
1. **Installer les prérequis** : [JDK 17](https://www.azul.com/downloads/?package=jdk#zulu) + [Maven](https://maven.apache.org/download.cgi)
2. **Lancer le jeu** : Double-cliquer sur `JavaQuest.sh`

### Installation automatique (macOS/Linux avec Homebrew)
```bash
brew install openjdk@17 maven
./JavaQuest.sh
```

## 🎯 Fonctionnalités

- **Personnages** : Guerrier ou Magicien avec stats évolutives
- **Plateau de jeu** : 64 cases avec événements aléatoires
- **Système de combat** : Combats contre ennemis avec XP
- **Équipements** : Armes et sorts avec statistiques
- **Sauvegarde** : Base de données H2 intégrée
- **Interface** : JavaFX moderne et responsive

## 🛠 Développement

### Structure du projet
```
JavaQuest/
├── src/main/java/fr/pierrickviret/javaquest/
│   ├── Main.java                    # Point d'entrée
│   ├── Game.java                    # Logique du jeu
│   ├── board/                       # Plateau et cases
│   ├── character/                   # Personnages et ennemis
│   ├── db/                         # Base de données H2
│   └── javafx/                     # Interface utilisateur
├── src/main/resources/             # Assets (images, fonts)
├── JavaQuest.sh                    # Lanceur macOS/Linux
├── JavaQuest.bat                   # Lanceur Windows
└── pom.xml                         # Configuration Maven
```

### Compilation et exécution
```bash
# Compilation
mvn clean compile

# Lancement
mvn javafx:run

# Tests
mvn test

# Package
mvn clean package
```

## 🎮 Gameplay

1. **Création personnage** : Choisir entre Guerrier (force) ou Magicien (magie)
2. **Lancer de dé** : Avancer sur le plateau (1-6 cases)
3. **Événements** :
   - **Cases vides** : Repos
   - **Ennemis** : Combat ou fuite
   - **Potions** : Soins
   - **Équipements** : Nouvelles armes/sorts
4. **Objectif** : Atteindre la case 64 pour gagner

## 💾 Technologies utilisées

- **Java 17** : Langage principal
- **JavaFX 21** : Interface graphique
- **H2 Database** : Base de données embarquée
- **Maven** : Gestion des dépendances
- **Gson** : Sérialisation JSON

## 🔧 Configuration requise

- **Java JDK** : 17 ou supérieur
- **Maven** : 3.6 ou supérieur
- **Mémoire** : 256 MB RAM minimum
- **OS** : Windows 10+, macOS 10.14+, Linux

## 📁 Distribution

Le projet inclut :
- Code source complet
- Assets graphiques et audio
- Scripts de lancement multiplateforme
- Documentation complète

## 🐛 Résolution de problèmes

### Erreur "JavaFX components missing"
```bash
# Vérifier l'installation Java
java -version

# Utiliser les scripts fournis
./JavaQuest.sh  # ou JavaQuest.bat
```

### Permission refusée (macOS)
```bash
chmod +x JavaQuest.sh
```

## 📝 Licence

Projet éducatif - Code source disponible pour consultation et apprentissage.

---

**Développé par Pierrick Viret** | *Compatible Windows, macOS, Linux*

*📧 Contact pour questions techniques ou suggestions d'amélioration*