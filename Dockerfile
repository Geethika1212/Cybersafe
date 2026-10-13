
FROM tomcat:10.1-jdk17

RUN rm -rf /usr/local/tomcat/webapps/ROOT

COPY . /usr/local/tomcat/webapps/cyberweb

RUN sed -i 's/port="8080"/port="10000"/' /usr/local/tomcat/conf/server.xml

EXPOSE 10000

CMD ["catalina.sh", "run"]
