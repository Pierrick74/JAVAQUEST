@echo off
title JavaQuest - Debug Launcher
cls
echo =====================================
echo     JAVAQUEST - Debug Launcher
echo =====================================
echo.

:: Pause immediate pour voir les erreurs
echo Appuyez sur une touche pour commencer le diagnostic...
pause >nul
cls

:: Se placer dans le répertoire du script
echo [DEBUG] Changement de repertoire...
cd /d "%~dp0"
echo Repertoire actuel: %CD%
echo.

:: Test de Java - Version detailed
echo [1/5] Test de Java...
echo Commande: java -version
java -version
set JAVA_ERROR=%errorlevel%
echo Code retour Java: %JAVA_ERROR%
if %JAVA_ERROR% neq 0 (
    echo [ERREUR] Java non trouve dans PATH
    echo.
    echo Solutions:
    echo 1. Installez Java 17+: https://adoptium.net/
    echo 2. Ajoutez Java au PATH systeme
    echo 3. Ou installez via: winget install Eclipse.Temurin.17.JDK
    goto :error
) else (
    echo [OK] Java detecte
)
echo.

:: Test de Maven - Version detailed
echo [2/5] Test de Maven...
echo Commande: mvn -version
mvn -version
set MAVEN_ERROR=%errorlevel%
echo Code retour Maven: %MAVEN_ERROR%
if %MAVEN_ERROR% neq 0 (
    echo [ERREUR] Maven non trouve dans PATH
    echo.
    echo Solutions:
    echo 1. Installez Maven: https://maven.apache.org/download.cgi
    echo 2. Ajoutez Maven au PATH systeme
    echo 3. Ou installez via: winget install Apache.Maven
    goto :error
) else (
    echo [OK] Maven detecte
)
echo.

:: Test du projet
echo [3/5] Verification du projet...
if not exist "pom.xml" (
    echo [ERREUR] pom.xml non trouve dans: %CD%
    echo.
    echo Verifiez que vous etes dans le bon repertoire
    dir *.xml *.java /b
    goto :error
) else (
    echo [OK] pom.xml present
)

if not exist "src" (
    echo [ERREUR] Dossier src/ manquant
    goto :error
) else (
    echo [OK] Dossier src/ present
)
echo.

:: Test de compilation
echo [4/5] Test de compilation...
echo Commande: mvn clean compile
echo.
mvn clean compile
set COMPILE_ERROR=%errorlevel%
echo.
echo Code retour compilation: %COMPILE_ERROR%
if %COMPILE_ERROR% neq 0 (
    echo [ERREUR] Echec de la compilation
    echo.
    echo Verifiez les erreurs Maven ci-dessus
    goto :error
) else (
    echo [OK] Compilation reussie
)
echo.

:: Lancement du jeu
echo [5/5] Lancement du jeu...
echo.
echo ATTENTION: Ne fermez PAS cette fenetre pendant que le jeu tourne !
echo.
echo Commande: mvn javafx:run
echo.
mvn javafx:run
set RUN_ERROR=%errorlevel%
echo.
echo Code retour execution: %RUN_ERROR%
if %RUN_ERROR% neq 0 (
    echo [ERREUR] Echec du lancement
    echo.
    echo Tentative alternative...
    echo Commande: mvn javafx:run -X
    mvn javafx:run -X
    goto :error
) else (
    echo [OK] Jeu termine normalement
)
echo.
goto :success

:error
echo.
echo =====================================
echo         DIAGNOSTIC D'ERREUR
echo =====================================
echo.
echo Erreur detectee. Codes de retour:
echo - Java: %JAVA_ERROR%
echo - Maven: %MAVEN_ERROR%
echo - Compilation: %COMPILE_ERROR%
echo - Execution: %RUN_ERROR%
echo.
echo Repertoire de travail: %CD%
echo.
echo Verifiez les messages d'erreur ci-dessus
echo.
goto :end

:success
echo.
echo =====================================
echo        EXECUTION TERMINEE
echo =====================================
echo.
echo Le jeu s'est execute avec succes !
echo.

:end
echo Appuyez sur une touche pour fermer...
pause >nul