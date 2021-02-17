@echo off
REM Quick way to start h2 from intellij.

REM The h2 jar is populated via the maven dependency plugin to the $project\h2 folder.
REM This script will reference it via relative path links:  <this script location>\..\h2
REM The databases are all relative via the h2 folder.
REM We are starting the h2 server for tcp binding.  I guess we can still use file path binding
REM but I am not sure why you would want to do that, particularly if you are looking for that
REM shared database feel.

REM Location and name of the h2 jar.  Run mvn dependency:copy or mvn package to get the h2 jar copied over.
set h2-jar-path=%~dp0\..\h2
set h2-jar-name=h2-1.4.200.jar

REM The root folder for placing the h2 databases.  This is relevant when you
REM are referencing the databases via a tcp connection.  This allows you to
REM use tcp:\\localhost\<database name> as opposed to use a file path on the
REM host computer.
set h2-base-directory=%h2-jar-path%\databases

REM For convenience, I have add -ifNotExists which allows H2 to create an empty database, if someone references a
REM missing database.  This is very convenient for deleting databases and starting fresh.
@echo on
java -Dh2.bindAddress=127.0.0.1 -Duser.timezone=UTC -cp %h2-jar-path%\%h2-jar-name% org.h2.tools.Server ^
 -baseDir %h2-base-directory% ^
 -tcp ^
 -web -webAdminPassword password ^
 -browser ^
 -pg ^
 -ifNotExists
