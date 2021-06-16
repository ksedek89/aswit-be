#!/bin/sh
set -e
set -x

java -version
exec "$@"
