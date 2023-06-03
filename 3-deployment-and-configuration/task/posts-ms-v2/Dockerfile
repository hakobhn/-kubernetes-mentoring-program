# Login to docker hub: docker login -u hakobhn
# Build the image: docker build -t hakobhn/dockerdemo-posts:2.0.1 .
# Push image to docker hub: docker push hakobhn/dockerdemo-posts:2.0.1

FROM gradle:jdk11 AS build-stage
COPY --chown=gradle:gradle . /home/gradle
WORKDIR /home/gradle
RUN gradle build --no-daemon


FROM openjdk:11-jre-slim AS prod-stage
COPY --from=build-stage /home/gradle/build/libs/kubernetes-demo-posts-ms-*.jar /usr/local/lib/app.jar
EXPOSE 8080
ENTRYPOINT ["java","-jar","/usr/local/lib/app.jar"]
