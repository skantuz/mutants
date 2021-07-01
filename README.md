# Mutants - Validation
Para desplegar el servicio se requiere docker y docker-compose, se debe ejecutar según el sistema operativo

####windows
```sh
.\deploy.bat
```
####Linux
```sh
sh deploy.sh
```

####Despliegue manual 
```
./gradlew clean build
cp ./app/build/libs/app.jar ./deployment/app.jar
cd deployment
docker-compose build --no-cache
docker-compose up
```

##Descripcion del Servicio

Aplicación desarrollada en java 11 con gradle 7.1 con sub proyectos para diseño en arquitectura limpia

###estructura de carpetas
```
📦mutants-app
 ├─📂deployment        // Contiene los archivos necesarios para el desplegar el servicio con Docker
 │  📜Dockerfile           // Archivo usuado para crear imagen de Docker del servicio
 │  📜docker-compose.yaml  // Archivo usuado para crear imagen de Docker del servicio
 │  📜aplication.yaml      // Archivo de configuracion para la app
 │  📜mutant_schema.son    // Archivo de validacion para el body correcto
 ├─📂app               // Contienen la aplicacion de inicio del servicio
 │ └─📂config              // Creaciones de Beans para el servicio
 ├─📂domain            // Nucleo de la aplicacion basada en necesidades del negocio
 │ ├─📂model  
 │ ├─📂Repository         // Repositorios para instanciar los driven adapters
 │ └─📂usecase            // Casos de uso de los repositorios
 ├─📂gradle
 │ └─📂wrapper
 │     📜gradle-wrapper.jar
 │     📜gradle-wrapper.properties
 ├─📂buildSrc         // Archivos de configuracion central del gradle 7.1
 ├─📂driven-adapters  // Conexiones a bases de datos otras apis segun lo requerido deben implementar repositorios del dominio
 │ ├─📂identity_mutants  // Valida Dna
 │ └─📂mongodb_mutants   // almacena en base de datos mongodb y consulta 
 └─📂entry-points     // puntos de entra a la app
   └─📂api_rest       //subcomponente de api rest
```