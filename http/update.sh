#!/bin/bash

set -o nounset
set -o errexit

BASEDIR="$( cd "$( dirname "${BASH_SOURCE[0]}" )" && pwd )"

IFS="
"

Q_VERSION=$1
C_VERSION=$2

DEST="$BASEDIR/src/main/openapi/openapi-v${C_VERSION}.json"
SRC="$BASEDIR/../../qdrant/docs/redoc/v${Q_VERSION}/openapi.json"
cp $SRC $DEST
