docker-compose -f automation-compose.yml up
docker cp automation:/usr/src/taf/build/reports/allure-report .
docker-compose -f automaMessageStreamingTesttion-compose.yml down