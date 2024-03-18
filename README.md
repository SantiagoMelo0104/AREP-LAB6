LABORATORIO 6 - PATRONES ARQUITECTURALES

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
 # Arquitectura 📄 
 
 # Diseño de clases 

# Instalación ⬇️
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

# Ejecución🪄

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

  [VIDEO AWS]()

# Autor 
Santiago Naranjo Melo [SantiagoMelo0104](https://github.com/SantiagoMelo0104)

