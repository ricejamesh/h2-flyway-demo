#!/bin/bash
# Quick way to see the h2 command-line parameters.

# The h2 jar is populated via the maven dependency plugin to the $project\h2 folder.
# This script will reference it via relative path links:  <this script location>\..\h2

# Location of this script.
DIR="$( cd "$( dirname "${BASH_SOURCE[0]}" )" >/dev/null 2>&1 && pwd )"


# Location and name of the h2 jar.  Run mvn dependency:copy or mvn package to have the h2 jar copied to the h2 folder.
H2_JAR_PATH=${DIR}/../h2
H2_JAR_NAME=h2-1.4.200.jar

echo

java -cp ${H2_JAR_PATH}/${H2_JAR_NAME} org.h2.tools.Server -?
echo

