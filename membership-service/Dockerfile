FROM amazoncorretto:17.0.7-al2023-headless
EXPOSE 8080

#membership-service/build/libs/membership-service-0.0.1-SNAPSHOT.jar ./APP.jar
#"-Djava.security.egd=file:/dev/./urandom",
#"-Dspring.profiles.active=prod",
ENV JAVA_HOME=/opt/java/openjdk
ENV PATH "${JAVA_HOME}/bin:${PATH}"

ENV TZ=Asia/Seoul
ENV LC_ALL=C.UTF-8

COPY ./build/libs/membership-service-0.0.1-SNAPSHOT.jar ./APP.jar

CMD ["java", "-jar", "APP.jar"]