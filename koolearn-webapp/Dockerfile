FROM frolvlad/alpine-oraclejdk8:slim
ARG module
ARG jenkins_build_path
#VOLUME /tmp
COPY $jenkins_build_path/$module-0.0.1-SNAPSHOT.jar /app.jar
RUN ls -lh /
RUN sh -c 'touch /app.jar'
ENV JAVA_OPTS=""
ENTRYPOINT [ "sh", "-c", "java $JAVA_OPTS -Djava.security.egd=file:/dev/./urandom -jar /app.jar" ]
