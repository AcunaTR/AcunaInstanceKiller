#!/bin/sh

function testFail {
    echo "Maven test failed"
}
trap testFail ERR

CURDIR=`pwd`

docker run --rm \
           -v "${CURDIR}/:/test" \
           --workdir /test/AcunaCode \
           maven:3.5.2-jdk-8 mvn test