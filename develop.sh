cd server
mvn package
cd ..
docker-compose -f docker-compose-develop.yml up -d