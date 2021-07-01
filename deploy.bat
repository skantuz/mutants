@ECHO OFF
mode 120,50
ECHO limpiando versiones anteriores
docker rm mutants_api
docker rm mutants_mongodb
docker image rm deployment_mutants_api
del .\deployment\app.jar
call .\gradlew clean
ECHO compilando
call .\gradlew build
move .\app\build\libs\app.jar .\deployment\app.jar
ECHO Creando Imagen de docker
cd deployment
docker-compose build --no-cache
ECHO Desplegando contenedores api y mongo
docker-compose up
ECHO Eliminando deployment
cd ..
docker rm mutants_api
docker rm mutants_mongodb
docker image rm deployment_mutants_api
del .\deployment\app.jar
