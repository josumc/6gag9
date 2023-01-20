# 6GAG9

Hemos diseñado un proyecto con la idea de generar una red social dedicada a los memes. Con esto nuestro objetivo es que la gente tenga un lugar donde evadirse de los problemas cotidianos, así como evitar toda la presión que genera instagram y redes similares al ver la vida “perfecta” de los usuarios. Para conseguir esto un administrador deberá aprobar las publicaciones de manera previa evitando que se comparta aquello que no esté relacionado con el tema

# Tecnologías usadas

Con el fin de desarrollar este proyecto nos hemos ayudado de una serie de tecnologías entre las que encontramos: HTML5, CSS, Maven, Java, API REST, JSP, Servlet, JS, Bootstrap. Utilizando para ello el entorno de Eclipse con la suite de Spring instalada

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

![diagrama de la base datos](/bbdd.PNG)
