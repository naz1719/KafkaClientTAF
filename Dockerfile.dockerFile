# ------- Execution -------
# 1. docker build --tag=taf/man:v1 .  --file=./Dockerfile.dockerFile
# 2. docker run -it taf/man:v1 sh
# 3. docker exec 7d438c3fcead cat /var/jenkins_home/secrets/initialAdminPassword
#   docker run -p 8080:8080 -v /var/run/docker.sock:/var/run/docker.sock -v jenkins:/var/jenkins_home --name jenkins diemol/selenium_grid_workshop_jenkins:latest
#   docker-compose up --build --abort-on-container-exit
#   ARG GRADLE_COMMAND
#   docker build --tag=taf/man:v1 .  --file=./Dockerfile.dockerFile  --build-arg ARG=gradlew
#   docker run -it taf/man:v1 sh
#   docker run -it taf/man:v1 -e GRADLE_COMMAND='gradlew clean test'
#   docker run -it taf/man:v1 ./gradlew clean test

FROM openjdk:8u212-jdk-stretch
MAINTAINER Nazar Khimin <nazar.khimin@gmail.com>

WORKDIR /usr/src/project

COPY . .

RUN apt-get update && apt-get install jq -y && apt install dos2unix
RUN chmod +x wait-for-grid.sh && dos2unix wait-for-grid.sh
RUN ./gradlew