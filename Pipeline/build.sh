#!/bin/sh

function buildFail {
    echo "Maven build failed"
}
trap buildFail ERROR

CURDIR=`pwd`

docker run --rm \
           -v "${CURDIR}/:/build" \
           --workdir /build/AcunaLambda \
           maven:3.5.2-jdk-8 mvn compile