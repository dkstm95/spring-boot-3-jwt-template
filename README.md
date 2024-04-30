## Reference
- https://github.com/ali-bouali/spring-boot-3-jwt-security
- (book) 만들면서 배우는 클린 아키텍처

## 기술 스택
- `Java 17`
- `Gradle`
- `Spring Boot 3.2.0`
- `Spring Data JPA`
- `Spring Security`
- `jsonwebtoken`
- `H2 Database`

## 프로젝트 구조
```
├── src
│   ├── main
│   │   ├── java
│   │   │   └── com.seungilahn
│   │   │       └── springboot3jwttemplate
│   │   │           ├── auth
│   │   │           ├── common
│   │   │           ├── config
│   │   │           └── user
│   │   │               ├── adapter
│   │   │                   ├── in
│   │   │                   └── out
│   │   │               ├── application
│   │   │                   ├── port
│   │   │                       ├── in
│   │   │                       └── out
│   │   │                   └── service
│   │   │               └── domain
```

## API
### 공통 응답
- Response
```json
{
  "code": 200,
  "message": "success",
  "data": {}
}
```

### 회원가입
- URL: `/api/v1/signup`
- Method: `POST`
- Request
```json
{
  "email": "test@email.com",
  "name": "test",
  "phoneNumber": "010-1234-5678",
  "password": "test1234",
  "role": "USER"
}
```
- Response
```json
{
  "access_token": "..",
  "refresh_token": ".."
}
```

<br>

### 로그인
- URL: `/api/v1/signin`
- Method: `POST`
- Request
```json
{
  "email": "test@email.com",
  "password": "test1234"
}
```
- Response
```json
{
  "access_token": "..",
  "refresh_token": ".."
}
```

<br>

### 로그아웃
- URL: `/api/v1/auth/signout`
- Method: `POST`
- Request
```json
{
  "refresh_token": ".."
}
```

<br>

### Refresh Token
- URL: `/api/v1/auth/refresh-token`
- Method: `POST`
- Request
```json
{
  "refresh_token": ".."
}
```
- Response
```json
{
  "access_token": "..",
  "refresh_token": ".."
}
```

<br>

### 회원 정보 수정
- URL: `/api/v1/users`
- Method: `PUT`
- Header
```
Authorization: Bearer {access_token}
```
- Request
```json
{
  "name": "test",
  "phoneNumber": "010-1234-5678"
}
```

<br>

### 회원 탈퇴
- URL: `/api/v1/users`
- Method: `DELETE`
- Header
```
Authorization: Bearer {access_token}
```