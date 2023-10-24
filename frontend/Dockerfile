# Node.js 이미지를 기반으로 사용합니다.
FROM node:alpine as build

# 작업 디렉토리를 설정합니다.
WORKDIR /usr/src/app

# 소스 코드를 현재 디렉토리로 복사합니다.
COPY . .

# 필요한 종속성을 설치하고 빌드합니다.
RUN npm install
RUN npm run build

# Nginx 이미지를 사용합니다.
FROM nginx

# Nginx 설정 파일을 복사합니다.
COPY nginx.conf /etc/nginx/conf.d/default.conf

# 빌드된 애플리케이션을 Nginx의 기본 디렉토리로 복사합니다.
COPY --from=build /usr/src/app/build /usr/share/nginx/html

# 80 포트를 엽니다.
EXPOSE 80

# Nginx를 실행합니다.
CMD ["nginx", "-g", "daemon off;"]