#!/bin/bash

mvn clean package -DskipTests

set -e

APP_NAME="springboot3-performance-simulation-api"
IMAGE_NAME="springboot3-performance-simulation-api-image:latest"

echo "Garantindo que a pasta ./dumps existe..."
mkdir -p ./dumps

echo "Construindo imagem Docker..."
docker build -f build-image-for-dev.dockerfile -t $IMAGE_NAME .

echo "Removendo container antigo (se existir)..."
docker rm -f $APP_NAME 2>/dev/null || true

echo "Subindo container com docker-compose..."
docker-compose up -d

echo "Aplicação $APP_NAME rodando em background!"
echo "Acesse em https://localhost:443"
echo "Monitor dumps: ./dumps/heapdump.hprof e threaddump-*.txt"