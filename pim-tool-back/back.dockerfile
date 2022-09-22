FROM maven
WORKDIR /server
COPY /target/*.war .
CMD java -jar *.war