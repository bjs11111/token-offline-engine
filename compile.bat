call mvn clean 
call mvn generate-sources
call mvn compile
call mvn datanucleus:enhance
call mvn install