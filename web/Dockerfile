FROM node:latest
COPY . /logweb
ARG REACT_APP_BASE_URL
ENV REACT_APP_BASE_URL=$REACT_APP_BASE_URL
RUN cd /logweb && npm install