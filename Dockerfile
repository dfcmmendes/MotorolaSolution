FROM tomcat:8.0.20-jre8

COPY /target/MotorolaSolution.war /usr/local/tomcat/webapps/motorola.war
