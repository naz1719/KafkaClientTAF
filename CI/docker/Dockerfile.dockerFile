# ------- Execution -------
# 1. docker build --tag=taf/man:v1 .  --file=./Dockerfile.dockerFile
# 2. docker run -it taf/man:v1 sh
# 3. docker exec 7d438c3fcead cat /var/jenkins_home/secrets/initialAdminPassword

#   docker run -p 8080:8080
#   -v /var/run/docker.sock:/var/run/docker.sock
#   -v jenkins:/var/jenkins_home --name jenkins diemol/selenium_grid_work
#   shop_jenkins:latest


# set the base image
FROM jenkins/jenkins:2.60.3
USER root

# author
MAINTAINER Nazar Khimin
# extra metadata
LABEL version="1.0"
LABEL description="Configured image based on Jenksin, JDK8 image and configured Gradle, Git in docker file"

# install git
RUN apt-get update
RUN apt-get install git
ENV GIT_HOME=/usr/bin/git

# install gradle
RUN apt -y install gradle
ENV GRADLE_HOME=/usr/bin/gradle