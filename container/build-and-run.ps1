# Variáveis
$AppName = "springboot3-performance-simulation-api"
$ImageName = "springboot3-performance-simulation-api-image:latest"
$DumpPath = "C:/TEST_DUMPS"

# Compila o projeto
Write-Host "Compilando o projeto com Maven..."
cd ..; mvn clean package -DskipTests; cd container

# Garante diretório de dumps
Write-Host "Garantindo que a pasta $DumpPath existe..."
if (!(Test-Path $DumpPath)) {
    New-Item -ItemType Directory -Path $DumpPath | Out-Null
}

# Constrói a imagem
Write-Host "Construindo imagem Docker..."
docker build -f build-image-for-dev.dockerfile -t $ImageName ..

# Remove container antigo se existir
Write-Host "Removendo container antigo (se existir)..."
docker rm -f $AppName 2>$null | Out-Null

# Sobe o container
Write-Host "Subindo container com docker-compose..."
#docker-compose up -d
docker-compose -f docker-compose.yml up -d

# Status final
Write-Host "Aplicação $AppName rodando em background!"
Write-Host "Acesse: https://localhost:443"
Write-Host "Monitor dumps: $DumpPath"