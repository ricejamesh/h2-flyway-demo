@echo off
REM Quick way to start h2 from intellij.  Only if it is not already running on port 9092.
setlocal

REM The h2 jar is copied to the h2 folder via the maven dependency plugin.

REM This script will reference the j2 jar via relative path links:  (this file location)\..\h2

REM We are starting the h2 server for tcp binding.  I guess we can still use file path binding
REM but I am not sure why you would want to do that, particularly if you are looking for a
REM shared database feel.

REM Location and name of the h2 jar.  Run mvn dependency:copy or mvn package to have the h2 jar copied to the h2 folder.
set THIS_DIR=%~dp0
set h2-jar-path=%THIS_DIR%\..\h2
set h2-jar-name=h2-1.4.200.jar

REM The root folder for placing the h2 databases.  This is relevant when you
REM are referencing the databases via a tcp connection.  This allows you to
REM use tcp:\\localhost\<database name> as opposed to a file path on the
REM host computer, which you probably shouldn't want to worry about.
set h2-base-directory=%h2-jar-path%\databases

CALL :check_server_running

REM Only start H2 if it is not already running.
if "%H2_RUNNING%" == "" goto :start_h2
goto :eof

:check_server_running
REM check to see if h2 is already running.
set TEMP_DIR=%THIS_DIR%\tmp
set TEMP_FILE=netstat.tmp
if not exist %TEMP_DIR%\NUL echo create %TEMP_DIR% && mkdir %TEMP_DIR%
netstat -an | find "9092" > %TEMP_DIR%\%TEMP_FILE%
set /P NETSTAT_OUT=<%TEMP_DIR%\%TEMP_FILE%
if exist %TEMP_DIR%\%TEMP_FILE% DEL %TEMP_DIR%\%TEMP_FILE%
if NOT "%NETSTAT_OUT%" == "" echo H2 is already running. && set H2_RUNNING=1
EXIT /B

REM For convenience, I have added the -ifNotExists parameter which allows H2 to create an initialed empty database,
REM when someone references a missing database.  This is very convenient for deleting databases and starting fresh.
:start_h2
@echo on
start java -Dh2.bindAddress=127.0.0.1 -Duser.timezone=UTC -cp %h2-jar-path%\%h2-jar-name% org.h2.tools.Server ^
 -tcpPort 9092 ^
 -baseDir %h2-base-directory% ^
 -tcp ^
 -web -webAdminPassword password ^
 -browser ^
 -pg ^
 -ifNotExists