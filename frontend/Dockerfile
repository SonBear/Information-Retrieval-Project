# BUILD REACT APP
FROM  node:16-alpine3.14 AS build

MAINTAINER cherrysoft.com

WORKDIR /app

COPY ./frontend/package.json ./

RUN npm install

COPY ./frontend/ .

RUN npm run build

# CREATE NGINX SERVER
FROM nginx:1.21.3-alpine

COPY --from=build /app/build /usr/share/nginx/html

COPY ./frontend/nginx/nginx.conf /etc/nginx/nginx.conf

EXPOSE 3000

CMD ["nginx", "-g", "daemon off;"]
