FROM tomcat:10.1-jdk17

RUN rm -rf /usr/local/tomcat/webapps/ROOT

COPY . /usr/local/tomcat/webapps/cyberweb

EXPOSE 8080

CMD ["catalina.sh", "run"]