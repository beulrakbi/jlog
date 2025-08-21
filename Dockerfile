# 1. Java 런타임 환경이 있는 이미지 사용 (예: OpenJDK 17)
FROM openjdk:17-jdk-slim

# 2. 컨테이너 안에서 작업할 디렉토리 지정
WORKDIR /app

RUN apt-get update && apt-get install -y tzdata
# 한국 시간대로 심볼릭 링크 생성
RUN ln -sf /usr/share/zoneinfo/Asia/Seoul /etc/localtime
# 한국 시간대로 환경 변수 설정
ENV TZ=Asia/Seoul

# 3. 빌드된 jar 파일을 컨테이너 안으로 복사
COPY build/libs/Jlog-0.0.1-SNAPSHOT.jar app.jar
#   (Maven이라면 target/myapp-0.0.1-SNAPSHOT.jar)

# 4. 실행 명령어
ENTRYPOINT ["java", "-jar","app.jar"]
