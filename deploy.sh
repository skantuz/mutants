#!/bin/bash
echo limpiando versiones anteriores
docker rm mutants-api
docker rm mutants_mongodb
docker image rm deployment_mutants-api
docker volume rm mongodb
rm ./deployment/app.jar
/bin/sh ./gradlew clean
echo compilando
/bin/sh ./gradlew build
mv ./app/build/libs/app.jar ./deployment/app.jar
echo Creando Imagen de docker
cd deployment
docker-compose build --no-cache
echo Desplegando contenedores api y mongo
docker-compose up
echo Eliminando deployment
cd ..
docker rm mutants-api
docker rm mutants_mongodb
docker image rm deployment_mutants-api
docker volume rm mongodb
rm ./deployment/app.jar
