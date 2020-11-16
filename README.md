# hospital_prueba_dharbor
Código de front y back para prueba de Digital Harbor

====================================
===PEQUEÑO MANUAL DE INSTALACION====
====================================
Para poder levantar la aplicación tendremos dos opciones, la primera el levantar mediante comando desplegando tanto el backen que está hecho en JAVA, y el frontend que está hecho en Angular.
Para ambos casos necesitamos cargar la base de datos.

=(1)= CARGAR BASE DE DATOS MYSQL==
Para cargar  la estructura e información previamente populada, se requerirá correr el archivo script "estructura_data.sql" que esta en la carpeta "scripts" del repositorio de nuestro proyecto.
Cabe recalcar que la base de datos es MYSQL.


=(2)= INICIAR EL BACKEND ==
***Opción 1 por código***
1.- Dirigirse al archivo "backend\src\main\resources\application.properties" y modificar los siguientes valores para que corresponda a los valores de conexión de tu base de datos.
db.url=jdbc:mysql://localhost:3306/TU_BASE_DE_DATOS?useUnicode........................
db.username=TU_USERNAME_DB
db.password=TU_PASSWORD_DB

2.-Por consola Ir a la ruta de nuestro proyecto en ingresar a la carpeta "codigo/backend", que es donde esta nuestro proyecto MAVEN.
ejecutar: 		mvn clean install

3.-Una vez que termine se genera una carpeta llamada "target", ingresar a esa carpeta y ejecutar el comando.
ejecutar: 		java -jar hospital-0.0.1.jar

4.-Nuestra aplicación springboot java levantara en el puerto 9090 y con esto estaría disponible para que sea usado por el frontend.

***Opción 2 aplicación ya compilada***
1.- En el repositorio ya tenemos el JAR generado y esta en la carpeta "aplicacion/backend/hospital-0.0.1.jar", cabe recalcar que se tendria que tener los siguientes datos de base de datos a conectarse.
DB_NOMBRE:hospital_dh
DB_USERNAME:root
DB_PASSWORD:12345

2.-Ejecutar el comando.
		java -jar hospital-0.0.1.jar
3.-Nuestra aplicación springboot java levantara en el puerto 9090 y con esto estaría disponible para que sea usado por el frontend.

=(3)= INICIAR EL FRONTEND ==
***Opción 1 por código***
1.- Instalamos todas las dependencias de nuestro frontend con el comando,para esto nos vamos a la carpeta "codigo/frontend".
ejecutar:	npm install

2.-Una vez instalada nuestras dependencias levantamos nuestra aplicación con el siguiente comando desde la misma carpeta.
ejecutar:	ng serve

***Opción 2 aplicación ya compilada***
1.- Copiamos todo el contendió de "aplicacion/frontend/" a una carpeta que este alojado en algún servidor de aplicaciones, por ejemplo Apache.
2.- Según como este configurado el servidor de aplicaciones se podrá acceder al "index.html" de nuestro frontend.


