#!/bin/sh
# Gradle Wrapper script for Unix
APP_NAME="Gradle"
APP_BASE_NAME=`basename "$0"`
warn () { echo "$*"; }
die () { echo "$*"; exit 1; }
DIRNAME=`dirname "$0"`
[ -z "$INSTALL_PYTHON" ] && INSTALL_PYTHON=false
case "`uname`" in
  CYGWIN* ) cygwin=true ;;
  Darwin* ) darwin=true ;;
  MINGW* ) mingw=true ;;
esac
CLASSPATH=$APP_HOME/gradle/wrapper/gradle-wrapper.jar
exec java -classpath "$DIRNAME/gradle/wrapper/gradle-wrapper.jar" org.gradle.wrapper.GradleWrapperMain "$@"
