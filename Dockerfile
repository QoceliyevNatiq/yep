FROM openjdk:27-ea-oraclelinux9
ADD target/yep-0.0.1-SNAPSHOT.jar app.jar
LABEL authors="Natiq"

ENTRYPOINT ["top", "-jar", "app.jar"]