FROM openjdk:11
VOLUME /tmp
ARG JAR_FILE
COPY ${JAR_FILE} hero.jar
ENTRYPOINT ["java","-jar","/hero.jar"]
