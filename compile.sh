#!/bin/sh
mvn3 clean 
mvn3 generate-sources
mvn3 compile
mvn3 datanucleus:enhance
mvn3 install -DskipTests