docker-compose up -d --force-recreate --remove-orphans
docker-compose exec kafka0  kafka-topics --create --bootstrap-server localhost:9092 --replication-factor 1 --partitions 1 --topic devTesTask90POE


# docker run --rm -it -p 8000:8000  -e "KAFKA_REST_PROXY_URL=https://localhost:9092" -e "PROXY=true" landoop/kafka-topics-ui