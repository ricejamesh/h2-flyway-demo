@echo off
REM Quick way to see the h2 command-line parameters.

REM The h2 jar is populated via the maven dependency plugin to the $project\h2 folder.
REM This script will reference it via relative path links:  <this script location>\..\h2

REM Location and name of the h2 jar.  Run mvn dependency:copy or mvn package to have the h2 jar copied to the h2 folder.
set THIS_DIR=%~dp0
set h2-jar-path=%THIS_DIR%\..\h2
set h2-jar-name=h2-1.4.200.jar

echo.

java -cp %h2-jar-path%\%h2-jar-name% org.h2.tools.Server -?
echo.

