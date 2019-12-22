# ------- Execution -------
#   docker run -it taf/man:v1 sh
# docker build --tag=taf/man:v1 .  --file=./Dockerfile.dockerFile
# the version of gradle in build context and docker must have the same.

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
