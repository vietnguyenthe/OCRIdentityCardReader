FROM java:8
EXPOSE 8989
ADD /target/ident.jar ident.jar
ENTRYPOINT ["java", "-jar", "ident.jar"]