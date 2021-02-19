#!/bin/bash
# Quick way to start h2 from intellij.

# The h2 jar is copied to the h2 folder via the maven dependency plugin.

# This script will reference the j2 jar via relative path links:  (this file location)/../h2 which is populated
# in that folder via the maven dependency copy plugin.

# We are starting the h2 server for tcp binding.  I guess we can still use file path binding
# but I am not sure why you would want to do that, particularly if you are looking for a
# shared database feel.

# Location of this script.
DIR="$( cd "$( dirname "${BASH_SOURCE[0]}" )" >/dev/null 2>&1 && pwd )"

# Location and name of the h2 jar.  Run mvn dependency:copy or mvn package to have the h2 jar copied to the h2 folder.
H2_JAR_PATH=${DIR}/../h2
H2_JAR_NAME=h2-1.4.200.jar

H2_PORT=9092
PG_PORT=5435

# The root folder for placing the h2 databases.  This is relevant when you
# are referencing the databases via a tcp connection.  This allows you to
# use tcp:\\localhost\<database name> as opposed to a file path on the
# host computer, which you probably shouldn't want to worry about.
H2_BASE_DIRECTORY=${H2_JAR_PATH}/databases

function is_H2_running() {
  local NETSTAT=$(netstat -an | grep -w "${H2_PORT}")

  if [ "$NETSTAT" == "" ]; then
    return 1;
  else
    # return 0 on success -> true. ie. H2 is running.
    return 0;
  fi
}

# If H2 is already moving, just exit.
if is_H2_running
then
  echo "H2 is already running";
  exit 0
else
  echo "H2 needs to start";
fi

# For convenience, I have added the -ifNotExists parameter which allows H2 to create an initialed empty database,
# when someone references a missing database.  This is very convenient for deleting databases and starting fresh.

# java -Dh2.bindAddress=127.0.0.1 -Duser.timezone=UTC -cp ${H2_JAR_PATH}/${H2_JAR_NAME} org.h2.tools.Server \
gnome-terminal --title="H2 Server - ${H2_PORT}" -- java -Duser.timezone=UTC -cp ${H2_JAR_PATH}/${H2_JAR_NAME} org.h2.tools.Server \
 -baseDir ${H2_BASE_DIRECTORY} \
 -tcp -tcpPort ${H2_PORT} \
 -web -webAdminPassword password \
 -webAllowOthers -tcpAllowOthers \
 -pg -pgPort ${PG_PORT} \
 -ifNotExists
