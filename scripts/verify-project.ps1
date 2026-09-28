$ErrorActionPreference="Stop"
Write-Host "Checking project structure..."
$required=@("backend/pom.xml","frontend/package.json","docker-compose.yml","postman/Coaching-SaaS.postman_collection.json","docs/01-architecture.md")
foreach($p in $required){if(!(Test-Path $p)){throw "Missing $p"}}
Write-Host "Structure OK"
