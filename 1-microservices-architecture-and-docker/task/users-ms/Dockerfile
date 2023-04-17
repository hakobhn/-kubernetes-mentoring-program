# Login to docker hub: docker login -u hakobhn -p *******
# Login to hub: docker login -u hakobhn -p PiramidaNanoelq1980
# Build the image: docker build -t hakobhn/dockerdemo:1.0.0 .
# Push image to docker hub: docker push hakobhn/dockerdemo:1.0.0

FROM gradle:jdk11 AS build-stage
COPY --chown=gradle:gradle . /home/gradle
WORKDIR /home/gradle
RUN gradle build --no-daemon


FROM openjdk:11-jre-slim AS prod-stage
COPY --from=build-stage /home/gradle/build/libs/docker-demo-users-ms-*.jar /usr/local/lib/app.jar
EXPOSE 8080
ENTRYPOINT ["java","-jar","-Dspring.profiles.active=prod","/usr/local/lib/app.jar"]
