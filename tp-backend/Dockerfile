FROM quay.io/wildfly/wildfly:latest-jdk17
ADD --chmod=644 https://repo1.maven.org/maven2/org/postgresql/postgresql/42.7.4/postgresql-42.7.4.jar /tmp/postgresql.jar
COPY configure-ds.cli /tmp/configure-ds.cli
RUN /opt/jboss/wildfly/bin/jboss-cli.sh --file=/tmp/configure-ds.cli
COPY target/tp-backend.war /opt/jboss/wildfly/standalone/deployments/ROOT.war
EXPOSE 8080