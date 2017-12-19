#!/bin/sh

function deployFail {
    echo "Deploy to AWS Lambda failed"
}
trap deployFail ERROR

aws lambda update-function-code --function-name "AcunaLambdaKiller" --zip-file fileb://AcunaCode/target/demo-1.0.0.jar --region "us-east-1"
