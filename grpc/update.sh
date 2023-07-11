#!/bin/bash

set -o nounset
set -o errexit

BASEDIR="$( cd "$( dirname "${BASH_SOURCE[0]}" )" && pwd )"

IFS="
"

DEST=$BASEDIR/src/main/proto

rm $DEST/*.proto
cp $BASEDIR/../../qdrant/lib/api/src/grpc/proto/* $DEST
