# 0. 황재용 Git-hub    
<br/>  
<br/>  

# 1. Project Overview (프로젝트 개요)  
- 프로젝트 이름: Jlog  
- 프로젝트 설명:  SpringBoot 기반의 블로그 프로젝트를 AWS Ubuntu 가상 환경에 배포하여, 실제 서비스 배포 과정을 학습하고 경험하고자 진행한 프로젝트입니다.  
  
<br/>
<br/>

# 2. Team Members (팀원 및 팀 소개)
| 황재용 |  
|:------:|  
| Fullstack |  

<br/>
<br/>

# 3. Key Features (주요 기능)
- **회원가입**:
  - 회원가입 시 DB에 유저정보가 등록됩니다.

- **로그인**:
  - 사용자 인증 정보를 통해 로그인합니다.

- **임시 비밀번호 전송**:
  - 이메일이 테이블에 존재하는지 확인하고 이메일을 전송합니다.    
  - 임시 이메일로 users테이블에 비밀번호를 변경됩니다.  

- **게시판**:
  - 블로그에 사용자는 게시글을 작성할 수 있습니다.    


<br/>
<br/>


# 4. Technology Stack (기술 스택)   
|  |  |  
|-----------------|-----------------|  
| Java    |  <img src="https://github.com/user-attachments/assets/d89cffd5-ed08-4183-875d-c248b6462603" alt="Java" width="100"> |  
| Spring boot    |  <img src="https://github.com/user-attachments/assets/40ce0c36-fe55-438d-82da-95cd4bca7a7a" alt="SpringBoot" width="100"> |  
| Spring security    |  <img src="https://github.com/user-attachments/assets/c686bdd7-4b29-4c7b-b840-936f277fde16" alt="SpringSecurity" width="100"> |  
| Docker    |  <img width="100" alt="free-icon-docker-919853" src="https://github.com/user-attachments/assets/f9357b6b-cb19-4347-859c-64523af2b2bf" /> |    
| AWS    |  <img width="100" alt="free-icon-amazon-16183564" src="https://github.com/user-attachments/assets/0c27f383-e628-4c63-8227-e9fe49c7662c" /> |  
| MySQL    |  <img width="100" alt="free-icon-mysql-5968313" src="https://github.com/user-attachments/assets/001fca89-8d31-4fe3-924b-eb9174952b81" /> |    

<br/>

# 5. 개발 기간
- 2025년 8월 5일 ~ 2025 8월 21일  
<br/>
<br/>

# 6. 주요 기능 구현 @황재용  
## 결과물 영상  
[🎥유튜브 https://www.youtube.com/watch?v=sPFcLeRC0T0]  (https://studio.youtube.com/video/sPFcLeRC0T0/edit)  
[💬벨로그](https://velog.io/@iamjyh/jlog-%EB%B8%94%EB%A1%9C%EA%B7%B8-%ED%94%84%EB%A1%9C%EC%A0%9D%ED%8A%B8-%EB%B0%B0%ED%8F%AC-%EB%8F%84%EC%A0%84%EA%B8%B01)  

# 7. 회고  
SpringBoot프로젝트를 Docker 이미지로 빌드하여 Docker Hub에 배포하고, AWS Ubuntu 서버에서 이미지를 성공적으로 Pull받아 컨테이너로 실행했습니다. 이 과정에서 Dockerfile 및 Docker-Compose.yml의 핵심 개념을 깊이 있게 이해했으며, Docker 컨테이너와 이미지 역할을 명확히 파악하여 EC2 인스턴스 환경에서의 애플리케이션 구동 원리를 실질적으로 체득했습니다.  


<br/><br/>
