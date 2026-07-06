# 회원관리 API 목록

현재 구현 기준 base path는 `/api`입니다.

## 공통 응답 포맷

성공/실패 응답은 `ApiResponse` 포맷을 사용합니다.

```json
{
  "status": "OK",
  "code": 200,
  "body": {}
}
```

예외 응답의 `body`는 다음 형태입니다.

```json
{
  "status": "BAD_REQUEST",
  "code": 400,
  "body": {
    "message": "요청 값이 올바르지 않습니다.",
    "errors": [
      "code: 회원번호는 4글자여야 합니다."
    ]
  }
}
```

## 회원 API

| URL | METHOD | PARAMS | DESC | STATUS |
|:---|:---:|:---|:---|:---:|
| `/api/members` | `POST` | body: `MemberRegisterRequest` | 신규 회원 등록 | `201` |
| `/api/members/{id}` | `GET` | path: `id<Long>` | 회원 상세 조회 | `200` |
| `/api/members/{id}` | `PUT` | path: `id<Long>`, body: `MemberUpdateRequest` | 회원 정보 수정 | `200` |

### 회원 등록

```http
POST /api/members
Content-Type: application/json
```

```json
{
  "code": "1234",
  "name": "홍길동",
  "belt": "WHITE",
  "grau": 0,
  "phone": "01012345678"
}
```

요청 필드:

| FIELD | TYPE | REQUIRED | DESC |
|:---|:---|:---:|:---|
| `code` | `String` | O | 숫자 4자리 |
| `name` | `String` | O | 회원 이름, blank 불가 |
| `belt` | `Belt` | O | `WHITE`, `BLUE`, `PURPLE`, `BROWN`, `BLACK` |
| `grau` | `Int` | O | 0 이상 4 이하 |
| `phone` | `String` | O | 하이픈 제외 휴대폰 번호 |

응답 예시:

```json
{
  "status": "CREATED",
  "code": 201,
  "body": {
    "id": 1,
    "name": "홍길동",
    "code": "1234",
    "phone": "01012345678",
    "belt": {
      "belt": "WHITE",
      "grau": 0
    },
    "registrationDate": "2026-07-06",
    "expirationDate": "2026-07-06"
  }
}
```

### 회원 상세 조회

```http
GET /api/members/{id}
```

응답 예시:

```json
{
  "status": "OK",
  "code": 200,
  "body": {
    "id": 1,
    "name": "홍길동",
    "code": "1234",
    "phone": "01012345678",
    "belt": {
      "belt": "BLUE",
      "grau": 2
    },
    "registrationDate": "2026-07-06",
    "expirationDate": "2026-07-06"
  }
}
```

### 회원 정보 수정

```http
PUT /api/members/{id}
Content-Type: application/json
```

```json
{
  "code": "1234",
  "belt": "BLUE",
  "grau": 2,
  "phone": "01012345678"
}
```

요청 필드:

| FIELD | TYPE | REQUIRED | DESC |
|:---|:---|:---:|:---|
| `code` | `String` | O | 숫자 4자리 |
| `belt` | `Belt` | O | `WHITE`, `BLUE`, `PURPLE`, `BROWN`, `BLACK` |
| `grau` | `Int` | O | 0 이상 4 이하 |
| `phone` | `String` | O | 하이픈 제외 휴대폰 번호 |

응답 예시:

```json
{
  "status": "OK",
  "code": 200,
  "body": {
    "id": 1,
    "name": "홍길동",
    "code": "1234",
    "phone": "01012345678",
    "belt": {
      "belt": "BLUE",
      "grau": 2
    },
    "registrationDate": "2026-07-06",
    "expirationDate": "2026-07-06"
  }
}
```

## 출석 API

| URL | METHOD | PARAMS | DESC | STATUS |
|:---|:---:|:---|:---|:---:|
| `/api/attendances` | `POST` | query: `code<String>` | 출석 등록 | `201` |
| `/api/attendances/daily` | `GET` | query: `date<yyyy-MM-dd>` | 일별 출결 리스트 조회 | `200` |

### 출석 등록

```http
POST /api/attendances?code=1234
```

응답 예시:

```json
{
  "status": "CREATED",
  "code": 201,
  "body": {
    "id": 1,
    "timestamp": "2026-07-06T10:30:00",
    "time": "10:30",
    "memberInfo": {
      "id": 1,
      "name": "홍길동",
      "code": "1234",
      "phone": "01012345678",
      "belt": {
        "belt": "BLUE",
        "grau": 2
      },
      "registrationDate": "2026-07-06",
      "expirationDate": "2026-07-06"
    }
  }
}
```

### 일별 출석 조회

`date`를 생략하면 서버 현재 날짜로 조회합니다.

```http
GET /api/attendances/daily?date=2026-07-06
```

응답 예시:

```json
{
  "status": "OK",
  "code": 200,
  "body": [
    {
      "id": 1,
      "timestamp": "2026-07-06T10:30:00",
      "time": "10:30",
      "memberInfo": {
        "id": 1,
        "name": "홍길동",
        "code": "1234",
        "phone": "01012345678",
        "belt": {
          "belt": "BLUE",
          "grau": 2
        },
        "registrationDate": "2026-07-06",
        "expirationDate": "2026-07-06"
      }
    }
  ]
}
```

## 에러 케이스

| CASE | STATUS | MESSAGE |
|:---|:---:|:---|
| 회원을 찾을 수 없음 | `404` | `Member Not Found` |
| 회원 코드 중복 | `400` | `코드가 중복되었습니다.` |
| 요청 값 검증 실패 | `400` | `요청 값이 올바르지 않습니다.` |
| 필수 query parameter 누락 또는 타입 불일치 | `400` | Spring MVC 예외 메시지 |

## 아직 구현되지 않은 API

기존 README에 있었지만 현재 컨트롤러에 없는 API입니다.

| URL | METHOD | DESC |
|:---|:---:|:---|
| `/api/members` | `GET` | 멤버 목록 조회 |
| `/api/members/{id}` | `DELETE` | 회원 정보 삭제 |
| `/api/validate_code/{code}` | `GET` | 출석번호 중복 조회 |
| `/api/attendances/{memberId}` | `GET` | 회원별 출결 기록 조회 |
