FROM eclipse-temurin:21-jdk-alpine
COPY /target/*.jar /opt/app/magicview.jar
LABEL maintainer="adevalter@gmail.com"
ENTRYPOINT ["java","-jar","/opt/app/magicview.jar"]
EXPOSE 8080