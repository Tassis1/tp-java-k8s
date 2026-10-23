FROM quay.io/wildfly/wildfly:latest-jdk17
USER root
ADD https://repo1.maven.org/maven2/org/postgresql/postgresql/42.7.4/postgresql-42.7.4.jar /tmp/postgresql.jar
RUN chmod 644 /tmp/postgresql.jar
USER jboss
COPY configure-ds.cli /tmp/configure-ds.cli
RUN /opt/jboss/wildfly/bin/jboss-cli.sh --file=/tmp/configure-ds.cli
COPY target/tp-backend.war /opt/jboss/wildfly/standalone/deployments/ROOT.war
EXPOSE 8080