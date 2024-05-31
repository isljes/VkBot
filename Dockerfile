FROM eclipse-temurin:21-jdk-jammy as build
WORKDIR /build
ARG JAR_FILE=target/VkBot.jar
ADD ${JAR_FILE} application.jar
RUN java -Djarmode= -jarlayertools application.jar extract --destination extracted

FROM eclipse-temurin:21-jdk-jammy
RUN addgroup spring-boot-group && adduser --ingroup spring-boot-group spring-boot
USER spring-boot:spring-boot-group
VOLUME /tmp
WORKDIR /application

COPY --from=build /build/extracted/dependencies .
COPY --from=build /build/extracted/spring-boot-loader .
COPY --from=build /build/extracted/snapshot-dependencies .
COPY --from=build /build/extracted/application .

ENTRYPOINT exec java ${JAVA_OPTS} org.springframework.boot.loader.JarLauncher ${0} ${@}



