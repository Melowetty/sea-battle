FROM eclipse-temurin:21-jdk-jammy AS build

WORKDIR /app

COPY build.gradle.kts settings.gradle.kts gradlew ./
COPY gradle ./gradle
COPY src ./src

COPY api ./api
COPY data ./data
COPY game-core ./game-core
COPY security ./security
COPY match-making ./match-making

RUN ./gradlew dependencies --no-daemon
COPY src /app/src

RUN ./gradlew build --no-daemon -x test

FROM eclipse-temurin:21-jre-jammy

WORKDIR /app

COPY --from=build /app/build/libs/*.jar /app/app.jar

EXPOSE 8080

ENTRYPOINT ["sh", "-c", "java -jar /app/app.jar"]