# Netfix Eureka naming server
- http://localhost:6789

# Build
docker build -t eureka:1.0 .
docker run -d --name dtms-eureka -p 6789:6789 eureka:1.0