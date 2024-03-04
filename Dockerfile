FROM openjdk:17-alpine as build
LABEL maintainer="aclij"
ARG JAR_FILE=/target/configserver-0.0.1-SNAPSHOT.jar
COPY ${JAR_FILE} app.jar
RUN mkdir -p target/dependency && (cd target/dependency; jar -xf /app.jar)

FROM openjdk:17-alpine
VOLUME /tmp

ARG DEPENDENCY=/target/dependency
COPY --from=build ${DEPENDENCY}/BOOT-INF/lib /app/lib
COPY --from=build ${DEPENDENCY}/META-INF /app/META-INF
COPY --from=build ${DEPENDENCY}/BOOT-INF/classes /app


ENTRYPOINT ["java","-cp","app:app/lib/*", "ru.pio.aclij.configserver.ConfigserverApplication"]