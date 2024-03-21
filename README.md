# LABORATORIO 6 - PATRONES ARQUITECTURALES

En este laboratorio, se construirá una aplicación web utilizando una arquitectura basada en microservicios y se desplegará en AWS utilizando EC2 y Docker. La aplicación constará de dos microservicios: un servicio MongoDB que almacenará las cadenas enviadas por el cliente web, y un servicio REST llamado LogService que recibirá las cadenas, las almacenará en la base de datos y responderá con un objeto JSON que incluya las 10 últimas cadenas almacenadas junto con las fechas en que fueron almacenadas.

El cliente web de la aplicación tendrá un campo de texto y un botón. Cada vez que el usuario envíe un mensaje, este se enviará al servicio REST. Para garantizar la escalabilidad y la distribución equitativa de la carga, el servicio REST implementará un algoritmo de balanceo de cargas de Round Robin, delegando el procesamiento del mensaje y la respuesta a cada una de las tres instancias del servicio LogService.
# Iniciando 
A continuación se indican una serie de instruciones para bajar y ejecutar el proyecto de manera exitosa:

Es **importante**❗tener instalado: 
- [MAVEN](https://maven.apache.org) : Manejo de las dependecias. 
- [GIT](https://git-scm.com) : Control de versiones.
- [JAVA](https://www.java.com/es/) : Lenguaje de programación (JDK 20).
- [DOCKER](https://www.docker.com/) : Contenedor.
- [AWS ACADEMI](https://awsacademy.instructure.com/): Para el uso de una máquina virtual.

# DockerHub
Se encuntra en el siguiente repositorio: [darkxs/arep-lab6](https://hub.docker.com/repository/docker/darkxs/arep-lab6/general)

 Pantallazo de cuando se subio al repositorio:
 ![imagen](https://github.com/SantiagoMelo0104/AREP-LAB6/assets/123812833/dfeecd9f-b5fc-4603-98e5-7f0a5f35a928)

 # Arquitectura 📄 
La arquitectura de este laboratorio consta de dos proyectos principales, cada uno con su propia estructura de paquetes y clases.

El primer proyecto [Service](https://github.com/SantiagoMelo0104/AREP-LAB6/tree/master/Service) tiene una clase llamada LogService en el paquete org.arep. Esta clase es la encargada de gestionar las peticiones HTTP GET en la ruta "/logservice" y almacenar los mensajes de log en una base de datos MongoDB. La conexión a la base de datos se realiza mediante el driver oficial de MongoDB para Java, y se utiliza la librería Gson para convertir los datos almacenados en JSON.

El segundo proyecto [ROUND_ROBIN](https://github.com/SantiagoMelo0104/AREP-LAB6/tree/master/ROUND_ROBIN) tiene dos clases en el paquete org.arep. La primera clase se llama App y es la encargada de gestionar las peticiones HTTP GET en la ruta "/log". Al recibir una petición, esta clase invoca al método getLogs de la clase RRInvoker para obtener los últimos 10 mensajes de log almacenados en la base de datos.

La segunda clase del segundo proyecto se llama RRInvoker y es la encargada de implementar un mecanismo de equilibrado de carga round-robin para distribuir las peticiones de log entre varios servidores. La clase mantiene una lista de URLs de los servidores de log y rota entre ellos cada vez que se realiza una petición. De esta forma, se distribuye la carga de trabajo entre los diferentes servidores y se evita sobrecargar a un único servidor.
![imagen](https://github.com/SantiagoMelo0104/AREP-LAB6/assets/123812833/33bc754c-3fce-4d17-8c92-e54663f3b148)

 
 # Diseño de clases 
**1. LogService:** Esta clase implementa el servicio de registro de mensajes en una base de datos MongoDB. La clase tiene un método main que inicializa el servidor web Spark y configura las rutas para manejar las solicitudes GET. El método logMessage es responsable de insertar un nuevo documento en la colección de logs en MongoDB y devolver los últimos 10 registros en formato JSON.

**2. App:** Esta clase implementa el punto de entrada principal del segundo proyecto. La clase tiene un método main que inicializa el servidor web Spark y configura las rutas para manejar las solicitudes GET. El método getLogs es invocado cuando se accede a la ruta "/log". Este método hace una solicitud HTTP GET a uno de los servidores de registro rotando entre ellos utilizando un algoritmo Round-Robin.

**3. RRInvoker:** Esta clase implementa un invocador de Round-Robin para realizar solicitudes HTTP GET a los servidores de registro. La clase mantiene una lista de URL de los servidores de registro y una variable currentServer para realizar un seguimiento del servidor actual al que se realizará la próxima solicitud. El método rotateRoundRobinServer devuelve la URL del siguiente servidor en la rotación. El método getLogs es responsable de realizar la solicitud HTTP GET al servidor de registro seleccionado y devolver la respuesta en forma de lista de cadenas.

 

# Instalación ⬇️ y Ejecución🪄
* Los siguiente comando le permitira clonar el repositorio de manera local:
  ~~~
  git clone https://github.com/SantiagoMelo0104/AREP-LAB6.git
  ~~~
* Entrar al directorio del proyecto con el siguiente comando:
   ~~~
   cd AREP-LAB6
   ~~~
* Compilar cada proyecto:
   ~~~
   cd ROUND_ROBIN
   mvn clean install
   ~~~

    ~~~
   cd Service
   mvn clean install
   ~~~
* Construir las imagenes de docker, cada uno en el directorio correpondiente:
   ~~~
   docker build --tag service .
   ~~~
  ~~~
  docker build --tag round .
   ~~~
![imagen](https://github.com/SantiagoMelo0104/AREP-LAB6/assets/123812833/a5205d8a-f308-4fe0-8394-24ee55d37f6a)

* Teniendo en cuenta que se está en el directorio donde se encuentra el docker compose se da lo siguiente:
  ~~~
  docker-compose up -d
  ~~~
  ![imagen](https://github.com/SantiagoMelo0104/AREP-LAB6/assets/123812833/eeddce6c-3c11-46e6-bc7a-b866eca4b7ef)



# Pruebas 
## Local 
Abrir en el navegador y colocar el path lo siguiente: 
~~~
http://localhost:4567/formulario.html
~~~
![imagen](https://github.com/SantiagoMelo0104/AREP-LAB6/assets/123812833/797c1e44-3827-47e2-b99b-36185bfd91b5)
![imagen](https://github.com/SantiagoMelo0104/AREP-LAB6/assets/123812833/6b4ebdcf-110a-4319-bffb-8b7fd592af3e)
![imagen](https://github.com/SantiagoMelo0104/AREP-LAB6/assets/123812833/d0f8841e-c006-4085-bbad-da7db2b2f26f)

## Local con Docker-Compose
![imagen](https://github.com/SantiagoMelo0104/AREP-LAB6/assets/123812833/eeddce6c-3c11-46e6-bc7a-b866eca4b7ef)
![imagen](https://github.com/SantiagoMelo0104/AREP-LAB6/assets/123812833/60fa4fee-b667-4863-a5cc-a9ab245830ed)
![imagen](https://github.com/SantiagoMelo0104/AREP-LAB6/assets/123812833/d65163e6-14e5-4034-9e2c-5c8b0c33e55b)

## AWS 
Abrir en el navegador y colocar el path lo siguiente: 
~~~
http://ec2-54-90-216-27.compute-1.amazonaws.com:4567/formulario.html
~~~
![Sin título](https://github.com/SantiagoMelo0104/AREP-LAB6/assets/123812833/0b157b84-0795-445c-b8e8-f3790f01880c)

![Sin título](https://github.com/SantiagoMelo0104/AREP-LAB6/assets/123812833/2e141515-33e3-4b31-ba88-51740d147bd3)

![imagen](https://github.com/SantiagoMelo0104/AREP-LAB6/assets/123812833/8f086146-f13c-415e-97dc-d128c9ab3bc8)


# Prueba del Despliegue:

  [VIDEO AWS](https://youtu.be/Y0iIeu5fBHY)

# Autor 
Santiago Naranjo Melo [SantiagoMelo0104](https://github.com/SantiagoMelo0104)

