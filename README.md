# 🎮 JavaQuest - Jeu RPG en JavaFX

Un jeu d'aventure développé en Java avec interface graphique JavaFX et base de données H2 intégrée.

## 🚀 Installation rapide (2 étapes)

**En cas de problème, voir la section [Dépannage](#-résolution-de-problèmes)**

### Windows

**Prérequis :** [JDK 17](https://www.azul.com/downloads/?package=jdk#zulu) + [Maven](https://maven.apache.org/download.cgi)

#### Méthode 1 : Ligne de commande (fiable)
```batch
cd chemin\vers\JAVAQUEST
mvn clean compile javafx:run
```

#### Méthode 2 : Double-clic
- Essayez `JavaQuest.bat`
- Si ça ne fonctionne pas → Utilisez la méthode 1

#### Méthode 3 : Raccourci Windows
1. Créez un raccourci sur le bureau
2. **Cible :** `cmd.exe /k "cd /d C:\votre\chemin\JAVAQUEST && mvn clean compile javafx:run"`

### macOS / Linux  

**Prérequis :** [JDK 17](https://www.azul.com/downloads/?package=jdk#zulu) + [Maven](https://maven.apache.org/download.cgi)

```bash
cd /chemin/vers/JAVAQUEST
mvn clean compile javafx:run
```

#### Installation automatique (avec Homebrew)
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

## 🎮 Gameplay

1. **Création personnage** : Choisir entre Guerrier (force) ou Magicien (magie)
2. **Lancer de dé** : Avancer sur le plateau (1-6 cases)
3. **Événements** :
   - **Cases vides** : Repos et récupération
   - **Ennemis** : Combat ou fuite
   - **Potions** : Soins instantanés
   - **Équipements** : Nouvelles armes/sorts
4. **Objectif** : Atteindre la case 64 pour gagner !

## 🛠️ Développement

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

# Package (optionnel)
mvn clean package
```

## 💾 Technologies utilisées

| Technologie | Version | Usage |
|-------------|---------|-------|
| **Java** | 17+ | Langage principal |
| **JavaFX** | 21 | Interface graphique |
| **H2 Database** | 2.2+ | Base de données embarquée |
| **Maven** | 3.6+ | Gestion des dépendances |
| **Gson** | 2.10+ | Sérialisation JSON |

## 🔧 Configuration requise

- **Java JDK** : 17 ou supérieur
- **Maven** : 3.6 ou supérieur  
- **Mémoire** : 256 MB RAM minimum
- **Stockage** : 50 MB d'espace libre
- **OS** : Windows 10+, macOS 10.14+, Linux

## 🐛 Résolution de problèmes

### ❌ Erreur "JavaFX components missing"
```bash
# Vérifier l'installation Java
java -version

# Utiliser les scripts fournis
./JavaQuest.sh    # macOS/Linux
JavaQuest.bat     # Windows
```

### ❌ "Command not found: mvn"
```bash
# Installer Maven
# Windows : winget install Apache.Maven
# macOS : brew install maven
# Linux : sudo apt install maven
```

### ❌ Permission refusée (macOS/Linux)
```bash
chmod +x JavaQuest.sh
```

### ❌ Dépannage Windows
Si les fichiers `.bat` ne fonctionnent pas :

- **Solution rapide** : Utilisez directement `mvn clean compile javafx:run` dans cmd
- **Cause** : Restrictions de sécurité Windows ou antivirus  
- **Alternative** : Utilisez PowerShell ou IntelliJ IDEA

### ❌ Erreur de compilation
```bash
# Nettoyer et recompiler
mvn clean compile

# Vérifier la version Java
java -version  # Doit être 17+
```

## 📝 Licence

Projet éducatif - Code source disponible pour consultation et apprentissage.

---

**Développé par Pierrick Viret** | *Compatible Windows, macOS, Linux* | [🎮 Jouer maintenant !](#-installation-rapide-2-étapes)
