@echo off
title JavaQuest - Launcher
echo =====================================
echo     🎮 JAVAQUEST - Launcher 🎮
echo =====================================
echo.

:: Se placer dans le répertoire du script
cd /d "%~dp0"

:: Test de Java
echo [1/3] Test de Java...
java -version >nul 2>&1
if %errorlevel% neq 0 (
    echo ❌ ERREUR: Java non trouve !
    echo Installez Java 17+ depuis https://adoptium.net/
    goto :error
) else (
    echo ✅ Java OK
    :: Vérification version Java 17+
    for /f "tokens=3" %%g in ('java -version 2^>^&1 ^| findstr /i "version"') do (
        set JAVA_VERSION=%%g
    )
    set JAVA_VERSION=%JAVA_VERSION:"=%
    for /f "delims=. tokens=1-3" %%a in ("%JAVA_VERSION%") do (
        set /a JAVA_MAJOR=%%a
        if %%a equ 1 set /a JAVA_MAJOR=%%b
    )
    if %JAVA_MAJOR% lss 17 (
        echo ❌ ERREUR: Java %JAVA_MAJOR% detecte, Java 17+ requis !
        echo Installez Java 17+ depuis https://adoptium.net/
        goto :error
    )
)

:: Test de Maven
echo [2/3] Test de Maven...
mvn -v >nul 2>&1
if %errorlevel% neq 0 (
    echo ❌ ERREUR: Maven non trouve !
    echo Installez Maven depuis https://maven.apache.org/
    goto :error
) else (
    echo ✅ Maven OK
)

:: Test de l'existence du pom.xml
if not exist "pom.xml" (
    echo ❌ ERREUR: pom.xml non trouve !
    echo Assurez-vous d'etre dans le repertoire du projet
    goto :error
)

:: Installation des dépendances
echo [3/4] Installation des dependances...
mvn clean install -q
if %errorlevel% neq 0 (
    echo ❌ Erreur lors de l'installation des dependances !
    mvn clean install
    if %errorlevel% neq 0 (
        echo ❌ Echec de l'installation
        goto :error
    )
) else (
    echo ✅ Dependances OK
)

:: Lancement du jeu
echo [4/4] Lancement de JavaQuest...
echo.
echo Test de lancement avec logs detailles...
echo.
mvn javafx:run
echo.
echo Code de retour: %errorlevel%
if %errorlevel% neq 0 (
    echo.
    echo ❌ Erreur detectee lors du lancement !
    echo.
    echo Tentative alternative avec compile...
    mvn clean compile exec:java -Dexec.mainClass="fr.pierrickviret.javaquest.Main"
    if %errorlevel% neq 0 (
        echo.
        echo ❌ Echec avec exec:java aussi
        echo.
        echo Derniere tentative avec jar...
        mvn clean package
        java -jar target\javaquest-1.0.jar
        if %errorlevel% neq 0 (
            echo ❌ Toutes les tentatives ont echoue
            goto :error
        )
    )
)

echo.
echo ✅ Jeu termine normalement
goto :end

:error
echo.
echo ❌ Erreur detectee !
echo Verifiez l'installation de Java 17+ et Maven
echo.

:end
pause