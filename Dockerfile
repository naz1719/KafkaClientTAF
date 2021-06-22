# docker build --tag taf/90poe . --file=Dockerfile
# docker run -p 8080:8080 -it taf/90poe
# docker run -it taf/90poe sh
# docker push eu.gcr.io/taf/90poe

# set the base image
FROM gradle:6.9.0-jdk11-openj9
MAINTAINER Nazar Khimin

WORKDIR /usr/src/taf
COPY . .

RUN gradle build -x test
ENTRYPOINT gradle test --tests "messaging.MessageStreamingTest"