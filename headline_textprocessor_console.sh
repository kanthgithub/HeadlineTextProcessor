#!/bin/sh

mvn -e org.apache.maven.plugins:maven-clean-plugin:2.5:clean org.apache.maven.plugins:maven-compiler-plugin:3.6.1:compile org.apache.maven.plugins:maven-compiler-plugin:3.6.1:testCompile org.apache.maven.plugins:maven-surefire-plugin:2.12.4:test org.apache.maven.plugins:maven-jar-plugin:2.4:jar org.apache.maven.plugins:maven-install-plugin:2.4:install
mvn spring-boot:run
