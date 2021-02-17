# h2-flyway-demo
Simple project for exercising h2, flyway, spring-data, and jpa.

## Description

This simple project will write an entry to a database table each time it is run.  By default, it
is using an H2 database running at localhost.  The application tables are located in a schema 
named 'application'.

## Getting started

The spring-application is referencing a h2 server running @ tcp://localhost:9202,
which will initialize an empty database, if one is not present.  There are two scripts in the
[./h2-scripts] for starting the h2 jar as a server or just looking at command-line options.  

The h2 jar is expected to be located at ./h2/h2-1.4.200.jar and will be automatically copied there by the maven
dependency plugin when you use `mvn dependency:copy` or `mvn package`

The [./h2-scripts/h2-server.bat] file, will start the server.

The unit test is using an in-memory h2 database by default and references a different db name.
