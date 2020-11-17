# Install dependencies
npm install

# Serve with hot reload at localhost:9528
npm run dev

# Build for production with minification
npm run build

# Build for production and view the bundle analyzer report
npm run build --report

# code format check
npm run lint

# code format check and auto fix
npm run lint -- --fix

# Development 
Run on Chrome under development
  chrome --disable-web-security --user-data-dir="D:/tmp"
Access on browser
  http://localhost:8898/

# Deployment
docker build . -t dtmsv2-frontend

docker run -d -p 80:80 dtmsv2-frontend

curl localhost