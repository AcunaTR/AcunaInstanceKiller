#!/bin/sh

function buildFail {
    echo "Maven build failed"
}
trap buildFail ERR

CURDIR=`pwd`

docker run --rm \
           -v "${CURDIR}/:/build" \
           --workdir /build/AcunaCode \
           maven:3.5.2-jdk-8 mvn compile