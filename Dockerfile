FROM eclipse-temurin:21-jdk-alpine

LABEL maintainer = "mohammedfurkhan9@gmail.com"

ADD target/springboot-blog-rest-api-0.0.1-SNAPSHOT.jar springboot-docker.jar

ENTRYPOINT ["java", "-jar", "springboot-docker.jar"]