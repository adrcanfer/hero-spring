# Heroes: Aplicación de prueba con Spring Boot

### Introducción
Este proyecto es una api RESTful haciendo del Framework de Spring Boot.
En este proyecto se ha hecho uso de las siguientes tecnologías:
  - Spring Web
  - Spring Security
  - Spring Data
  - Spring AOP
  - Spring Caché
  - H2
  - OpenAPI
  - Docker
  - Kubernetes
  - GithubActions
  - Heroku

### Servicios expuesto
Esta API expone 5 servicios que nos permiten operar con los Heroes almacenados. Estos servicios nos permiten:
  - Obtener el listado completo de heroes y filtrar por nombre.
  - Obtener el detalle de un heroe
  - Crear un heroe
  - Modificar un heroe
  - Eliminar un heroe

### Spring Web
Se ha utilizado Spring Web para la implementación de los servicios REST.

### Spring Security
Esta dependencia ha sito utilizada para securizar la aplicación. Se hace implementado una autorización básica (Basic Auth) y para ello se han definido los siguientes 3 usuarios y contraseñas:

  - admin/admin: Con permisos de escritura y lectura.
  - read/read: Con permisos de lectura.
  - write/write: Con permisos de escritura.

### Spring Data
Ha sido utilizada para operar con la BBDD. Con esta dependencia hemos mapeado los registros de la BBDD a objetos Java y viceversa. Además, haciendo uso de Hibernate operamos con la BBDD.

### Spring AOP
Esta dependencia ha sido utilizada para el desarrollo del aspecto que muestra el tiempo de ejecución de un método que se encuentra anotado con la etiqueta llamada '@TimeLogger'.

### Spring Caché
Usada para cachear a los heroes. Se ha introducido un delay en el método que obtiene los heroes de la BBDD para simular carga en la BBDD. Se puede apreciar como durante la primera llamada el servicio tarda más de 3s en responder y las demás llamadas, ese tiempo se reduce al orden de milisegundos.

### H2
Es la BBDD en memoria que se ha usado para resolver este problema.

### Open API
Se ha usado para la documentación de las APIs expuestas. Cuando la aplicación se encuentre levantada, se puede acceder a la documentación a través de la siguiente URL: http://localhost:8080/swagger-ui/index.html

### Docker
Se ha incluido un fichero Dockerfile. 
Para generar la imagen se debe lanzar el siguiente comando proprocionando la ruta al jar como argumento:
```sh
docker build -t hero --build-arg JAR_FILE=${PATH_DEL_JAR} .
```
Para lanzar un nuevo contenedor con esta imagen es necesario lanzar el siguiente comando:
```sh
docker run -p 8080:8080 hero
```

### Kubernetes
En la carpeta ./k8s se encuentran los siguientes ficheros:
  - hero-deploy.yaml: Fichero con el cual se puede crear un deployment en kubernetes con dos réplicas de la imagen docker que se ha creado en el paso anterior. Es impresindible que la imagen docker generada mantenga el mismo nombre en registry local ya que es de ahí donde se busca la imagen
  - hero-service.yaml: Fichero con el cual se crea un servicio de tipo NodePort para este despliegue.

### Github Actions
Se ha implementado una acción en Github para que cuando se realice un commit en el repositorio, se ejecuten los tests unitarios.

### Heroku
La aplicación se encuentra desplegada en Heroku y es accesible a través de la siguiente URL: https://adrcanfer-hero.herokuapp.com
Se puede acceder a la documentación de la API a través de la siguiente URL: https://adrcanfer-hero.herokuapp.com/swagger-ui/index.html
