docker-compose -f automation-compose.yml up --build
docker cp automation:/usr/src/taf/build/reports/allure-report .
docker-compose -f automation-compose.yml down