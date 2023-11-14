FROM node:18.16.0-alpine as builder
# root 에 app 폴더를 생성
RUN mkdir /app
WORKDIR /app
# RUN mkdir ./build
COPY package.json ./
RUN npm install
# COPY ./ ./
COPY . /app
RUN npm run build
CMD ["npm", "start"]


#FROM nginx
EXPOSE 3000