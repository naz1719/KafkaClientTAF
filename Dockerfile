# ------- Execution -------
# docker build --build-arg ENV=garage --tag=compoud/back-end:v1 . --file=Dockerfile
# docker run -p 8080:8080 -it eu.gcr.io/agreed-gcp/prod-back-end:v1.0
# docker run -it compoud/back-end:v1 sh
# docker push eu.gcr.io/agreed-gcp/base-back-end:v1.0

# docker build --tag taf/90poe . --file=Dockerfile
# docker run -p 8080:8080 -it taf/90poe:v1.0
# docker run -it taf/90poe:v1.0 sh
# docker push eu.gcr.io/taf/90poe:v1.0

# set the base image
FROM gradle:6.9.0-jre11-openj9
MAINTAINER Nazar Khimin

WORKDIR /usr/src/taf
COPY . .
#  RUN gradlew build

#  ENTRYPOINT gradlew test --tests "messaging.MessageStreamingTest"