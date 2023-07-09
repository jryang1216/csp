#!/bin/sh

export secret_key=test

export JAVA_OPTS = "$JAVA_OPTS" -Ddatabase.serverName=jdbc:postgresql://172.17.0.4:5432/csp



