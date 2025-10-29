FROM eclipse-temurin:17-jdk

EXPOSE 8080

ADD build/libs/forum-api-0.0.1-SNAPSHOT.jar app.jar

ENTRYPOINT ["java", \
    "-XX:+UseContainerSupport", \
    "-Xmx300m", \
    "-Xss512k", \
    "-XX:CICompilerCount=2", \
    "-Dserver.port=${PORT}", \
    "-Dspring.profiles.active=prod", \
    "-jar", "app.jar"]
