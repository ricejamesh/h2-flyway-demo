REM Make this script work first by finding the correct h2-jar-path
REM Note the 'c:' before %HOMEPATH%, which is a subtle windows thing.
REM
REM The -? will show the h2 cli settings for running the server.
set h2-jar-path=c:%HOMEPATH%\h2-2019-10-14\h2\bin

java -cp %h2-jar-path%\h2-1.4.200.jar org.h2.tools.Server -?

