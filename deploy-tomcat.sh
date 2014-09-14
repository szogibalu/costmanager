#!/bin/sh
mvn clean verify
echo "Copying cost-manager-services.war into Tomcat's webapps folder"
cp -f services/target/cost-manager-services.war ${CATALINA_HOME}/webapps
echo "Copying cost-manager-client.war into Tomcat's webapps folder"
cp -f client/target/cost-manager-client.war ${CATALINA_HOME}/webapps