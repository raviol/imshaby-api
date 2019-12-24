#!/bin/bash

ENV_TYPE=$1
if [ -z "${ENV_TYPE}" ]
then
    ENV_TYPE="local"
fi
PROJECTS_DIR="$( cd "$( dirname "${BASH_SOURCE[0]}" )/../.." >/dev/null 2>&1 && pwd )"

mvn clean package -f ${PROJECTS_DIR}/imshaby-api/pom.xml
docker rmi local/imshaby-api:v1.0 imshaby-api:latest &> /dev/null
docker build -t imshaby-api ${PROJECTS_DIR}/imshaby-api
docker tag imshaby-api local/imshaby-api:v1.0
