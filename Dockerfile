FROM openjdk:8u232-jre-slim

# copy the fat jar and the run script to /opt

COPY target/imshaby-api-0.0.1-SNAPSHOT.war /opt/imshaby-api-0.0.1-SNAPSHOT.war
COPY run.sh /opt/run.sh
RUN chmod +x /opt/run.sh

# to start a docker container, use docker run /opt/run.sh <port>

CMD /opt/run.sh
