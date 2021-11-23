
FROM maven:3-jdk-11-slim
WORKDIR /app
ADD pom.xml pom.xml
ADD src src
RUN mvn package && mv target/*.jar ./app.jar
ENTRYPOINT ["java","-cp","app.jar","cc.momas.docker.dockerfile.DockerfleApplication"]