FROM amazoncorretto:17-alpine-jdk
MAINTAINER Xen0
COPY target/onex-0.0.1-SNAPSHOT.jar onex-app.jar
ENTRYPOINT ["java", "-jar", "/onex-app.jar"]