# JavaQuest PowerShell Launcher
# Sauvegardez ce fichier comme JavaQuest.ps1

Write-Host "🎮 JAVAQUEST - Launcher PowerShell 🎮" -ForegroundColor Cyan
Write-Host "=====================================" -ForegroundColor Cyan
Write-Host ""

# Obtenir le répertoire du script
$scriptPath = Split-Path -Parent $MyInvocation.MyCommand.Path
Set-Location $scriptPath

Write-Host "📁 Répertoire : $scriptPath" -ForegroundColor Yellow
Write-Host ""

Write-Host "🚀 Lancement de JavaQuest..." -ForegroundColor Green
Write-Host ""
Write-Host "⚠️  GARDEZ cette fenêtre ouverte pendant le jeu !" -ForegroundColor Red
Write-Host ""

try {
    # Lancer la commande qui fonctionne
    & cmd /c "mvn clean compile javafx:run"
    
    Write-Host ""
    Write-Host "✅ Jeu terminé avec succès !" -ForegroundColor Green
} catch {
    Write-Host ""
    Write-Host "❌ Erreur lors du lancement : $_" -ForegroundColor Red
}

Write-Host ""
Read-Host "Appuyez sur Entrée pour fermer"