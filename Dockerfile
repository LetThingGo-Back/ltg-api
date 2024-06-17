FROM openjdk:17-alpine

ENV SPRING_PROFILES_ACTIVE=dev

COPY ./build/libs/letthinggo-server.jar app.jar

ENTRYPOINT ["java", "-jar", "app.jar"]