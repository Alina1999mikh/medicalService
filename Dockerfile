FROM gradle:7.4-jdk-alpine AS TEMP_BUILD_IMAGE
COPY $PWD .
RUN ./gradlew build

FROM openjdk:17-jdk-alpine
COPY --from=TEMP_BUILD_IMAGE /home/gradle/build/libs/medical-service-0.0.1-SNAPSHOT.jar /
ENTRYPOINT exec java -jar medical-service-0.0.1-SNAPSHOT.jar
