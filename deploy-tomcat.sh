#!/bin/sh
mvn clean verify
echo "Copying cost-manager-services.war into Tomcat's webapps folder"
cp -f services/target/cost-manager-services.war $CATALINA_HOME/webapps