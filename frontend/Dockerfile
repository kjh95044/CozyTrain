# Node.js 이미지로 사용
FROM node:alpine as build

# root 에 app 폴더를 생성
# RUN mkdir /app

# # 작업 디렉토리를 /app으로 설정합니다
# WORKDIR /app

# # work dir 에 build 폴더 생성 /app/build
# RUN mkdir ./next

# # host pc의 현재경로의 build 폴더를 workdir 의 build 폴더로 복사
# ADD ./next ./next

# # 컨테이너에 package.json와 package-lock.json 파일을 복사합니다
# COPY package*.json ./

# COPY . .

# # 새로운 독립적인 nginx 이미지를 생성합니다
# FROM nginx

# 오픈할 포트를 지정합니다
EXPOSE 3000

RUN mkdir /app
WORKDIR /app
COPY package*.json ./

RUN npm install
COPY . /app
CMD npm start

# # nginx 의 default.conf 를 삭제
# RUN rm /etc/nginx/conf.d/default.conf

# # host pc 의 nginx.conf 를 아래 경로에 복사
# COPY ./nginx.conf /etc/nginx/conf.d

# nextjs 빌드 결과물을 /usr/share/nginx/html에 복사합니다
# COPY --from=build /app/out  /usr/share/nginx/html

# Nginx를 실행합니다
# CMD ["nginx", "-g", "daemon off;"]
# CMD ["node", "server.js"]
# CMD ["npm", "start"]
# CMD ["npm", "run", "dev"]