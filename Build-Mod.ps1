$ErrorActionPreference = "Stop"

$root = Split-Path -Parent $MyInvocation.MyCommand.Path
Set-Location $root

$java17 = "C:\Program Files\Java\jdk-17"
$gradleVersion = "8.8"
$toolsDir = Join-Path $root ".gradle-tools"
$tmpDir = Join-Path $root ".tmp"
$zipPath = Join-Path $toolsDir "gradle-$gradleVersion-bin.zip"
$gradleHome = Join-Path $toolsDir "gradle-$gradleVersion"
$gradleBat = Join-Path $gradleHome "bin\gradle.bat"

if (!(Test-Path (Join-Path $java17 "bin\java.exe"))) {
    throw "Java 17 was not found at $java17"
}

$env:JAVA_HOME = $java17
$env:PATH = "$java17\bin;$env:PATH"
$env:TEMP = $tmpDir
$env:TMP = $tmpDir
$env:GRADLE_OPTS = "-Djava.io.tmpdir=$tmpDir"

New-Item -ItemType Directory -Force -Path $toolsDir | Out-Null
New-Item -ItemType Directory -Force -Path $tmpDir | Out-Null

if (!(Test-Path $gradleBat)) {
    Write-Host "Downloading Gradle $gradleVersion..."
    if (!(Test-Path $zipPath)) {
        $downloadUrl = "https://services.gradle.org/distributions/gradle-$gradleVersion-bin.zip"
        [Net.ServicePointManager]::SecurityProtocol = [Net.SecurityProtocolType]::Tls12
        $curl = Get-Command curl.exe -ErrorAction SilentlyContinue
        if ($curl) {
            & $curl.Source -L --ssl-no-revoke --retry 5 --retry-delay 2 -o $zipPath $downloadUrl
            if ($LASTEXITCODE -ne 0) {
                if (Test-Path $zipPath) {
                    Remove-Item $zipPath -Force
                }
                throw "Gradle download failed."
            }
        } else {
            Invoke-WebRequest -Uri $downloadUrl -OutFile $zipPath
        }
    }

    Write-Host "Extracting Gradle..."
    Expand-Archive -Path $zipPath -DestinationPath $toolsDir -Force
}

Write-Host "Building Fabric and Forge jars with Java 17..."
& $gradleBat --no-daemon :fabric:build :forge:build
if ($LASTEXITCODE -ne 0) {
    throw "Build failed."
}

Write-Host ""
Write-Host "Done. Built jars are in:"
Write-Host "  fabric\build\libs"
Write-Host "  forge\build\libs"
