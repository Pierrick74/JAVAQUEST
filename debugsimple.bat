@echo off
title Debug Simple - JavaQuest
echo ===========================================
echo    DEBUG SIMPLE - JAVAQUEST
echo ===========================================
echo.

:: Forcer une pause au début
echo DEBUT DU SCRIPT - Appuyez sur une touche...
pause
echo.

:: Test Java
echo === TEST JAVA ===
echo Commande: java -version
java -version
echo.
echo Code erreur Java: %errorlevel%
echo.
echo Appuyez sur une touche pour continuer...
pause
echo.

:: Test Maven
echo === TEST MAVEN ===
echo Commande: mvn -version
mvn -version
echo.
echo Code erreur Maven: %errorlevel%
echo.
echo Appuyez sur une touche pour continuer...
pause
echo.

:: Test répertoire
echo === TEST REPERTOIRE ===
echo Repertoire actuel: %CD%
echo.
echo Fichiers dans le repertoire:
dir /b
echo.
echo Recherche pom.xml:
if exist "pom.xml" (
    echo TROUVE: pom.xml
) else (
    echo MANQUE: pom.xml
)
echo.
echo Appuyez sur une touche pour continuer...
pause
echo.

:: Test compilation
echo === TEST COMPILATION ===
echo Commande: mvn clean compile
mvn clean compile
echo.
echo Code erreur compilation: %errorlevel%
echo.

echo ===========================================
echo        FIN DES TESTS
echo ===========================================
echo.
echo Si vous voyez ce message, tous les tests sont passes !
echo.
echo Appuyez sur une touche pour fermer...
pause