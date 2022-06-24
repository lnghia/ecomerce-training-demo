FROM node:16.14.0-alpine

WORKDIR /app

COPY package*.json ./
COPY /public public
COPY /src src

RUN npm install

CMD ["npm", "start"]