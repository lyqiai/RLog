FROM node:16.13.2-alpine

COPY . /logweb

RUN npm install -g http-server \
    && cd /logweb \
    && npm install \
    && npm run build

WORKDIR /logweb/build
