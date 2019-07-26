FROM java:8
EXPOSE 8282
ADD /target/ident.jar ident.jar
ENTRYPOINT ["java", "-jar", "ident.jar"]