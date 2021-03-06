#!/bin/sh
mvn verify
echo "Copying cost-manager-web-services.war into Tomcat's webapps folder"
rm -rf ${CATALINA_HOME}/webapps/cost-manager-web-services
cp -f web-services/target/cost-manager-web-services.war ${CATALINA_HOME}/webapps
echo "Copying cost-manager-client.war into Tomcat's webapps folder"
rm -rf ${CATALINA_HOME}/webapps/cost-manager-client
cp -f client/target/cost-manager-client.war ${CATALINA_HOME}/webapps