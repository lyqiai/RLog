cd web
npm install
npm run build
cd ..
cd server
mvn package
cd ..
docker-compose -f docker-compose.yml up -d