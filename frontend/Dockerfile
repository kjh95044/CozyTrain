# next 이미지를 사용합니다. 뒤에 tag가 없으면 latest 를 사용.
FROM node:alpine as build

# root 에 app 폴더를 생성
RUN mkdir /app

# work dir 고정
WORKDIR /app

# work dir 에 build 폴더 생성 /app/build
RUN mkdir ./build

# host pc의 현재경로의 package.json을 workdir 로 복사
COPY package.json ./

# package.json의 종속성을 설치
RUN npm install

# host pc의 현재경로의 모든 파일을 workdir 로 복사
COPY . .

# next 앱 빌드
RUN npm run build

# build 단계에서 생성한 build 폴더를 workdir 의 build 폴더로 복사
COPY --from=build /app/build ./build

# nginx 의 default.conf 를 삭제
RUN rm /etc/nginx/conf.d/default.conf

# host pc 의 nginx.conf 를 아래 경로에 복사
COPY ./nginx.conf /etc/nginx/conf.d

# 3000 포트 오픈
EXPOSE 3000

# container 실행 시 자동으로 실행할 command. nginx 시작함
CMD ["nginx", "-g", "daemon off;"]