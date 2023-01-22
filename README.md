# 6GAG9

Hemos diseñado un proyecto con la idea de generar una red social dedicada a los memes. Con esto nuestro objetivo es que la gente tenga un lugar donde evadirse de los problemas cotidianos, así como evitar toda la presión que genera instagram y redes similares al ver la vida “perfecta” de los usuarios. Para conseguir esto un administrador deberá aprobar las publicaciones de manera previa evitando que se comparta aquello que no esté relacionado con el tema

# Tecnologías usadas

Con el fin de desarrollar este proyecto nos hemos ayudado de una serie de tecnologías entre las que encontramos: HTML5, CSS, Maven, Java, API REST, JSP, Servlet, JS, Bootstrap. Utilizando para ello el entorno de Eclipse con la suite de Spring instalada.

### Motivo
##### SWEETALERT2
  Hemos utilizado esta tecnologia para los dialogos por que despues de haber comparado distintas herramientas hemos considerado qu ela que nos aportaba los dialosgos de una manera mas estetica a nuestro juicio era esta
##### BOOSTRAP5
  Hemos utilizado esta tecnologia para relaizar las vistas por que es una herramienta muy facil y con una comunidad muy activa
##### SPRING
  Hemos utilizado esta tecnologia para la ap rest por que es la herramienta mas novedosa y por lo tanto la mas completa y simple
##### HIBERNATE
  Hemos utilizado esta tecnologia para la persistencia de la informacion por que es la herramienta mas utilizada a nivel empresarial
##### JPA
  Hemos utilizado esta tecnologia para la conexion con la base de datos por que la compatibilidad con hibernate es muy alta
##### JQUERY
  Hemos utilizado esta tecnologia para mejorar la funcionalidad del JavaScript por que las interaccines y la facilidad que aportan al js hacen de esta una herramienta muy util
##### HTML5
  Hemos utilizado esta tecnologia para la maquetacion web por que es el lenguaje de etiquetas mas actual y por lo tamto a nuestro juicio el que deberia utilizarse
##### Servlet
  Hemos utilizado esta tecnologia para utilizar codigo java y html en una misma clase por que nos parecia la forma correcta y mas simple de ralizarlo

## Proyectos

### Modelo

Este proyecto se usará para generar un modelo de datos del usuario, las imágenes y los likes así como el dao referente a ellos en la que encontraremos las diferentes consultas a base de datos.

### Rest

En esta capa creamos los métodos que llaman al dao para que terceras personas puedan hacer uso de esta información.

### Web

En esta capa enlazamos la visual de la web con el modelo de datos para realizar un acceso a datos controlado desde la vista

### Web-Rest

En esta parte a diferencia de la web hacemos la conexión a la rest en vez de al modelo de datos.

## Configuración bbdd

No hace falta crear las tablas ya que lo genera de manera automática gracias a Hibernate. Pero si necesitaremos crear la base de datos llamada 6gag9.
Disponemos de tres tablas que son: usuarios, publicación y likes
usuarios (1:N) publicacion
usuarios (1:N) likes
publicacion (1:N) likes

![diagrama de la base datos](/documentacion/bbdd.PNG)

## Instalación

Para realizar la instalacion es necesario tener el entorno de Eclipse con la suite de Spring Tools, tambien es necesario tener el XAMPP para activar la conexion de bbdd y tener el apache Tomcat instalado. Para obtener esta aplicacion descargaremos el zip desde el [github](https://github.com/josumc/6gag9/)

PD: Se debe crear una BBDD con el nombre 6gag9 y importar el [SQL](https://github.com/josumc/6gag9/blob/main/JUEGO_DE_DATOS.SQL)

![Descarga del zip](/documentacion/zip.PNG)

Cuando hayamos desargado el zip lo descomprimimos y lo importamos File>Import>Maven Project>../model-jpa una vez importado hacemos un Run As>Maven Install y una vez completado importamos de la misma manera el proyecto web y lo añadimos al tomcat. Al finalizar el proceso podemos ver la pagina buscando localhost:8080/6gag9-servlet/Inicio 
A la hora de iniciar el despliegue en caso de dar un error tenemos que abrir el properties del proyecto y en target runtimes debemos añadir un server propio, en la seccion ui-statics/js marca un fallo pero debemos omitirla.

Ahora para instalar la rest la importamos como se ha explicado para los otros proyectos y ejeccutamos la opcion Run us> Spring Boot Application. Para ver la web la importamos a un servidor web, en este caso elegimos con el xampp en la carpeta htdocs cremos una carpeta de nombre 6gag9 y copiamos el archivo index.html que encontraremos en la carpeta Rest-front de este mismo repositorio y buscando en cualquier navegador localhost/6gag9 veremos la web (sustituimos 6gag9 por el nombre de la carpeta que hayamos usado). Finalmente para ver los datos de la api-rest usaremos la url localhost:8080/swagger-ui.html

## Diagrama de despliege
![diagrama de la base datos](/documentacion/despliege.PNG)
