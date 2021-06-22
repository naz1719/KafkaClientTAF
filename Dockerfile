# docker build --tag taf/90poe:v1 . --file=Dockerfile
# docker run -p 52329:52329 -it taf/90poe:v1
# docker run -it taf/90poe:v1
# docker run -it taf/90poe:v1 sh
# docker push eu.gcr.io/taf/90poe:v1

# set the base image
FROM gradle:6.9.0-jdk11-openj9
MAINTAINER Nazar Khimin

WORKDIR /usr/src/taf
COPY . .

RUN gradle build -x test
ENTRYPOINT gradle test --tests "messaging.MessageStreamingTest"