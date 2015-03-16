#!/bin/sh
mvn clean 
mvn generate-sources
mvn compile
mvn datanucleus:enhance
mvn install -DskipTests