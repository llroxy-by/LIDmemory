$ErrorActionPreference = "Stop"
$root = Split-Path -Parent $MyInvocation.MyCommand.Path
Set-Location $root

Write-Host "===== LIDmemory - GitHub Upload ====="
Write-Host ""

if (!(Test-Path .gitignore)) {
    Set-Content .gitignore ".gradle/`nbuild/`n.tmp/`n.gradle-tools/`nHOW_TO_BUILD.txt`n.idea/"
}

Remove-Item common\src\main\java\com\lidmemory\LIDMemoryBlocks.java -ErrorAction SilentlyContinue
Remove-Item common\src\main\resources\assets\lidmemory\models\item\lroxy_relic.json -ErrorAction SilentlyContinue

git init
git add -A
git commit -m "LIDmemory beta0.1"

Write-Host ""
Write-Host "===== Commit OK ====="
Write-Host ""
Write-Host "Next: Create a GitHub repo at https://github.com/new"
Write-Host "  Suggested name: LIDmemory"
Write-Host "  Do NOT check README / .gitignore / license"
Write-Host ""

$repoUrl = Read-Host "Paste GitHub repo URL (e.g. https://github.com/USER/LIDmemory.git)"

if ($repoUrl -and $repoUrl -ne "") {
    git remote add origin $repoUrl
    git branch -M main
    git push -u origin main
    Write-Host ""
    Write-Host "Upload done!"
}
else {
    Write-Host "Skipped push. Run manually later."
}