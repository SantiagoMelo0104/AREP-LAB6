FROM openjdk:20

WORKDIR /usrapp/bin


COPY /target/classes /usrapp/bin/classes
COPY /target/dependency /usrapp/bin/dependency

ENV PORT 4567


CMD ["java","-cp","./classes:./dependency/*", "org.arep.App"]