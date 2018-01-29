FROM docker.hzbox.net/common/openjdk:8-jre-alpine

VOLUME /tmp
ADD target/journal-service.jar /journal-service.jar
ENV JAVA_OPTS=""

CMD java $JAVA_OPTS -Djava.security.egd=file:/dev/./urandom -jar /journal-service.jar