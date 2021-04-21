## CancunSleep
A demo API of unique hotel in Cancùn. 

## Motivation
It has been developed for Alten recruitment test.

## Build status
[![js-standard-style](https://img.shields.io/badge/Build-OK-brightgreen)]() 
[![js-standard-style](https://img.shields.io/badge/Execution-OK-brightgreen)]() 

## Tech/framework used

<b>Built with :</b>
- [Java 8](https://www.oracle.com/java/technologies/java8.html)
- [SpringBoot 1.4.1](https://spring.io/blog/2021/03/18/spring-boot-2-4-4-available-now)
- [Maven 3.6.1](https://maven.apache.org/docs/3.6.1/release-notes.html)
- [Hibernate-core 5.4.29.Final](https://hibernate.org/orm/releases/5.4/)
- [Postgresql 10](https://www.postgresql.org/about/news/postgresql-10-released-1786/)

## Features
- Check the room availability, place a reservation, cancel or modify it.
- CRUD on Room
- CRUD on Innkeeper
- CRUD on Client
- Automatic transparent real time CRUD on Date

## Installation
=> Backend: From root folder, execute this command: 'mvn spring-boot:run'.

=> Database:
 -create an empty database named 'cancunsleepdb' on a Postgresql 10 running server on port 5432
 -import the database file cancunsleep.sql by executing the following command from pgadmin.exe folder : 
 'psql -h hostname -d databasename -U username -f file.sql'
 -make sure database credentials are right on application.properties file.
    
## API Reference
Rest API

## Tests
- Firstly, import the database 'cancunsleep': shared in the project. 
- Run this backend application by executing: 'mvn spring-boot:run'.
- Simulate Http requests with postman: import Postman collections shared in the project.

## Contribute
[Want to contribute?](https://github.com/adamoubello/cancunsleep.git) 

## Credits
[Java Time API 8](https://docs.oracle.com/en/java/javase/11/docs/api/java.base/java/time/package-summary.html) 

## License
Version 1.0

ALTEN CANADA © [Bello Adamou](https://adamoubello.com)