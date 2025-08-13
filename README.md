# 🎮 JAVAQUEST

Un jeu d'aventure développé en Java avec interface graphique JavaFX et persistance des données via MySQL.

## 📋 Table des matières

- [Prérequis](#-prérequis)
- [Installation](#-installation)
  - [1. Cloner le repository](#1-cloner-le-repository)
  - [2. Configuration de la base de données](#2-configuration-de-la-base-de-données)
  - [3. Configuration du projet](#3-configuration-du-projet)
- [Compilation et exécution](#-compilation-et-exécution)
- [Structure du projet](#-structure-du-projet)
- [Dépendances](#-dépendances)
- [Dépannage](#-dépannage)

## 🔧 Prérequis

Avant d'installer JAVAQUEST, assurez-vous d'avoir les éléments suivants installés sur votre système :

| Composant | Version minimale | Vérification |
|-----------|-----------------|--------------|
| **Java JDK** | 11 ou supérieur | `java -version` |
| **JavaFX** | 11 ou supérieur | Inclus dans le JDK ou à installer séparément |
| **MySQL** | 5.7 ou supérieur | `mysql --version` |
| **Git** | 2.0 ou supérieur | `git --version` |

### Installation des prérequis

#### Windows
```bash
# Java JDK (via Chocolatey)
choco install openjdk

# MySQL
choco install mysql
```

#### macOS
```bash
# Java JDK (via Homebrew)
brew install openjdk

# MySQL
brew install mysql
brew services start mysql
```

#### Linux (Ubuntu/Debian)
```bash
# Java JDK
sudo apt update
sudo apt install openjdk-11-jdk

# MySQL
sudo apt install mysql-server
sudo systemctl start mysql
```

## 📦 Installation

### 1. Cloner le repository

```bash
# Cloner le projet
git clone https://github.com/Pierrick74/JAVAQUEST.git

# Accéder au dossier
cd JAVAQUEST
```

### 2. Configuration de la base de données

#### Étape 2.1 : Créer la base de données

Connectez-vous à MySQL et créez la base de données :

```sql
-- Se connecter à MySQL
mysql -u root -p

-- Créer la base de données
CREATE DATABASE javaquest;

-- Créer un utilisateur dédié (optionnel mais recommandé)
CREATE USER 'javaquest_user'@'localhost' IDENTIFIED BY 'votre_mot_de_passe';
GRANT ALL PRIVILEGES ON javaquest.* TO 'javaquest_user'@'localhost';
FLUSH PRIVILEGES;

-- Sélectionner la base de données
USE javaquest;
```

#### Étape 2.2 : Créer les tables

Exécutez le script SQL suivant pour créer les tables nécessaires :

```sql
-- Table des personnages
CREATE TABLE IF NOT EXISTS JavaquestCharacters (
    id INT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(100) NOT NULL,
    type VARCHAR(50) NOT NULL,
    health INT NOT NULL,
    maxHealth INT NOT NULL,
    attack INT NOT NULL,
    experience INT NOT NULL,
    boostAttack INT NOT NULL,
    position INT NOT NULL,
    offensiveEquipment1 VARCHAR(100),
    offensiveEquipment2 VARCHAR(100),
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

-- Table des positions des joueurs
CREATE TABLE IF NOT EXISTS JavaquestPlayerPositions (
    id INT AUTO_INCREMENT PRIMARY KEY,
    character_id INT NOT NULL,
    position INT NOT NULL,
    saved_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    FOREIGN KEY (character_id) REFERENCES JavaquestCharacters(id) ON DELETE CASCADE
);

-- Table du plateau de jeu
CREATE TABLE IF NOT EXISTS JavaquestBoard (
    id INT AUTO_INCREMENT PRIMARY KEY,
    data JSON NOT NULL,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);
```

#### Étape 2.3 : Configuration de la connexion

Modifiez le fichier `src/fr/pierrickviret/javaquest/db/SQLRepository.java` pour ajuster les paramètres de connexion :

```java
// Remplacez ces valeurs par vos propres paramètres
private static final String DB_URL = "jdbc:mysql://localhost:3306/javaquest";
private static final String DB_USER = "javaquest_user";
private static final String DB_PASSWORD = "votre_mot_de_passe";
```

### 3. Configuration du projet

#### Option A : IntelliJ IDEA (Recommandé)

1. **Ouvrir le projet**
   - File → Open → Sélectionner le dossier JAVAQUEST

2. **Configurer le JDK**
   - File → Project Structure → Project
   - Définir Project SDK : JDK 11 ou supérieur
   - Définir Project language level : 11

3. **Configurer JavaFX**
   - File → Project Structure → Libraries
   - Cliquer sur + → Java
   - Naviguer vers votre installation JavaFX et sélectionner le dossier `lib`

4. **Ajouter les dépendances**
   - Les JAR sont déjà inclus dans le dossier `lib/`
   - Vérifier qu'ils sont bien ajoutés au classpath du projet

5. **Configuration de l'exécution**
   - Run → Edit Configurations
   - Ajouter une nouvelle configuration Application
   - Main class : `fr.pierrickviret.javaquest.Main`
   - VM options (pour JavaFX) : 
   ```
   --module-path /path/to/javafx/lib --add-modules javafx.controls,javafx.fxml
   ```

#### Option B : Eclipse

1. **Importer le projet**
   - File → Import → Existing Projects into Workspace
   - Sélectionner le dossier JAVAQUEST

2. **Configurer le Build Path**
   - Clic droit sur le projet → Properties → Java Build Path
   - Onglet Libraries → Add External JARs
   - Ajouter tous les JAR du dossier `lib/`
   - Ajouter les JAR JavaFX

3. **Configuration de l'exécution**
   - Run → Run Configurations
   - Créer une nouvelle Java Application
   - Main class : `fr.pierrickviret.javaquest.Main`
   - Arguments → VM arguments :
   ```
   --module-path /path/to/javafx/lib --add-modules javafx.controls,javafx.fxml
   ```

#### Option C : Visual Studio Code

1. **Installer les extensions nécessaires**
   - Extension Pack for Java
   - JavaFX Support

2. **Ouvrir le projet**
   - File → Open Folder → Sélectionner JAVAQUEST

3. **Configurer launch.json**
   Créer `.vscode/launch.json` :
   ```json
   {
     "version": "0.2.0",
     "configurations": [
       {
         "type": "java",
         "name": "Launch JAVAQUEST",
         "request": "launch",
         "mainClass": "fr.pierrickviret.javaquest.Main",
         "vmArgs": "--module-path /path/to/javafx/lib --add-modules javafx.controls,javafx.fxml",
         "classPaths": ["${workspaceFolder}/lib/*"]
       }
     ]
   }
   ```

## 🚀 Compilation et exécution

### Méthode 1 : Via l'IDE
Utiliser le bouton Run de votre IDE après configuration

### Méthode 2 : Ligne de commande

#### Compilation
```bash
# Windows
javac -cp ".;lib/*" --module-path /path/to/javafx/lib --add-modules javafx.controls,javafx.fxml -d out src/fr/pierrickviret/javaquest/*.java src/fr/pierrickviret/javaquest/*/*.java

# Linux/macOS
javac -cp ".:lib/*" --module-path /path/to/javafx/lib --add-modules javafx.controls,javafx.fxml -d out src/fr/pierrickviret/javaquest/*.java src/fr/pierrickviret/javaquest/*/*.java
```

#### Exécution
```bash
# Windows
java -cp ".;out;lib/*" --module-path /path/to/javafx/lib --add-modules javafx.controls,javafx.fxml fr.pierrickviret.javaquest.Main

# Linux/macOS
java -cp ".:out:lib/*" --module-path /path/to/javafx/lib --add-modules javafx.controls,javafx.fxml fr.pierrickviret.javaquest.Main
```

### Méthode 3 : Créer un JAR exécutable

```bash
# Compiler d'abord le projet
javac -cp "lib/*" -d out src/fr/pierrickviret/javaquest/**/*.java

# Créer le JAR
jar cvfm JAVAQUEST.jar src/META-INF/MANIFEST.MF -C out . -C lib .

# Exécuter le JAR
java --module-path /path/to/javafx/lib --add-modules javafx.controls,javafx.fxml -jar JAVAQUEST.jar
```

## 📁 Structure du projet

```
JAVAQUEST/
├── 📁 src/
│   ├── 📁 fr/pierrickviret/javaquest/
│   │   ├── 📄 Main.java                 # Point d'entrée
│   │   ├── 📄 Game.java                 # Logique principale du jeu
│   │   ├── 📄 Menu.java                 # Gestion des menus
│   │   ├── 📄 Dice.java                 # Système de dés
│   │   ├── 📁 board/                    # Gestion du plateau
│   │   ├── 📁 character/                # Classes de personnages
│   │   ├── 📁 equipement/               # Système d'équipement
│   │   ├── 📁 db/                       # Couche base de données
│   │   │   └── 📄 SQLRepository.java    # Connexion MySQL
│   │   └── 📁 javafx/                   # Interface graphique
│   │       ├── 📄 MainView.java         # Vue principale
│   │       ├── 📁 assets/               # Ressources graphiques
│   │       └── 📁 composants/           # Composants UI réutilisables
│   └── 📁 META-INF/
│       └── 📄 MANIFEST.MF                # Manifest pour JAR
├── 📁 lib/                               # Bibliothèques externes
│   ├── 📄 gson-2.10.1.jar               # Sérialisation JSON
│   ├── 📄 mysql-connector-j-9.3.0.jar   # Connecteur MySQL
│   └── 📄 protobuf-java-4.29.0.jar      # Protocol Buffers
├── 📄 .gitignore
├── 📄 JAVAQUEST.iml                     # Configuration IntelliJ
└── 📄 README.md                          # Documentation

```

## 📚 Dépendances

Le projet utilise les bibliothèques suivantes (incluses dans le dossier `lib/`) :

| Bibliothèque | Version | Utilisation |
|--------------|---------|-------------|
| **Gson** | 2.10.1 | Sérialisation/désérialisation JSON pour la sauvegarde |
| **MySQL Connector/J** | 9.3.0 | Connexion et interactions avec MySQL |
| **Protocol Buffers** | 4.29.0 | Dépendance du MySQL Connector |
| **JavaFX** | 11+ | Interface graphique (à installer séparément) |

## 🔧 Dépannage

### Problèmes courants et solutions

#### 1. Erreur : "JavaFX runtime components are missing"
**Solution :** Assurez-vous d'avoir ajouté les VM arguments JavaFX :
```bash
--module-path /path/to/javafx/lib --add-modules javafx.controls,javafx.fxml
```

#### 2. Erreur : "Access denied for user 'javaquest_user'@'localhost'"
**Solution :** Vérifiez les identifiants MySQL dans `SQLRepository.java` et assurez-vous que l'utilisateur existe et a les permissions nécessaires.

#### 3. Erreur : "Communications link failure"
**Solution :** 
- Vérifiez que MySQL est en cours d'exécution
- Vérifiez le port (par défaut 3306)
- Vérifiez le pare-feu

#### 4. Erreur : "ClassNotFoundException: com.mysql.cj.jdbc.Driver"
**Solution :** Assurez-vous que `mysql-connector-j-9.3.0.jar` est bien dans le classpath

#### 5. JavaFX non trouvé sur Java 11+
**Solution :** JavaFX n'est plus inclus dans le JDK depuis Java 11. Téléchargez-le séparément depuis [openjfx.io](https://openjfx.io/)

### Logs et débogage

Pour activer les logs détaillés, ajoutez ces paramètres JVM :
```bash
-Djava.util.logging.config.file=logging.properties
-Dcom.sun.javafx.isEmbedded=true
-Dprism.verbose=true
```

## 📧 Support

Si vous rencontrez des problèmes :
1. Vérifiez d'abord la section [Dépannage](#-dépannage)
2. Consultez les [Issues](https://github.com/Pierrick74/JAVAQUEST/issues) existantes
3. Créez une nouvelle issue avec :
   - Description du problème
   - Messages d'erreur complets
   - Version de Java et JavaFX utilisées
   - Système d'exploitation

## 📝 License

[À définir]

---

**Bon jeu !** 🎮
