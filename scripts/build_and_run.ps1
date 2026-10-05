# Checkpoint 3 - Script usado para compilar, instalar y ejecutar la app durante el examen.
# Requiere: un emulador o dispositivo Android ya conectado (ver `adb devices`).
#
# Uso: desde la raiz del proyecto -> .\scripts\build_and_run.ps1

$ErrorActionPreference = "Stop"

# Android Studio trae su propio JDK de 64 bits; se usa para que Gradle no falle
# por falta de memoria con un JRE de 32 bits instalado en el sistema.
$env:JAVA_HOME = "C:\Program Files\Android\Android Studio\jbr"

$sdk = "C:\Users\OSCAR\AppData\Local\Android\Sdk"
$adb = Join-Path $sdk "platform-tools\adb.exe"
$packageId = "com.example.parcial1"

Write-Host "1) Compilando el APK de debug..."
.\gradlew.bat assembleDebug

$apk = Get-ChildItem -Path "app\build\outputs\apk\debug" -Filter "*.apk" | Select-Object -First 1
if (-not $apk) {
    throw "No se genero ningun APK en app\build\outputs\apk\debug"
}

Write-Host "2) Instalando $($apk.Name) en el dispositivo/emulador..."
& $adb install -r $apk.FullName

Write-Host "3) Lanzando la app..."
& $adb shell am start -n "$packageId/.MainActivity"

Write-Host "Listo. La app deberia estar abierta en el emulador/dispositivo."
