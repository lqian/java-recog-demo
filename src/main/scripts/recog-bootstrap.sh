#!/bin/bash

#set service name
SERVICE_NAME=AppBoot

# set the main class
MAIN_CLASS=com.xinhuan.examples.web.AppBoot

# set basic executable environment, do not modify those lines
BIN_HOME=$(dirname $0)
if [ "${BIN_HOME}" = "." ]; then
	BIN_HOME=$(pwd)
fi

BASE_HOME=${BIN_HOME%bin}

LIB_HOME=${BASE_HOME}lib

ETC_HOME=${BASE_HOME}etc

LOG_HOME=${BASE_HOME}logs

if [ ! -d "${LOG_HOME}" ] ; then
  mkdir -p "${LOG_HOME}"
fi

MAIN_JAR=${BIN_HOME}/java-recog-demo.jar


start() {
   pid=`ps aux|grep ${SERVICE_NAME}|grep -v grep|awk '{print $2}'`
   if [ -n "$pid" ]; then
     echo "service ${SERVICE_NAME} already start with PID :$pid"
     return 0
   fi
   nohup java ${JAVA_OPTS} -Djava.ext.dirs=${LIB_HOME}  -Dlog.home=${LOG_HOME} -cp ${ETC_HOME}:${MAIN_JAR} ${MAIN_CLASS}  2>&1 > /dev/null &
   echo -n "Starting $SERVICE_NAME : "
   pid=`ps aux | grep $SERVICE_NAME | grep -v grep | awk '{print \$2}' ` 
   if [ -n "$pid" ]; then
   	echo "start ${SERVICE_NAME} successfully with PID: ${pid}"
   else
   	echo "start ${SERIVCE_NAME} failed"
   fi
}

debug() {
   if [ -n "$pid" ]; then
     kill -9 $pid
   fi
   java -Djava.ext.dirs=${LIB_HOME} -cp ${ETC_HOME}:${MAIN_JAR} ${MAIN_CLASS}
}

stop() {
   pid=`ps aux|grep ${SERVICE_NAME}|grep -v grep|awk '{print $2}'` 
   if [ -z "$pid" ]; then
        echo "service $SERVICE_NAME already stopped"
   else
        kill $pid
        echo -n "Shutting down $SERVICE_NAME : "
        check_pid=`ps aux | grep $SERVICE_NAME | grep -v grep|awk '{print $2}'`
        while [ -n "${check_pid}" ]
        do
                check_pid=`ps aux | grep $SERVICE_NAME | grep -v grep | awk '{print $2}'`
                if [ -z "${check_pid}" ];then
                        break;
                fi
        done
        echo "stop ${SERVICE_NAME} with PID: ${pid}"
   fi
}


status() {
   pid=`ps aux|grep $SERVICE_NAME | grep -v grep | awk '{print $2}' `
   if [ -n "$pid" ] ;then
   	echo "service $SERVICE_NAME (pid $pid) is running ..."
   else
   	echo "service $SERVICE_NAME is stopped"
   fi
}

# See how we were called.
case "$1" in
  start)
	start
	;;
  stop)
	stop
	;;
  status)
	status
	;;
  restart)
	stop
	start
	;;
  debug)
  	debug
  	;;	
  *)
	echo $"Usage: $0 {start|stop|status|restart|debug}"
	exit 2
esac
