# Apps
| Name | Port |
| ---------------- | ------------ |
| Neflix Eureka naming server | 6789 |
| Netflix Zuul Api gateway | 8888|
| AA Server | 9989 |
| Device Manager | 9111 |
| Notification Service | 8222 |
| TR-069 Device Service | 8555|
| Adminer (MySQL UI) | 9000|

# Run
- Install docker
- Build container:
cd aa-server
mvn clean install
docker build -t aa-server:1.0 .

cd device-manager
mvn clean install
docker build -t device-manager:1.0 .

cd eureka-naming-server
mvn clean install
docker build -t eureka:1.0 .

cd notification-service
mvn clean install
docker build -t notification-service:1.0 .

cd tr069-device-service
mvn clean install
docker build -t tr069-device-service:1.0 .

cd zuul-api-gateway
mvn clean install
docker build -t zuul:1.0 .

cd frontend
npm run build
docker built -t frontend:1.0 .

- Command: 
docker-compose up