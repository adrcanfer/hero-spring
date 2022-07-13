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
  - GithubActions

### Servicios expuesto
Esta API expone 5 servicios que nos permiten operar con los Heroes almacenados. Estos servicios nos permiten:
  - Obtener el listado completo de heroes y filtrar por nombre.
  - Obtener el detalle de un heroe
  - Crear un heroe
  - Modificar un heroe
  - Eliminar un heroe
  - ¿¿EXPORTAR??

### Spring Web
Se ha utilizado Spring Web para la implementación de los servicios REST.

### Spring Security
Esta dependencia ha sito utilizada para securizar la aplicación. Se hace implementado una autorización básica (Basic Auth) y para ello se han definido los siguientes 3 usuarios y contraseñas:

  - admin/admin: Con permisos de escritura y lectura.
  - read/read: Con permisos de lectura.
  - write/write: Con permisos de escritura.

### Spring Data
Ha sido utilizada para operar con la BBDD. Con esta dependencia hemos mapeado los registros de la BBDD a objetos Java y viceversa. Además, haciendo uso de Hibernate operamos con la BBDD.

### Docker
Se ha incluido un fichero Dockerfile. 
Para generar la imagen se debe lanzar el siguiente comando proprocionando la ruta al jar como argumento:
```sh
docker build -t adrcanfer/hero --build-arg JAR_FILE=./hero-0.0.1-SNAPSHOT.jar .
```
Para lanzar un nuevo contenedor con esta imagen es necesario lanzar el siguiente comando:
```sh
docker run -p 8080:8080 adrcanfer/hero
```

## Calidad
![Coverage](.github/badges/jacoco.svg)


### Reference Documentation
For further reference, please consider the following sections:

* [Official Apache Maven documentation](https://maven.apache.org/guides/index.html)
* [Spring Boot Maven Plugin Reference Guide](https://docs.spring.io/spring-boot/docs/2.7.1/maven-plugin/reference/html/)
* [Create an OCI image](https://docs.spring.io/spring-boot/docs/2.7.1/maven-plugin/reference/html/#build-image)
* [Spring Data JPA](https://docs.spring.io/spring-boot/docs/2.7.1/reference/htmlsingle/#data.sql.jpa-and-spring-data)
* [Spring Web](https://docs.spring.io/spring-boot/docs/2.7.1/reference/htmlsingle/#web)

### Guides
The following guides illustrate how to use some features concretely:

* [Accessing Data with JPA](https://spring.io/guides/gs/accessing-data-jpa/)
* [Building a RESTful Web Service](https://spring.io/guides/gs/rest-service/)
* [Serving Web Content with Spring MVC](https://spring.io/guides/gs/serving-web-content/)
* [Building REST services with Spring](https://spring.io/guides/tutorials/rest/)

