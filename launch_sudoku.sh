# Build backend project with maven
cd backend || exit
mvn clean package install

# Go back to project root
cd ..

# Build frontend project
cd frontend/angularSudoku || exit
npm install
ng build --prod

# Go back to project root
cd ../..

# docker compose
docker-compose down
docker-compose up --build

# Shut everything down when done
docker-compose down
